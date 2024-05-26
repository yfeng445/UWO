#include <stdio.h>
#include <math.h>
int an();

int main() {


	float base;
	int exp;

	printf("Enter the base number: ");
	scanf_s("\n%f", &base);
	printf("Enter the exponent number: ");
	scanf_s("\n%f", &exp);

	if (base == 0) {
		return 0;
	}
	else if (exp < 0) {
		return 0;
	}
	else {
		printf("%f", base,"\n");
		printf("%f",exp,"\n");
		printf("%lf", pow(base, exp));
		printf("%lf", an(base, exp));
		return 0;
	}

}

int an(float base, int exp) {
	float a = base;
	int n = exp;

	if (!(n > 0)) {
		return pow(a, n);
		/**/
	}
	else {
		if (n % 2 == 0) {
			return pow(pow(a, n / 2), 2);
			/* */
		}
		else {
			return pow(pow(a, (n - 1) / 2), 2) * a;
			/* */
		}
	}
}