#include <stdio.h>

int main() {
	int input;
	char* single_digits[11] = {"","one", "two ", "three ", "four", "five ", "six", "seven", "eight", "nine","ten"};
	char* two_digits[10] = {"ten", "eleven", "twelve", "thirteen",  "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
	char* tens_multiple[10] = { "","", "twenty ", "thirty", "forty", "fifty", "sixty",  "seventy", "eighty","ninety" };
	printf("Please enter a value(1 - 999, 0 to quit) : ");
	scanf("%d", &input);
	while (input != 0) {
		printf("%d", input);
		printf("\n");
		if (input < 11) {
			printf(single_digits[input]);
			printf("\n");
		}
		else if (input > 9 && input < 20) {
			printf(two_digits[input - 9]);
			printf("\n");
		}
		else if (input > 19 && input < 100) {
			printf(tens_multiple[input / 10]);
			printf("-");
			printf(single_digits[input % 10]);
			printf("\n");
		}
		else if (input > 99 && input < 1000) {
			printf(single_digits[input / 100]);
			printf(" hundred ");
			printf(tens_multiple[(input % 100) / 10]);
			printf("-");
			printf(single_digits[input % 10]);
			printf("\n");
		}
		printf("Please enter a value(1 - 999, 0 to quit) : ");
		scanf("%d", &input);
	}

	return 0;
}