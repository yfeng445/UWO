/* CS2211a 2020 */
/* Lab 04 */
/* Prof Magguilli */
/* your student number */
/* your UWO User Name */
/* September 30, 2020 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void stringFunc();
int readString();
int readFullString();
int fputsFunc();
int putsFunc();
int atoiFunc();
void variablePromotion();
void sizeOfFunction();

int main () {

//   readString();
//   readFullString();
//   fputsFunc();
//   putsFunc();
//   stringFunc();
//   atoiFunc();
//   variablePromotion();
//   sizeOfFunction();

  return 0;

}

int readString() {

   char name[10];
   int age;
   printf("Enter your first name and age: \n");
   scanf("%s %d", name, &age);
   printf("You entered: %s %d",name,age);
   return 0;
}

int readFullString() {
   char full_name[25];
   printf("Enter your full name: ");
   gets(full_name);
   printf("My full name is %s ",full_name);
   return 0;
}

int fputsFunc(){
  char town[40];
  printf("Enter the city/town you were born in: ");
  gets(town);
  fputs(town, stdout);
  return 0;
}

int putsFunc() {
   char name[15];
   gets(name);        //reads a string
   puts(name);        //displays a string
   return 0;
}


void stringFunc(){

//string initialization
char string1[15]="Hello";
char string2[15]=" World!";
char string3[15];
int val;

//string comparison
val= strcmp(string1,string2);
if(val==0){
    printf("Strings are equal\n");
}
else{
    printf("Strings are not equal\n");
}

//string concatenation
printf("Concatenated string:%s",strcat(string1,string2)); //string1 contains hello world!

//string length
printf("\nLength of first string:%u",strlen(string1));
printf("\nLength of second string:%u",strlen(string2));

//string copy
printf("\nCopied string is:%s\n",strcpy(string3,string1));  //string1 is copied into string3
}

int atoiFunc()  {
  char string_id[10];
  int ID;
  printf("Enter a number: ");
  gets(string_id);
  ID = atoi(string_id);
  printf("you enter %d  ",ID);
  return 0;
}

void variablePromotion() {

    char c = 'k';
	short int s = 37;
	int i = 485;
	unsigned int u = 250;
	long int l = 293995857;
	unsigned long  ul = 8464643;
	float f = 7.14;
	double d = 2.000102928377474;
	long double ld = 0.0000434;

	i = i + c;     // c is converted to int
	i = i + s;     // s is converted to int
	u = u + i;     // i is converted to unsigned int
	l = l + u;     // u is converted to long int
	ul = ul + l;   // l is converted to unsigned long int
	f = f + ul;    // ul is converted to float
	d = d + f ;     // f is converted to double
	ld = ld + d;   // d is converted to long double

	printf("i is ?? \n",i);
	printf("u is ?? \n",u);
	printf("l is ?? \n",l);
	printf("ul is ?? \n",ul);
	printf("f is ?? \n",f);
	printf("d is ?? \n",d);
	printf("ld is ?? \n",ld);

}


void sizeOfFunction() {
    printf("The size of char in bytes is: %u.", sizeof(char));
    printf("\nThe size of short in bytes is: %u.", sizeof(short));
    printf("\nThe size of int in bytes is: %u.", sizeof(int));
    printf("\nThe size of long in bytes is: %u.", sizeof(long));
    printf("\nThe size of long long in bytes is: %u.", sizeof(long long));
    printf("\nThe size of float in bytes is: %u.", sizeof(float));
    printf("\nThe size of double in bytes is: %u.", sizeof(double));
    printf("\nThe size of long double in bytes is: %u\n\n", sizeof(long double));
}
