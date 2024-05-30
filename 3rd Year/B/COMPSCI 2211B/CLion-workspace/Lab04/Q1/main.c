#include <stdio.h>

int main() {
    int hour, min;
    printf("Enter a 24-hour time:");
    scanf("%d:%d", &hour, &min);
//    printf("%d",min);
    if(hour>24||hour<0){printf("Invalid input.");}
    else if(hour>12){printf("Equivalent 12-hour time: %d:%d PM.", hour-12, min);}
    else if(hour==12){printf("Equivalent 12-hour time: %d:%d PM.", hour, min);}
    else{printf("Equivalent 12-hour time: %d:%d AM.", hour, min);}
    return 0;
}
