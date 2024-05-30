/**
 * @file API.cpp
 * @author Truman Huang (yhuan939) , Matthew Owen Tjhie (mtjhie)
 * @brief API class that handles all the API calls and send data to database
 * @date 2023-11-28
 *
 * @copyright Copyright (c) 2023
 *
 */

#include "API.h"
#include <nlohmann/json.hpp>
#include <sstream>
#include <algorithm>
using json = nlohmann::json;

/**
 * @brief Construct a new API:: API object
 *
 */
size_t write_callback(void *contents, size_t size, size_t nmemb, std::string *s)
{
    size_t newLength = size * nmemb;
    try
    {
        s->append((char *)contents, newLength);
        return newLength;
    }
    catch (std::bad_alloc &e)
    {
        // Handle memory allocation error
        return 0;
    }
}

/**
 * @brief Store API information
 *
 */
API::API()
{
    OpenAI = {
        "OpenAI",
        "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",
        "https://api.openai.com/v1/chat/completions"};

    OpenAI_Vision = {
        "OpenAI",
        "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",
        "https://api.openai.com/v1/chat/completions"};
    GoogleTrends = {
        "GoogleTrends",
        "",
        "https://trends.google.com/trends/trendingsearches/daily/rss?geo=CA"};
    BingSuggestion = {
        "BingSuggestion",
        "",
        "https://www.bing.com/osjson.aspx?query="};
    // openAI_link= "https://api.openai.com/v1/chat/completions";
    // openAI_key="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";
}

/**
 * @brief Accept json file in string format from openAI API and extract the key words store in allTags vector.
 * @param std::string &responseData(from API), std::vector<std::string> &allTags 
 */
void API::extractResponseData(const std::string &responseData, std::vector<std::string> &allTags)
{
    try
    {
        auto responseJson = json::parse(responseData);

        auto choices = responseJson["choices"];
        if (choices.is_array() && !choices.empty())
        {
            std::string content = choices[0]["message"]["content"];

            // Temporary set to avoid duplicates
            std::set<std::string> uniqueTags;

            size_t tagsStart = 0;
            while ((tagsStart = content.find("[\"", tagsStart)) != std::string::npos)
            {
                size_t tagsEnd = content.find("\"]", tagsStart);
                if (tagsEnd == std::string::npos)
                    break; // Exit if no closing bracket found

                std::string tagsStr = content.substr(tagsStart + 2, tagsEnd - tagsStart - 2); // Extract tags string

                // Split the tags and add to the set
                std::istringstream tagStream(tagsStr);
                std::string tag;
                while (std::getline(tagStream, tag, ','))
                {
                    tag.erase(std::remove_if(tag.begin(), tag.end(), [](char c)
                                             { return c == ' ' || c == '\"'; }),
                              tag.end()); // Remove spaces and double quotes
                    uniqueTags.insert(tag);
                }

                tagsStart = tagsEnd + 1; // Move to the end of current tags array
            }

            // Transfer unique tags to the allTags vector
            allTags.assign(uniqueTags.begin(), uniqueTags.end());
        }
    }
    catch (const std::exception &e)
    {
        std::cerr << "Error parsing response data: " << e.what() << std::endl;
    }
}

/**
 * @brief Accept user input , link the openAI API to generate keywords tags
 * @param User query(std::string message)
 * @return tags
 */
