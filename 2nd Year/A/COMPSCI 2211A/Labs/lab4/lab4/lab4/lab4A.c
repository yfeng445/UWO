/* CS2211a 2020 */
/* Lab 04 Part 1 */
/* Yulun Feng */
/* Oct 11, 2020*/

#include <stdio.h>
#include <stdlib.h>


int main() {

	char first_name[15] = { 'A','N','T','H','O','N','Y','\O' }; //NULL Character is required
	char last_name[15] = "ADVERSE";
	char string1[6] = "hello";
	//char string1[5] = "hello";	//string size 5 BUT array size must be 6 (why?)

	//char string1[6];
	char string2[] = "world!";
	char string3[6] = { 'h','e','l','l','o','\o' };
	//char string3[6] = { 'h','e','l','l','o','!' };	//what is wrong with this statement?

	printf("%s\n", first_name);
	puts(last_name);
	printf("%s:%s:%s\n", string1, string2, string3);

	return 0;
}
