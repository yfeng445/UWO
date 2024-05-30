#include <stdio.h>

int main() {
	int option;
	char input[1];
	float value;
	while (1) {
		printf("Enter the type of conversion: ");
		scanf("%d", &option);
		if (option == 1) {
			printf("Kilometre and Mile\n");
			printf("Enter the direction of the conversion: ");
			scanf("%s", &input);
			if ((int)input[0] == 75) {
				printf("Please enter the value: ");
				scanf("%f", &value);
				printf("Your conversion is: %f\n", value * 0.621371);
			}
			else if ((int)input[0] == 77) {
				printf("Please enter the value: ");
				scanf("%f", &value);
				printf("Your conversion is: %f\n", value / 0.621371);
			}
			else {
				printf("Invalid input, please try again.");
			}
		}
		else if (option == 2) {
			printf("Litres and Gallons\n");
			printf("Enter the direction of the conversion: ");
			scanf("%s", &input);
			if ((int)input[0] == 76) {
				printf("Please enter the value: ");
				scanf("%f", &value);
				printf("Your conversion is: %f\n", value * 0.264172);
			}
			else if ((int)input[0] == 71) {
				printf("Please enter the value: ");
				scanf("%f", &value);
				printf("Your conversion is: %f\n", value / 0.264172);
			}
			else {
				printf("Invalid input, please try again.");
			}
		}
		else if (option == 3) {
			printf("Hectares and Acres\n");
			printf("Enter the direction of the conversion: ");
			scanf("%s", &input);
			if ((int)input[0] == 72) {
				printf("Please enter the value: ");
				scanf("%f", &value);
				printf("Your conversion is: %f\n", value * 2.47105);
			}
			else if ((int)input[0] == 65) {
				printf("Please enter the value: ");
				scanf("%f", &value);
				printf("Your conversion is: %f\n", value / 2.47105);
			}
			else {
				printf("Invalid input, please try again.");
			}
		}
		else if (option == 4) {
			printf("Kilograms and Pounds\n");
			printf("Enter the direction of the conversion: ");
			scanf("%s", &input);
			if ((int)input[0] == 75) {
				printf("Please enter the value: ");
				scanf("%f", &value);
				printf("Your conversion is: %f\n", value * 2.20462);
			}
			else if ((int)input[0] == 80) {
				printf("Please enter the value: ");
				scanf("%f", &value);
				printf("Your conversion is: %f\n", value / 2.20462);
			}
			else {
				printf("Invalid input, please try again.");
			}
		}
		else if (option == 5) {
			break;
		}
		else {
			printf("Invalid input, please enter another number.");
		}
	}
	return 0;
}