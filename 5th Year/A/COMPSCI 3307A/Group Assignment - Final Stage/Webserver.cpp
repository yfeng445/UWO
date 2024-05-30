/**
 * @file Webserver.cpp
 * @author Maxwell Ding (jding263)
 * @brief Webserver class that works as the framework of the web server
 * @date 2023-11-28
 * 
 * @copyright Copyright (c) 2023
 * 
 */

#include "Webserver.h"

/**
 * @brief Construct a new Webserver:: Webserver object
 * 
 */
Webserver::Webserver()
{
    router.enroute(app);
    std::cout << "Web Server created" << std::endl;
}

/**
 * @brief Destroy the Webserver:: Webserver object
 * 
 */
Webserver::~Webserver()
{
    std::cout << "Web Server destroyed" << std::endl;
}

/**
 * @brief run the webserver on the specified port
 * 
 * @param port 
 */
void Webserver::run(int port)
{
    std::cout << "Web Server running on port " << port << std::endl;
    try {
        app.port(port).multithreaded().run();
    }
    catch (std::exception& e) {
        std::cout << e.what() << std::endl;
    
    }
}

/**
 * @brief stop the webserver
 * 
 */
void Webserver::stop()
{
    std::cout << "Web Server stopped" << std::endl;
}