/**
 * @file Database.cpp
 * @author Yulun Feng (yfeng445)
 * @brief A database class that handles all the database operations
 * @date 2023-11-28
 *
 * @copyright Copyright (c) 2023
 *
 */

#include "Database.h"
#include <string>

/**
 * @brief Construct a new Database:: Database object
 *
 */
Database::Database()
{
    driver = sql::mysql::get_mysql_driver_instance();
    con = driver->connect("tcp://127.0.0.1:3306", "hop", "2^WsMm$3UA8uXcYn%U");
    stmt = con->createStatement();
}

/**
 * @brief Destroy the Database:: Database object
 *
 */
Database::~Database()
{
    delete stmt;
    delete con;
    delete driver;
}

/**
 * @brief get items from the database
 *
 * @param query
 * @return std::vector<Item>
 */
std::vector<Item> Database::get(std::string query)
{
    int col;
    std::vector<Item> items;
    stmt->execute("USE hop");
    if (query.length() == 0)
    {
        res = stmt->executeQuery("SELECT * FROM items");
    }
    else
    {
        res = stmt->executeQuery("SELECT * FROM items WHERE tags LIKE '%" + query + "%'");
    }
    col = res->getMetaData()->getColumnCount();
    while (res->next())
    {
        Item item;
        item.name = res->getString(2);        // Updated column index
        item.summary = res->getString(4);     // Updated column index
        std::string tags = res->getString(3); // Updated column index
        std::string tag;
        for (int i = 0; i < tags.length(); i++)
        {
            if (tags[i] == ',' || tags[i] == ']')
            {
                item.tags.push_back(tag);
                tag = "";
            }
            else if (tags[i] == '[' || tags[i] == '"')
            {
                continue;
            }
            else
            {
                tag += tags[i];
            }
        }
        items.push_back(item);
    }
    return items;
}

/**
 * @brief convert a vector of strings to a string
 *
 * @param vec
 * @return std::string
 */
std::string vectorToString(const std::vector<std::string> &vec)
{
    std::string result = "{'tags': ['}";
    for (const auto &str : vec)
    {
        result += str;
        result += "','";
    }
    result += "']}";
    return result;
}

/**
 * @brief Insert a new item into the database
 *
 * @param newItem
 */
void Database::insert(Item newItem)
{
    std::string inputStr = "INSERT INTO items (name, tags, summary) VALUES ('";
    inputStr += newItem.name;
    inputStr += "', '";
    inputStr += vectorToString(newItem.tags);
    inputStr += "', '";
    inputStr += newItem.summary;
    inputStr += "');";
    std::cout << inputStr << std::endl;
    stmt->execute("USE hop");
    stmt->execute(inputStr);
    delete stmt;
    return;
}

// crow::response Database::handleQuery(std::string query)
// {
//     stmt->execute("USE hop");
//     res = stmt->executeQuery(query);
//     json items;
//     while (res->next())
//     {
//         json object;
//         object["id"] = res->getString(1);
//         object["name"] = res->getString(2);
//         object["tags"] = res->getString(3);
//         object["summary"] = res->getString(4);
//         items.push_back(object);
//     }
//     delete stmt;
//     delete con;
//     delete res;
//     auto test = crow::response(items.dump());
//     test.set_header("Content-Type", "application/json");
//     return test;
// }
