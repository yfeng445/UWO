#include <stdio.h>
/* CS2211a 2020 */
/* Assignment 03 */
/* Yulun Feng */
/* 251113989 */
/* yfeng445 */
/* Oct 16, 2020 */

/*sum of primes smaller than the given number*/
int main()
{
	int sum, base, num, find;

	while (1) {
		sum = 0;
		printf("Input? ");
		scanf_s("%d", &num);
		if (num == 0) break;	//exit the  program

		for (int i = 1; i <= num; i++) {	//go through all numbers smaller than the given number
			find = 0;
			for (base = 2; base <= i; base++) {		//find a base 
				if (i % base == 0) find++;
			}
			if (find == 1) sum = sum + i;	//add up bases
		}
		printf("sum of primes smaller than the given number: %d\n", sum);

	}
	return 0;
}