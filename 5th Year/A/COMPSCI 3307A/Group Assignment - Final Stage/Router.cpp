/**
 * @file Router.cpp
 * @author 	Maxwell Ding (jding263)
 * @brief Router class that works for the content of the web server
 * @date 2023-11-28
 *
 * @copyright Copyright (c) 2023
 *
 */

#include "Router.h"

Router::Router() : app(app)
{
}

Router::~Router()
{
    std::cout << "Router destroyed" << std::endl;
}

/**
 * @brief enroute routes the app to the specified path
 *
 * @param app
 * @return int
 */
int Router::enroute(crow::SimpleApp &app)
{
    try
    {
        CROW_ROUTE(app, "/")
        ([&](const crow::request &req)
         {
             auto page = crow::mustache::load("index.html");
             std::time_t now = std::time(nullptr);
             ctx["title"] = "Home - HOP";
             ctx["description"] = "HOP.cheap: Shop without Search, hop in shopping experience.";
             ctx["year"] = std::localtime(&now)->tm_year + 1900;
             return page.render(ctx); });

        CROW_ROUTE(app, "/favicon.ico")
        ([]
         {
            auto favicon = crow::response(200);
            favicon.set_static_file_info("static/favicon.ico");
            return favicon; });

        CROW_ROUTE(app, "/admin")
        ([&](const crow::request &req)
         {
            auto page = crow::mustache::load("dash.html");
            return page.render(ctx); });

        CROW_ROUTE(app, "/uploadImage")
            .methods("POST"_method)([&](const crow::request &req)
                                    {
                                        std::string image_url = req.body;
                                        size_t pos = image_url.find('=');
                                        if (pos == std::string::npos)
                                        {
                                            // '=' not found, handle error
                                            return crow::response(400, "Bad Request: URL not found");
                                        }

                                        image_url = image_url.substr(pos + 1);
                                        std::string decodedUrl = urlDecode(image_url);
                                        Item response = api.vision_openAI(decodedUrl);
                                        // Do something with the response, maybe send it back to the client or process it further
                                        db.insert(response);
                                        return crow::response(200, "Item inserted successfully"); // Example response
                                    });

        CROW_ROUTE(app, "/search")
        ([&](const crow::request &req)
         {
            // get query
            std::string query = req.url_params.get("q") ? req.url_params.get("q") : "",
                        history = req.url_params.get("h") ? req.url_params.get("h") : "",
                        type = req.url_params.get("type") ? req.url_params.get("type") : "";
            if(type == "json")
            {
                crow::json::wvalue json;
                json["type"] = type;
                json["query"] = query;
                json["result"] = handleQuery(query);
                return crow::response(json);
            }
            if(history != "")
            {
                /* demo data start */
                return crow::response(crow::json::wvalue({"best seasonal fruits for autumn", "best deal in Blackfriday 2024", "what should I buy for my GF's birthday?"}));
                /* demo data end */
            }
            // if query includes deal
            if(query.find("deal") != std::string::npos)
            {
                auto page = crow::mustache::load("search1.html");
                ctx["title"] = query + " - HOP";
                ctx["description"] = "Search results for " + query;
                ctx["query"] = query;
                auto response = page.render(ctx);
                return crow::response(response);
            } 
            else {
                auto page = crow::mustache::load("search.html");
                ctx["title"] = query + " - HOP";
                ctx["description"] = "Search results for " + query;
                ctx["query"] = query;
                auto response = page.render(ctx);
                return crow::response(response); 
            } });

        CROW_ROUTE(app, "/openai/<string>")
        ([&](std::string query)
         { return crow::response(api.response_openAI(query)); });

        CROW_ROUTE(app, "/suggestion")
        ([&]()
         { return crow::response(api.googleTrends()); });

        CROW_ROUTE(app, "/suggestion/<string>")
        ([&](std::string query)
         { return crow::response(api.bingSuggestion(query)); });

        CROW_ROUTE(app, "/db")
        ([&]()
         {
             crow::json::wvalue json;
             // Magic! Don't touch
             std::vector<crow::json::wvalue> items_list; // list of wvalue
             for (Item item : db.get())
             {
                 crow::json::wvalue item_json; // new wvalue
                 item_json["name"] = item.name;
                 std::vector<crow::json::wvalue> tags_list; // list of wvalue
                 for (std::string tag : item.tags) // iterate through tags
                 {
                     tags_list.push_back(tag); // push wvalue
                 }
                 item_json["tags"] = crow::json::wvalue(tags_list); // wvalue of list of wvalue
                 item_json["summary"] = item.summary;
                 items_list.push_back(item_json);
             }
            json = std::move(crow::json::wvalue::list(items_list)); // wvalue of list of wvalue
            return crow::response(json); });

        // to handle the POST request sent from the webpage
        // that contains the blob object of the recorded voice
        CROW_ROUTE(app, "/voiceUpload")
            .methods("POST"_method)([&](const crow::request &req)
                                    {
                std::time_t now = std::time(nullptr);
                crow::multipart::message parts(req);
                crow::multipart::part file = parts.get_part_by_name("file");
                std::string file_path = "static/upload/" + std::to_string(std::localtime(&now)->tm_sec) + ".mp3";
                std::string base64 = file.body.erase(0, 22);
                std::cout << base64 << std::endl;
                voice.decode_base64_and_write_to_file(file.body, file_path);
                return crow::response(200); });
    }
    catch (const std::exception &e) // catch any exceptions
    {
        std::cout << e.what() << std::endl;
    }
    return 0;
}

/**
 * @brief handleQuery handles the query and returns the result in json format
 *
 * @param query
 * @return crow::json::wvalue
 */
crow::json::wvalue Router::handleQuery(std::string query)
{
    crow::json::wvalue json;
    // Magic! Don't touch
    std::vector<crow::json::wvalue> items_list; // list of wvalue
    std::vector<Item> items = db.get(query);
    if (items.size() == 0)
    {
        return crow::json::wvalue({"No result found."});
    }
    for (Item item : items)
    {
        crow::json::wvalue item_json; // new wvalue
        item_json["name"] = item.name;
        std::vector<crow::json::wvalue> tags_list; // list of wvalue
        for (std::string tag : item.tags)          // iterate through tags
        {
            tags_list.push_back(tag); // push wvalue
        }
        item_json["tags"] = crow::json::wvalue(tags_list); // wvalue of list of wvalue
        item_json["summary"] = item.summary;
        items_list.push_back(item_json);
    }

    json = std::move(crow::json::wvalue::list(items_list)); // wvalue of list of wvalue
    return json;
}

/**
 * @brief urlDecode decodes the url
 *
 * @param encoded
 * @return std::string
 */
std::string Router::urlDecode(const std::string &encoded)
{
    std::string decoded;
    decoded.reserve(encoded.length());

    for (size_t i = 0; i < encoded.length(); ++i)
    {
        if (encoded[i] == '%' && i + 2 < encoded.length())
        {
            std::string hex = encoded.substr(i + 1, 2);
            decoded += static_cast<char>(std::strtol(hex.c_str(), nullptr, 16));
            i += 2;
        }
        else if (encoded[i] == '+')
        {
            decoded += ' ';
        }
        else
        {
            decoded += encoded[i];
        }
    }

    return decoded;
}
