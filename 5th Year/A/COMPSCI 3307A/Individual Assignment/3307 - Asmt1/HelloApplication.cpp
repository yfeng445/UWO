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
#include <Wt/WMessageBox.h>
#include <Wt/WTimer.h>
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
#include <Wt/WCssDecorationStyle.h>
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

    //Wt::WTimer::singleShot(2000, this, &HelloApplication::getKeyword);
    //Wt::WCssDecorationStyle CSS;
    //CSS.setRgb(255,0,0,0);

    // Before the inputs...
    root()->addWidget(std::make_unique<Wt::WText>("This is a SIMPLE wordle gameboard")); // show some text
    root()->addWidget(std::make_unique<Wt::WBreak>());
    root()->addWidget(std::make_unique<Wt::WBreak>());

    auto getKey = root()->addWidget(std::make_unique<Wt::WPushButton>("Click here to get a keyword and start:"));
    getKey->setMargin(0, Wt::Side::Left);
    getKey->clicked().connect(this, &HelloApplication::getKeyword);
    keyword = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());

    // Gird sections //
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G00 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G00->setMaxLength(1);
    G01 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G01->setMaxLength(1);
    G02 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G02->setMaxLength(1);
    G03 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G03->setMaxLength(1);
    G04 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G04->setMaxLength(1);
    auto firstButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 1st row"));
    firstButton->setMargin(10, Wt::Side::Left);
    firstButton->clicked().connect(this, &HelloApplication::firstRow);
    firstResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G10 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G10->setMaxLength(1);
    G11 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G11->setMaxLength(1);
    G12 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G12->setMaxLength(1);
    G13 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G13->setMaxLength(1);
    G14 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G14->setMaxLength(1);
    auto secondButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 2nd row"));
    secondButton->setMargin(10, Wt::Side::Left);
    secondButton->clicked().connect(this, &HelloApplication::secondRow);
    secondResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G20 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G20->setMaxLength(1);
    G21 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G21->setMaxLength(1);
    G22 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G22->setMaxLength(1);
    G23 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G23->setMaxLength(1);
    G24 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G24->setMaxLength(1);
    auto thirdButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 3rd row"));
    thirdButton->setMargin(10, Wt::Side::Left);
    thirdButton->clicked().connect(this, &HelloApplication::thirdRow);
    thirdResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G30 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G30->setMaxLength(1);
    G31 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G31->setMaxLength(1);
    G32 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G32->setMaxLength(1);
    G33 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G33->setMaxLength(1);
    G34 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G34->setMaxLength(1);
    auto fourthButton = root()->addWidget(std::make_unique<Wt::WPushButton>("check 4th row"));
    fourthButton->setMargin(10, Wt::Side::Left);
    fourthButton->clicked().connect(this, &HelloApplication::fourthRow);
    fourthResult = root()->addWidget(std::make_unique<Wt::WText>());
    root()->addWidget(std::make_unique<Wt::WBreak>());
    G40 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G40->setMaxLength(1);
    G41 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G41->setMaxLength(1);
    G42 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G42->setMaxLength(1);
    G43 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G43->setMaxLength(1);
    G44 = root()->addWidget(std::make_unique<Wt::WLineEdit>());
    G44->setMaxLength(1);
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
    //restart =
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
    G00->isReadOnly();
    G00->setEnabled(false);
    G01->isReadOnly();
    G01->setEnabled(false);
    G02->isReadOnly();
    G02->setEnabled(false);
    G03->isReadOnly();
    G03->setEnabled(false);
    G04->isReadOnly();
    G04->setEnabled(false);
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
    G10->isReadOnly();
    G10->setEnabled(false);
    G11->isReadOnly();
    G11->setEnabled(false);
    G12->isReadOnly();
    G12->setEnabled(false);
    G13->isReadOnly();
    G13->setEnabled(false);
    G14->isReadOnly();
    G14->setEnabled(false);
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
    G20->isReadOnly();
    G20->setEnabled(false);
    G21->isReadOnly();
    G21->setEnabled(false);
    G22->isReadOnly();
    G22->setEnabled(false);
    G23->isReadOnly();
    G23->setEnabled(false);
    G24->isReadOnly();
    G24->setEnabled(false);
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
    G30->isReadOnly();
    G30->setEnabled(false);
    G31->isReadOnly();
    G31->setEnabled(false);
    G32->isReadOnly();
    G32->setEnabled(false);
    G33->isReadOnly();
    G33->setEnabled(false);
    G34->isReadOnly();
    G34->setEnabled(false);
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
    G40->isReadOnly();
    G40->setEnabled(false);
    G41->isReadOnly();
    G41->setEnabled(false);
    G42->isReadOnly();
    G42->setEnabled(false);
    G43->isReadOnly();
    G43->setEnabled(false);
    G44->isReadOnly();
    G44->setEnabled(false);
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
void HelloApplication::reStart(){

}


