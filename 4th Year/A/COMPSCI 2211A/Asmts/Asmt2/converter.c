#include <stdio.h>

int main() {
    int option = 0;
    float input = 0, result = 0;
    char choice = "";
    while(option!=5){
        printf("Please enter the conversion type(5 to quit):");
        scanf("%d", &option);
        fflush(stdin);// clean the input cache
        if(option == 1){ // case 1
            printf("Conversion between Kilograms and Pounds (1 kilogram == 2.20462 pounds)\n");
            printf("Please enter your choice:");
            scanf("%c", &choice);
            if(choice=='K'){
                printf("Kilogram to Pound conversion.\n");
                printf("Please enter the value:");
                scanf("%f", &input);
                result = input*2.20462;
                printf("%f Kilograms is %f Pounds.\n", input, result);
            }
            else if(choice=='P'){
                printf("Pound to Kilogram conversion.\n");
                printf("Please enter the value:");
                scanf("%f", &input);
                result = input/2.20462;
                printf("%f Pounds is %f Kilograms.\n", input, result);
            }
            else{
                printf("Invalid input.\n");
            }
        }
        else if(option == 2){ //case 2
            printf("Conversion between Hectares and Acres (1 hectare == 2.47105 acres)\n");
            printf("Please enter your choice:");
            scanf("%c", &choice);
            if(choice=='H'){
                printf("Hectares to Acres conversion.\n");
                printf("Please enter the value:");
                scanf("%f", &input);
                result = input*2.47105;
                printf("%f Hectares is %f Acres.\n", input, result);
            }
            else if(choice=='A'){
                printf("Acres to Hectares conversion.\n");
                printf("Please enter the value:");
                scanf("%f", &input);
                result = input/2.47105;
                printf("%f Acres is %f Hectares.\n", input, result);
            }
            else{
                printf("Invalid input.\n");
            }
        }
        else if(option == 3){ //case 3
            printf("Conversion between Litres and Gallons (1 litre == 0.264172 gallons)\n");
            printf("Please enter your choice:");
            scanf("%c", &choice);
            if(choice=='L'){
                printf("Litres to Gallons conversion.\n");
                printf("Please enter the value:");
                scanf("%f", &input);
                result = input*0.264172;
                printf("%f Litres is %f Gallon.\n", input, result);
            }
            else if(choice=='G'){
                printf("Gallon to Litres conversion.\n");
                printf("Please enter the value:");
                scanf("%f", &input);
                result = input/0.264172;
                printf("%f Gallon is %f Litres.\n", input, result);
            }
            else{
                printf("Invalid input.\n");
            }
        }
        else if(option == 4){ //case 4
            printf("Conversion between Kilometre and Mile (1 kilometre == 0.621371 miles)\n");
            printf("Please enter your choice:");
            scanf("%c", &choice);
            if(choice=='K'){
                printf("Kilometers to Miles conversion.\n");
                printf("Please enter the value:");
                scanf("%f", &input);
                result = input*0.621371;
                printf("%f Kilometers is %f Miles.\n", input, result);
            }
            else if(choice=='M'){
                printf("Miles to Kilometers conversion.\n");
                printf("Please enter the value:");
                scanf("%f", &input);
                result = input/0.621371;
                printf("%f Mile is %f Kilometers.\n", input, result);
            }
            else{
                printf("Invalid input.");
            }
        }
        else if(option == 5){} //exit
        else{
            printf("Invalid input, press any key to continue.\n");//invalid input, do nothing
        }
    }
    return 0;
}
