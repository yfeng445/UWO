//
// Created by allen on 10/1/23.
//

#include "test.h"
#include <iostream>
#include <fstream>
#include <string>
//
// Created by allen on 10/1/23.
//

#include "test.h"
#include <iostream>
#include <fstream>
#include <string>
using namespace std;
#include <iostream>
#include <fstream>
#include <string>
#include <list>
#include <random>
#include <algorithm>

string compareStrings(string A, string B) {
    if (A.size() != B.size()) {
        throw runtime_error("Strings must be of equal length!");
    }

    std::transform(A.begin(), A.end(), A.begin(), ::tolower);
    std::transform(B.begin(), B.end(), B.begin(), ::tolower);

    string result;
    for (int i = 0; i < A.size(); i++) {
        if (A[i] == B[i]) {
            result.push_back('O');
        }
        else{
            if (B.find(A[i]) != string::npos) {
                result.push_back('V');
            }
            else {
                result.push_back('X');
            }
        }
    }
    return result;
}

int main() {
    std::string inText = "abcde";
    //std::string kw = "abbdx"; // 00X0X
    std::string kw = "ABCDF"; // 00X0X
    std::string outText = compareStrings(inText, kw);
    cout<<outText<<endl;

}


