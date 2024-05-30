#ifndef VOICE_H
#define VOICE_H

#include <boost/archive/iterators/binary_from_base64.hpp>
#include <boost/archive/iterators/transform_width.hpp>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <map>

class Voice {
public:
    Voice() {};
    void upload(std::string& body, const std::string& file_path);
    std::map<std::string, std::string> parse_form_data(const std::string& body);
    void decode_base64_and_write_to_file(const std::string& base64, const std::string& file_path);
};

#endif