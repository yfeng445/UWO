/*
 * Copyright (C) 2008 Emweb bv, Herent, Belgium.
 *
 * See the LICENSE file for terms of use.
 */

/*
 * To run the file:
 *  $ make
 *  $ ./hello --docroot . --http-listen 0.0.0.0:8080
 */

#include <Wt/WApplication.h>
#include <Wt/WBreak.h>
#include <Wt/WContainerWidget.h>
#include <Wt/WLineEdit.h>
#include <Wt/WPushButton.h>
#include <Wt/WText.h>
#include <iostream>
#include <fstream>
#include <string>
#include <list>
#include <random>
#include <cctype>
#include <algorithm>
#include "HelloApplication.h"

/*
 * The env argument contains information about the new session, and
 * the initial request. It must be passed to the WApplication
 * constructor so it is typically also an argument for your custom
 * application constructor.
*/
HelloApplication::HelloApplication(const Wt::WEnvironment& env)
        : WApplication(env) {
    setTitle("This is a SIMPLE wordle game");// application title



    // Before the inputs...
    root()->addWidget(std::make_unique<Wt::WText>("This is a SIMPLE wordle gameboard")); // show some text
    root()->addWidget(std::make_unique<Wt::WBreak>());
    root()->addWidget(std::make_unique<Wt::WBreak>());

    auto getKey = root()->addWidget(std::make_unique<Wt::WPushButton>("Click here to Start:"));
    getKey->setMargin(0, Wt::Side::Left);
    getKey->clicked().connect(this, &HelloApplication::getKeyword);
    keyword = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());


    // Gird sections //
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G00 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G01 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G02 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G03 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G04 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    auto firstButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 1st row"));
    firstButton->setMargin(10, Wt::Side::Left);
    firstButton->clicked().connect(this, &HelloApplication::firstRow);
    firstResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G10 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G11 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G12 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G13 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G14 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    auto secondButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 2nd row"));
    secondButton->setMargin(10, Wt::Side::Left);
    secondButton->clicked().connect(this, &HelloApplication::secondRow);
    secondResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G20 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G21 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G22 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G23 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G24 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    auto thirdButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 3rd row"));
    thirdButton->setMargin(10, Wt::Side::Left);
    thirdButton->clicked().connect(this, &HelloApplication::thirdRow);
    thirdResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G30 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G31 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G32 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G33 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G34 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    auto fourthButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 4th row"));
    fourthButton->setMargin(10, Wt::Side::Left);
    fourthButton->clicked().connect(this, &HelloApplication::fourthRow);
    fourthResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G40 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G41 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G42 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G43 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G44 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    auto fifthButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 5th row"));
    fifthButton->setMargin(10, Wt::Side::Left);
    fifthButton->clicked().connect(this, &HelloApplication::fifthRow);
    fifthResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    root()->addWidget(std::make_unique<Wt::WBreak>());


    result = root()->addWidget(std::make_unique<Wt::WText>());
    result -> setText("Enter guess, 5 letters maximum:");
    guessWord = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    guessWord->enterPressed().connect(std::bind(&HelloApplication::guess, this));
    auto guess = root()->addWidget(std::make_unique<Wt::WPushButton>("Submit"));
    guess->setMargin(5, Wt::Side::Left);
    guess->clicked().connect(this, &HelloApplication::guess);
    root()->addWidget(std::make_unique<Wt::WBreak>());
}

