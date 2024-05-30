# HOP.cheap

Shop without Search, hop in shopping experience.

[Hop is cross-platform store search engine powered by Crow Framework (Crowcpp/v1.0+5)]

## Live Demo

Main stream:
## [https://hop.cheap](https://hop.cheap)

Dev servers: (Only online when developers run the project)
* dev.hop.cheap
* dev-f.hop.cheap
* dev-o.hop.cheap
* dev-t.hop.cheap
* dev-x.hop.cheap


Task automation file: 

[.vscode/launch.json](.vscode/launch.json)

## Dependencies

* gcc
* boost
* cmake
* libcurl
* Crowcpp/v1.0 or higher
* emscripten/v2.0 or higher
* nlohmann_json/v3.0 or higher
* libmysqlcppconn-dev/v1.1 or higher

## How to Install
### MacOS
**Install Homebrew (if not already installed)**
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

**Install dependencies**
```
brew install gcc boost libcurl cmake emscripten nlohmann_json mysql-connector-c++
```

### Linux
#### apt based
**Update package lists**
```
sudo apt-get update
```

**Install dependencies**
```
sudo apt-get install -y gcc libboost-all-dev libcurl4-openssl-dev cmake emscripten nlohmann-json-dev libmysqlcppconn-dev
```


### yum based
**Update package lists**
```
sudo yum update
```

**Install dependencies**
```
sudo yum install -y gcc boost-devel libcurl-devel cmake emscripten nlohmann-json-devel mysql-connector-c++
```

### arch based
**Update package lists**
```
sudo pacman -Syu
```

**Install dependencies**
```
sudo pacman -Syu gcc boost curl cmake emscripten jsoncpp mariadb-connector-c++
```

## How to RUN

> Press `F5` to run the project in VSCode

The server will be available at [http://localhost:8081](http://localhost:8081)

> Or go to our live demo

[https://hop.cheap](https://hop.cheap)

### Manually run

```
cd build
cmake ..
make
./Crow
```

## UI

![home-page.png](screenshots/home-page.png)
![preference-settings](screenshots/preference-settings.png)
![trending-api.png](screenshots/trending-api.png)
![login-with-a-random-email.png](screenshots/login-with-a-random-email.png)
![login-with-github-1.png](screenshots/login-with-github-1.png)
![login-with-github-2.png](screenshots/login-with-github-2.png)
![database-query-result.png](screenshots/database-query-result.png)
![database-login-page.png](screenshots/database-login-page.png)
![database-dashboard.png](screenshots/database-dashboard.png)
![database-table.png](screenshots/database-table.png)

[Demo Video](screenshots/demo.mp4)

## Web Stack

* Debian 11 Bullseye (OS)
* Caddy/v2.7.5 (web server)
* Argo Tunnel (reverse proxy)
* Cloudflare (DNS/CDN)

## Database

* MariaDB/10.5.22
* phpMyAdmin/5.0.4

Dashboard (phpMyAdmin):
[https://db.hop.cheap](https://db.hop.cheap)