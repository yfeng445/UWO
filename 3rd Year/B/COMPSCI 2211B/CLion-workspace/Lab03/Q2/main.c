#include<stdio.h>

int main(){
    int dayNumber, dayStarting;
    printf("Enter number of days in month:");
    scanf("%d", &dayNumber);
    printf("Enter starting day of the week (1=Sun, 7=Sat):");
    scanf("%d", &dayStarting);
    int weekCounter = 0;
    for(int i = 0; i<dayStarting-1; i++){
        printf("   ");
        weekCounter++;
    }
    for(int i = 1; i<dayNumber+1; i++){
        if(i<10){
            printf(" %d ", i);
        }
        else{
            printf("%d ", i);
        }
        weekCounter++;
        if(weekCounter%7 == 0){
            printf("\b\n");
        }
    }

    return 0;
}