void HelloApplication::firstRow() {
    std::string inText = G00->text().toUTF8()+G01->text().toUTF8()+G02->text().toUTF8()+G03->text().toUTF8()+G04->text().toUTF8();
    std::string kw = keyword->text().toUTF8();
    std::transform(inText.begin(), inText.end(), inText.begin(), ::tolower);
    std::transform(kw.begin(), kw.end(), kw.begin(), ::tolower);
    std::string outText = "";
    for (int i = 0; i < inText.size(); i++) {
        if (inText[i] == kw[i]) {
            outText.push_back('O');
        }
        else{
            if (kw.find(inText[i]) != std::string::npos) {
                outText.push_back('V');
            }
            else {
                outText.push_back('X');
            }
        }
    }
    firstResult ->setText(outText);
    //firstResult ->setText("1st row: " +G00->text()+G01->text()+G02->text()+G03->text()+G04->text());
}
void HelloApplication::secondRow() {
    std::string inText = G10->text().toUTF8()+G11->text().toUTF8()+G12->text().toUTF8()+G13->text().toUTF8()+G14->text().toUTF8();
    std::string kw = keyword->text().toUTF8();
    std::transform(inText.begin(), inText.end(), inText.begin(), ::tolower);
    std::transform(kw.begin(), kw.end(), kw.begin(), ::tolower);

    std::string outText = "";
    for (int i = 0; i < inText.size(); i++) {
        if (inText[i] == kw[i]) {
            outText.push_back('O');
        }
        else{
            if (kw.find(inText[i]) != std::string::npos) {
                outText.push_back('V');
            }
            else {
                outText.push_back('X');
            }
        }
    }
    secondResult ->setText(outText);
    //secondResult ->setText("2nd row: " +G10->text()+G11->text()+G12->text()+G13->text()+G14->text());
}
void HelloApplication::thirdRow() {
    std::string inText = G20->text().toUTF8()+G21->text().toUTF8()+G22->text().toUTF8()+G23->text().toUTF8()+G24->text().toUTF8();
    std::string kw = keyword->text().toUTF8();
    std::transform(inText.begin(), inText.end(), inText.begin(), ::tolower);
    std::transform(kw.begin(), kw.end(), kw.begin(), ::tolower);

    std::string outText = "";
    for (int i = 0; i < inText.size(); i++) {
        if (inText[i] == kw[i]) {
            outText.push_back('O');
        }
        else{
            if (kw.find(inText[i]) != std::string::npos) {
                outText.push_back('V');
            }
            else {
                outText.push_back('X');
            }
        }
    }
    thirdResult ->setText(outText);
    //thirdResult ->setText("3rd row: " +G20->text()+G21->text()+G22->text()+G23->text()+G24->text());
}
void HelloApplication::fourthRow() {
    std::string inText = G30->text().toUTF8()+G31->text().toUTF8()+G32->text().toUTF8()+G33->text().toUTF8()+G34->text().toUTF8();
    std::string kw = keyword->text().toUTF8();
    std::transform(inText.begin(), inText.end(), inText.begin(), ::tolower);
    std::transform(kw.begin(), kw.end(), kw.begin(), ::tolower);

    std::string outText = "";
    for (int i = 0; i < inText.size(); i++) {
        if (inText[i] == kw[i]) {
            outText.push_back('O');
        }
        else{
            if (kw.find(inText[i]) != std::string::npos) {
                outText.push_back('V');
            }
            else {
                outText.push_back('X');
            }
        }
    }
    fourthResult ->setText(outText);
    //fourthResult ->setText("4th row: " +G30->text()+G31->text()+G32->text()+G33->text()+G34->text());
}
void HelloApplication::fifthRow() {
    std::string inText = G40->text().toUTF8()+G41->text().toUTF8()+G42->text().toUTF8()+G43->text().toUTF8()+G44->text().toUTF8();
    std::string kw = keyword->text().toUTF8();
    std::transform(inText.begin(), inText.end(), inText.begin(), ::tolower);
    std::transform(kw.begin(), kw.end(), kw.begin(), ::tolower);

    std::string outText = "";
    for (int i = 0; i < inText.size(); i++) {
        if (inText[i] == kw[i]) {
            outText.push_back('O');
        }
        else{
            if (kw.find(inText[i]) != std::string::npos) {
                outText.push_back('V');
            }
            else {
                outText.push_back('X');
            }
        }
    }
    fifthResult ->setText(outText);
    //fifthResult ->setText("5th row: " +G40->text()+G41->text()+G42->text()+G43->text()+G44->text());
}

void HelloApplication::getKeyword() {
    std::string filename = "wordList.txt"; // Replace with the name of your text file
    // Open the file
    std::ifstream file(filename);
    std::list<std::string> wordList;
    std::random_device rd;
    std::mt19937 generator(rd()); // Mersenne Twister pseudo-random number generator
    // Create a distribution to generate random numbers within the specified range
    std::uniform_int_distribution<int> distribution(0, 100);
    // Generate a random number
    int randomNum = distribution(generator);
    if (!file) {
        std::cerr << "Failed to open the file: " << filename << std::endl;
        //return 1;
    }
    std::string line;
    // Read and print each line from the file
    while (std::getline(file, line)) {
        wordList.push_back(line);
    }
    // Close the file
    file.close();
    // Get the length of the list
    std::size_t length = wordList.size();
    // Convert the length to an int (be cautious about potential overflow)
    int len = static_cast<int>(length);
    int randomPosition = randomNum%len;
    //string item = "";
    int position = 0;
    for(const std::string& item:wordList){
        if(position == randomPosition){
            keyword ->setText(item);
        }
        position++;
    }
}
void HelloApplication::guess(){
    if((guessWord->text().toUTF8()!="")&&(guessWord->text().toUTF8()==keyword->text().toUTF8())){
        result->setText("You guessed correctly! Play again?");
    }
    else{
        result->setText("You are out of guesses! Play again?");
    }
}


