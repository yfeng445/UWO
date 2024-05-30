#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    int input = 1;
    char *hundreds[10] = {"", "one hundred","two hundred", "three hundred","four hundred", "five hundred", "six hundred", "seven hundred", "eight hundred", "nine hundred"};
    char *tens[10] = {"","","twenty","thirty", "forty","fifty", "sixty", "seventy", "eighty", "ninety"};
    char *ones[20] = {"","one", "two", "three", "four", "five","six", "seven", "eight", "nine", "ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    char *connectors[2] = {"-", " and "};
    while(input!=0){
        char *temp = (char *)calloc(50, sizeof(char));
        printf("Please enter a input (1-999, 0 to quit):");
        scanf("%d", &input);
        strcat(temp, hundreds[input/100]);
        if(input>99&&input%100!=0) strcat(temp, connectors[1]);
        if(input%100<20){
            strcat(temp, ones[input%100]);
        }
        else{
            strcat(temp, tens[(input%100)/10]);
            if(input%10!=0) strcat(temp, connectors[0]);
            strcat(temp, ones[input%10]);
        }
        printf("%s\n", temp);
    }
    return 0;
}

