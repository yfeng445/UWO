#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    int input = 1;
    char *hundreds[10] = {"", "one hundred","two hundred", "three hundred","four hundred", "five hundred", "six hundred", "seven hundred", "eight hundred", "nine hundred"};
    char *tens[10] = {"","","twenty","thirty", "forty","fifty", "sixty", "seventy", "eighty", "ninety"};
    char *ones[20] = {"","one", "two", "three", "four", "five","six", "seven", "eight", "nine", "ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    char *connectors[2] = {"-", " and "};
    while(input!=0){ //loop until exit
        char *temp = (char *)calloc(50, sizeof(char)); //temp would be the output string
        printf("Please enter a input (1-999, 0 to quit):");
        scanf("%d", &input);
        strcat(temp, hundreds[input/100]); //put hundreds to the string
        if(input>99&&input%100!=0) strcat(temp, connectors[1]); // add connectors
        if(input%100<20){
            strcat(temp, ones[input%100]); //add 1-19
        }
        else{
            strcat(temp, tens[(input%100)/10]); //add tens
            if(input%10!=0) strcat(temp, connectors[0]); //add connectors if needed
            strcat(temp, ones[input%10]); // add ones
        }
        printf("%s\n", temp);
    }
    return 0;
}

