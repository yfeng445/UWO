#include <stdio.h>
int KM();
int LG();
int CI();
int CF();

int main() {

	char c;
	char d[] = { '1', '2', '3', '4', '5' };

	while (1) {
		printf("Enter the operation number: ");
		fflush(stdin);
		scanf_s("\n%c", &c);

		int find = 0;

		for (int i = 0; i < 6; i++) {
			if (c == d[i]) {
				find = 1;
			}
		}

		if (c == '1'){
			KM();
		}
		if (c == '2') {
			LG();
		}
		if (c == '3') {
			CI();
		}
		if (c == '4') {
			CF();
		}
		if (c == '5'&& find == 1) {
			return 0;
		}

	}



	return 0;
}

int KM(){

	char op[] = {'K', 'M'};
	char c;
	float n;

	while (1) {
		printf("Enter the origin unit: ");
		fflush(stdin);
		scanf_s("\n%c", &c);

		if (c == 'K') {
			printf("Enter a number: ");
			fflush(stdin);
			scanf_s(" %f", &n);
			printf("%f",n/1.6);
			printf("\n");
			return 0;
			break;
		}

		if (c == 'M') {
			printf("Enter a number: ");
			fflush(stdin);
			scanf_s(" %f", &n);
			printf("%f", n *1.6);
			printf("\n");
			return 0;
			break;
		}
	}
	return 0;
}

int LG() {

	char op[] = { 'L', 'G' };
	char c;
	float n;

	while (1) {
		printf("Enter the origin unit: ");
		fflush(stdin);
		scanf_s("\n%c", &c);

		if (c == 'L') {
			printf("Enter a number: ");
			fflush(stdin);
			scanf_s(" %f", &n);
			printf("%f", n / 4.54596);
			printf("\n");
			return 0;
			break;
		}

		if (c == 'G') {
			printf("Enter a number: ");
			fflush(stdin);
			scanf_s(" %f", &n);
			printf("%f", n * 4.54596);
			printf("\n");
			return 0;
			break;
		}
	}
	return 0;
}

int CI() {

	char op[] = { 'C', 'I' };
	char c;
	float n;

	while (1) {
		printf("Enter the origin unit: ");
		fflush(stdin);
		scanf_s("\n%c", &c);

		if (c == 'C') {
			printf("Enter a number: ");
			fflush(stdin);
			scanf_s(" %f", &n);
			printf("%f", n * 0.394);
			printf("\n");
			return 0;
			break;
		}

		if (c == 'I') {
			printf("Enter a number: ");
			fflush(stdin);
			scanf_s(" %f", &n);
			printf("%f", n / 0.394);
			printf("\n");
			return 0;
			break;
		}
	}
	return 0;
}

int CF() {

	char op[] = { 'C', 'F' };
	char c;
	float n;

	while (1) {
		printf("Enter the origin unit: ");
		fflush(stdin);
		scanf_s("\n%c", &c);

		if (c == 'C') {
			printf("Enter a number: ");
			fflush(stdin);
			scanf_s(" %f", &n);
			printf("%f", n*9/5+32);
			printf("\n");
			return 0;
			break;
		}

		if (c == 'F') {
			printf("Enter a number: ");
			fflush(stdin);
			scanf_s(" %f", &n);
			printf("%f", (n-32)*5/9);
			printf("\n");
			return 0;
			break;
		}
	}
	return 0;
}


	
	