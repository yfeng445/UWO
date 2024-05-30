#ifndef VOICEAPI_H
#define VOICEAPI_H
#include <cstdlib>
#include <iostream>
#include <curl/curl.h>

class VoiceAPI {
public:
    VoiceAPI() {};
    void upload(std::string &filelPath);
};


#endif