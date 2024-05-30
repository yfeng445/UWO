#include <iostream>
#include "Webserver.h"

/**
 * @brief main is the entry point of the program (launcher)
 * 
 * @return int 
 */
int main()
{
    Webserver server;
    server.run(8082);
}