std::string API::response_openAI(std::string message)
{
    CURL *curl;
    CURLcode res; // Default to an error code
    std::string name;
    std::string summary;
    std::vector<std::string> tags;
    response = "";
    curl = curl_easy_init();
    if (curl)
    {
        curl_easy_setopt(curl, CURLOPT_CUSTOMREQUEST, "POST");
        curl_easy_setopt(curl, CURLOPT_URL, OpenAI.url.c_str());
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
        curl_easy_setopt(curl, CURLOPT_DEFAULT_PROTOCOL, "https");
        struct curl_slist *headers = NULL;
        char header_string[256]; // Adjust the size as necessary
        sprintf(header_string, "Authorization: Bearer %s", OpenAI.key.c_str());
        headers = curl_slist_append(headers, header_string);
        headers = curl_slist_append(headers, "Content-Type: application/json");
        headers = curl_slist_append(headers, "Cookie: __cf_bm=a2G2DPqwgZfjno6TxGTGlr67X7QEWfuocCUMkPt_J.A-1700083564-0-ASn1MqABc/z7RkLFqTNrDWdVFQzKDKEb2oaXS7hBu/lnsNncEUfdGrm0vqxQHVYaOJ7pwOUPpjelWc0Bunjszkw=; _cfuvid=WoTQbHaZY_70._GaSXA3ATmR9Or895X_RssxxmJeGz0-1700081522623-0-604800000");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
        char data[1024]; // Adjust the size as necessary
        sprintf(data, "{\n    \"model\": \"gpt-4\",\n    \"messages\": [{\"role\": \"system\", \"content\": \"Generate keywords from the following user input\"},\n        {\"role\": \"user\", \"content\": \"%s\"},\n        {\"role\": \"user\", \"content\": \"Based on the user's input, provide a general tags in the format of a JSON file. The JSON should have 4 properties: 'id', 'name', 'summary', and 'tags'. Each recommendation should include at least 3 tags. Format the response as follows: 'id': [unique_id], 'name': [product_name], 'summary': [product_description], 'tags': {['tag1', 'tag2', 'tag3', 'tag4']}\"}]\n  }", message.c_str());
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, data);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
        res = curl_easy_perform(curl);
        // Check if curl_easy_perform was successful
        if (res == CURLE_OK)
        {
            extractResponseData(response, tags);

            // Output the extracted data for verification
            // std::cout << response << std::endl;
            // std::cout << "Name: " << name << std::endl;
            // std::cout << "Summary: " << summary << std::endl;
            std::cout << "Tags: ";
            for (const auto &tag : tags)
            {
                std::cout << tag << " ";
            }
            std::cout << std::endl;
        }
        else
        {
            std::cerr << "curl_easy_perform() failed: " << curl_easy_strerror(res) << std::endl;
        }

        curl_easy_cleanup(curl);
    }
    else
    {
        std::cerr << "Error initializing curl" << std::endl;
    }
    return response;
}

/**
 * @brief response from googleTrends
 *
 * @param name
 * @param key
 * @param url
 */
std::string API::googleTrends()
{
    response = "";
    curl = curl_easy_init();
    if (curl)
    {
        curl_easy_setopt(curl, CURLOPT_URL, GoogleTrends.url.c_str());
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
        res = curl_easy_perform(curl);
        curl_easy_cleanup(curl);
    }
    return response;
}

/**
 * @brief response from bingSuggestion
 *
 * @param name
 * @param key
 * @param url
 */
std::string API::bingSuggestion(std::string query)
{
    response = "";
    curl = curl_easy_init();
    if (curl)
    {
        curl_easy_setopt(curl, CURLOPT_URL, (BingSuggestion.url + query).c_str());
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
        res = curl_easy_perform(curl);
        curl_easy_cleanup(curl);
    }
    return response;
}

/**
 * @brief get item information from openAI
 *
 * @param name
 * @param key
 * @param url
 */
