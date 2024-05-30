cmake_minimum_required(VERSION 3.10)
project(Crow)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(Crow
        include/crow_all.h
        main.cpp
        Webserver.cpp
        Router.cpp
        API.cpp
        Voice.cpp
        Database.cpp)

# Find the CURL library
find_package(CURL REQUIRED)

# If CURL is found, add the include directories to your project
if(CURL_FOUND)
    include_directories(${CURL_INCLUDE_DIRS})
    target_link_libraries(Crow PRIVATE ${CURL_LIBRARIES})
else()
    message(FATAL_ERROR "CURL library not found")
endif()

# Manually set the paths to the MySQL Connector/C++ headers and libraries
include_directories(/usr/include/cppconn)
find_library(MYSQLCPPCONN_LIB mysqlcppconn)
target_link_libraries(Crow PRIVATE ${MYSQLCPPCONN_LIB})

# If json library is found, add it to the project
find_package(nlohmann_json 3.0.0 REQUIRED)
if(nlohmann_json_FOUND)
    # The target should already be set up with include directories, etc.
    target_link_libraries(Crow PRIVATE nlohmann_json::nlohmann_json)
else()
    message(FATAL_ERROR "nlohmann_json not found")
endif()

# Find Asio library
list(APPEND CMAKE_PREFIX_PATH "/opt")
target_link_libraries(Crow PRIVATE pthread)
include_directories(/usr/local/include)
include_directories(/opt/homebrew/Cellar/asio/1.28.1/include)

# Copy templates and static folders to build directory
file(COPY ${CMAKE_SOURCE_DIR}/templates DESTINATION ${CMAKE_BINARY_DIR})
file(COPY ${CMAKE_SOURCE_DIR}/static DESTINATION ${CMAKE_BINARY_DIR})