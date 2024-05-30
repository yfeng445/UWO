#ifndef WEBSERVER_H
#define WEBSERVER_H
#include <iostream>
#include "Router.h"

class Webserver
{
public:
    Webserver();
    ~Webserver();
    void run(int port);
    void stop();
private:
    crow::SimpleApp app;
    Router router;
};
#endif