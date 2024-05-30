/*
 * Copyright (C) 2008 Emweb bv, Herent, Belgium.
 *
 * See the LICENSE file for terms of use.
 */

#include <Wt/WApplication.h>
#include <Wt/WBreak.h>
#include <Wt/WContainerWidget.h>
#include <Wt/WLineEdit.h>
#include <Wt/WPushButton.h>
#include <Wt/WText.h>

/*
 * A simple hello world application class which demonstrates how to react
 * to events, read input, and give feed-back.
 */
class HelloApplication : public Wt::WApplication
{
public:
  HelloApplication(const Wt::WEnvironment& env);

private:
    Wt::WText *firstResult;
    Wt::WText *secondResult;
    Wt::WText *thirdResult;
    Wt::WText *fourthResult;
    Wt::WText *fifthResult;
    Wt::WText *keyword;
    Wt::WLineEdit *guessWord;
    Wt::WText *result;
    Wt::WPushButton *submit;
    Wt::WPushButton *restart;
    Wt::WPushButton *quitApp;




    // Input Grids  1st,  2nd,  3rd,  4th,  5th
    Wt::WLineEdit  *G00, *G01, *G02, *G03, *G04; // 1st row
    Wt::WLineEdit  *G10, *G11, *G12, *G13, *G14; // 2nd row
    Wt::WLineEdit  *G20, *G21, *G22, *G23, *G24; // 3rd row
    Wt::WLineEdit  *G30, *G31, *G32, *G33, *G34; // 4th row
    Wt::WLineEdit  *G40, *G41, *G42, *G43, *G44; // 5th row

    // Output Grids 1st,  2nd,  3rd,  4th,  5th
    Wt::WText  *M00, *M01, *M02, *M03, *M04; // 1st row
    Wt::WText  *M10, *M11, *M12, *M13, *M14; // 2nd row
    Wt::WText  *M20, *M21, *M22, *M23, *M24; // 3rd row
    Wt::WText  *M30, *M31, *M32, *M33, *M34; // 4th row
    Wt::WText  *M40, *M41, *M42, *M43, *M44; // 5th row

    // Functions
    void firstRow();
    void secondRow();
    void thirdRow();
    void fourthRow();
    void fifthRow();
    void getKeyword();
    void buttonFunc();
    void guess();
    void check();
    void reStart();
    void setRed();
    void setGreen();
};
