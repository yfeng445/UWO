/* CS2211a 2020 */
/* Assignment 04 */
/* Yulun Feng */
/* 251113989 */
/* yfeng445 */
/* Nov 13,2020 */
#include <stdio.h>
#include <malloc.h>

void inputFunction() {

	int length = 20;
	char* words;
	words = (char*)calloc(20, sizeof(char));
	
	char input[1000] = "0";

	//input words into the dynamic memory
	printf("Enter a string: ");
	gets(input);
	if (!(strcmp(input, ""))) {
		return 0;
	}
	else {
		words = (char*)realloc(words, length + sizeof(input));
		strcat(words, input);
	}

	do {
		printf("Enter a string: ");
		gets(input);
		words = (char*)realloc(words, length+sizeof(input));
		strcat(words, " ");
		strcat(words, input);
	} while (strcmp(input, ""));

	return(words);
}
