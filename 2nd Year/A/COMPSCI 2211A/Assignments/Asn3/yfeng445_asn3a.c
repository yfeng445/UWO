#include <stdio.h>
/* CS2211a 2020 */
/* Assignment 03 */
/* Yulun Feng */
/* 251113989 */
/* yfeng445 */
/* Oct 16, 2020 */

int main() {

	int Array[] = { 12,63,44,89,3,55,73,27,37,18,518,258,158,78,25 };
	int A[] = { 12,63,44,89,3,55,73,27,37,18,518,258,158,78,25 };
	printf("Size of array:%d%s\n", sizeof(Array)," bytes");
	printf("Length of array:%d%s\n", sizeof(Array)/sizeof(int)," elements");
	printf("\n");

	/*Part 1*/
	printf("PART 1:\n");
	printf("The values store into the array are: \n");
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {
		printf("%d     ",Array[i]);
	}
	printf("\n");
	printf("\n");

	/*Part 2*/
	printf("PART 2: \n");
	printf("The values store into the array in reverse are: \n");
	for (int i = sizeof(Array) / sizeof(int) - 1; i >-1 ; i--) {
		printf("%d     ", Array[i]);
	}
	printf("\n");
	printf("\n");

	/*Part 3*/
	printf("PART 3\n");
	int min = Array[0];
	int Pos = 0;
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {
		if (Array[i] < min) {
			min = Array[i];
			Pos = i + 1;
		}
	}
	printf("The smallest value stored in the array is :\nvalue: %d",min);
	printf(" at the ");
	if (Pos == 1){
		printf(" 1st");
	}
	if (Pos == 2) {
		printf(" 2nd");
	}
	if (Pos == 3) {
		printf(" 3rd");
	}
	else {
		printf("%d",Pos);
		printf("th");
	}
	printf(" position from the left\n");
	printf("\n");

	/*Part 4*/
	printf("PART 4\n");
	printf("The sum (total) value of the array is: \n");
	int sum = 0;
	for (int i = 0; i < sizeof(Array) / sizeof(int)-1; i++) {
		printf("%d",Array[i]);
		printf(" + ");
		sum += Array[i];
	}
	printf("%d",Array[sizeof(Array) / sizeof(int) - 1]);
	printf("     ");
	sum += Array[sizeof(Array) / sizeof(int) - 1];
	printf("equals: %d\n", sum);
	printf("\n");

	/*Part 5*/
	printf("PART 5\n");
	printf("Copy the array into a new array, but in reverse order :\n");
	printf("Original array \n");
	for (int i = 0; i < sizeof(Array) / sizeof(int) ; i++) {
		printf("%d", Array[i]);
		printf("   ");
	}
	printf("\n");
	printf("New (Reversed) Array :\n");
	int ReverseArray[sizeof(Array) / sizeof(int)];
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {
		ReverseArray[sizeof(Array) / sizeof(int) -1 -i] = Array[i];
	}
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {
		printf("%d",ReverseArray[i]);
		printf("   ");
	}
	printf("\n");
	printf("\n");

	/*Part 6*/
	printf("PART 6\n");
	printf("Switch the first value in the array with the last value in the array\n");
	printf("Original array: \n");
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {
		printf("%d", Array[i]);
		printf("   ");
	}
	printf("\n");
	int tmp = Array[0];
	Array[0] = Array[sizeof(Array) / sizeof(int) - 1];
	Array[sizeof(Array) / sizeof(int) - 1] = tmp;
	printf("Changed array:\n");
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {
		printf("%d", Array[i]);
		printf("   ");
	}
	printf("\n");
	printf("\n");

	/*Part 7*/
	printf("PART 7 \n");
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {
		Array[i] = A[i];
	}
	printf("Sort the array in ascending order: \n");
	printf("Original Array: \n");
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {
		printf("%d", Array[i]);
		printf("   ");
	}
	printf("\n");
	printf("Changed array: \n");
	int temp;
	for (int i = 0; i < sizeof(Array) / sizeof(int)-1; i++) {
		for (int j = 0; j < sizeof(Array) / sizeof(int) - 1 - i; j++) {
			if (Array[j] > Array[j + 1]) {
				temp = Array[j+1];
				Array[j+1] = Array[j];
				Array[j] = temp;
			}
		}
	}
	for (int i = 0; i < sizeof(Array) / sizeof(int); i++) {

		printf("%d", Array[i]);
		printf("   ");
	}
	printf("\n");
	printf("\n");

}