Item API::vision_openAI(std::string imageURL)
{
    Item item;
    std::string name;
    std::string summary;
    std::vector<std::string> tags;
    response_vision = "";
    CURL *curl;
    CURLcode res;
    curl = curl_easy_init();
    if (curl)
    {
        curl_easy_setopt(curl, CURLOPT_CUSTOMREQUEST, "POST");
        curl_easy_setopt(curl, CURLOPT_URL, "https://api.openai.com/v1/chat/completions?Authorization=Bearer%20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&Content-Type=application%2Fjson");
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
        curl_easy_setopt(curl, CURLOPT_DEFAULT_PROTOCOL, "https");
        struct curl_slist *headers = NULL;
        // headers = curl_slist_append(headers, "Authorization: Bearer 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");
        char header_string[256]; // Adjust the size as necessary
        sprintf(header_string, "Authorization: Bearer %s", OpenAI_Vision.key.c_str());
        headers = curl_slist_append(headers, header_string);
        headers = curl_slist_append(headers, "Content-Type: application/json");
        headers = curl_slist_append(headers, "Cookie: __cf_bm=h.pFjbb9HoNL1BPhLTPuk7McIEU4vJBuGxDjgprcejk-1700687871-0-Afy9GnYFrq+bLZg0laFb1BQ8znMhEpNwwv+kr+sHa0to3tL67n5jFiEMGFX2X3IWNaghreRzhqoT8jM/bRbXlT8=; _cfuvid=Qpi3Eam2TduP9HHXfuzsfiDW1RO20Sn_c8nGObMw260-1700687871631-0-604800000");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
        char data[4096]; // Increase the buffer size if needed
        sprintf(data, "{\n    \"model\": \"gpt-4-vision-preview\",\n    \"messages\": [\n      {\n        \"role\": \"user\",\n        \"content\": [\n          {\n            \"type\": \"text\",\n            \"text\": \"Analyze this image and generate a general product description in a structured JSON format with properties: id, name, summary, and tags.\"\n          },\n          {\n            \"type\": \"image_url\",\n            \"image_url\": {\n              \"url\": \"%s\"\n            }\n          }\n        ]\n      }\n    ],\n    \"max_tokens\": 300\n  }", imageURL.c_str());

        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, data);

        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response_vision);

        res = curl_easy_perform(curl);
        if (res == CURLE_OK)
        {
            extractImageData(response_vision, name, summary, tags);
            item.name = name;
            item.summary = summary;
            item.tags = tags;
        }
        else
        {
            std::cerr << "curl_easy_perform() failed: " << curl_easy_strerror(res) << std::endl;
        }
    }
    curl_easy_cleanup(curl);
    return item;
}

/**
 * @brief extract image data from openAI
 *
 * @param name
 * @param key
 * @param url
 */
void API::extractImageData(const std::string &responseData_vision, std::string &name, std::string &summary, std::vector<std::string> &tags)
{
    try
    {
        auto responseJson = json::parse(responseData_vision);

        auto choices = responseJson["choices"];
        if (choices.is_array() && !choices.empty())
        {
            // Remove the code block markers
            std::string content = choices[0]["message"]["content"];
            size_t codeBlockStart = content.find("```json") + 7;
            size_t codeBlockEnd = content.rfind("```");
            if (codeBlockEnd != std::string::npos && codeBlockStart < codeBlockEnd)
            {
                content = content.substr(codeBlockStart, codeBlockEnd - codeBlockStart);

                // Parse the JSON content
                auto productJson = json::parse(content);

                // Extract name, summary, and tags
                name = productJson["name"];
                summary = productJson["summary"];
                for (const auto &tag : productJson["tags"])
                {
                    tags.push_back(tag.get<std::string>());
                }
            }
        }
    }
    catch (const std::exception &e)
    {
        std::cerr << "Error parsing response data: " << e.what() << std::endl;
    }
}

// int main(){
//     API api;
//     //api.response_openAI("I wanna buy phone");
//     api.vision_openAI("https://imgs.search.brave.com/JN8V-JvR6e1uaZF1iT5CFFuwnLMazAJ8LH2hPi5L0eI/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5nZXR0eWltYWdl/cy5jb20vaWQvMTIz/NTcyODkwMy9waG90/by9hLTEzLWluY2gt/YXBwbGUtbWFjYm9v/ay1wcm8tbGFwdG9w/LWNvbXB1dGVyLXRh/a2VuLW9uLWphbnVh/cnktMjAtMjAyMS5q/cGc_cz02MTJ4NjEy/Jnc9MCZrPTIwJmM9/aFlSYnYxZThCNlk5/TWF2UVpfX29ibGhr/S0hUbVVyS2pXc0hs/QTNxZHppWT0");
//     return 0;
//     //g++ API.cpp -o API -lcurl
// }
