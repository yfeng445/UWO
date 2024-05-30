#ifndef DATABASE_H
#define DATABASE_H
#include <mysql_driver.h>
#include <mysql_connection.h>
#include <cppconn/statement.h>
#include <cppconn/resultset.h>
#include <cppconn/prepared_statement.h>
#include <iostream>
#include <string>
#include <nlohmann/json.hpp>
#include <error.h>
#include <vector>
#include "include/crow_all.h"
using json = nlohmann::json;

struct Item
{
    std::string name;
    std::vector<std::string> tags;
    std::string summary;
    Item(){};
    Item(std::string name, std::vector<std::string> tags, std::string summary)
    {
        this->name = name;
        this->tags = tags;
        this->summary = summary;
    }
} typedef Item;

class Database
{
public:
    Database();
    ~Database();
    std::vector<Item> get(std::string query = "");
    void insert(Item newItem);
    crow::response handleQuery(std::string query);

private:
    sql::mysql::MySQL_Driver *driver;
    sql::Connection *con;
    sql::Statement *stmt;
    sql::ResultSet *res;
    Item item;
};

#endif