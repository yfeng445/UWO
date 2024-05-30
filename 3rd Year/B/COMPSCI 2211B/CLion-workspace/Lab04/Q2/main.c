#include <stdio.h>
#include <math.h>

int main() {
    int hour, min;
    int hourD[8] = {8,9,11,12,14,15,19,21};
    int minD[8] = {0,43,19,47,0,45,0,45};
    int hourA[8] = {10,11,13,15,16,17,21,23};
    int minA[8] = {16,52,31,0,8,55,20,58};
    int notQuit = 1;
    while(notQuit){
        printf("Enter a 24-hour time:");
        scanf("%d:%d", &hour, &min);
        if(hour>23||hour<0){ //Invalid
            printf("Invalid input.");
        }
        else{
            for(int i = 0; i<8; i++){
                if(hour*60+min>(12+9)*60+45){// Midnight case
                    int diffEarlier = abs(hour-hourD[7]*60+min-minD[7]);
                    int diffNext = abs(hour+24-hourD[0]*60+min-minD[0]);
                    if(diffEarlier>diffNext){
                        printf("Closest departure time is %d:%d p.m., arriving at %d:%d a.m.", hourD[7],minD[7],hourA[7],minA[7]);
                    }
                    else{
                        printf("Closest departure time is %d:%d a.m., arriving at %d:%d p.m.", hourD[0],minD[0],hourA[0],minA[0]);
                    }
                }
                else{
                    if(hour>=hourD){
                        int diffEarlier = abs(hour-hourD[i]*60+min-minD[i]);
                        int diffNext = abs(hour-hourD[i+1]*60+min-minD[i+1]);
                        if(diffNext>diffEarlier){
                            if(hour>12){printf("Closest departure time is %d:%d a.m., arriving at %d:%d p.m.", hourD[i],minD[i],hourA[i],minA[i]);}
                            else if(hour==12){printf("Closest departure time is %d:%d a.m., arriving at %d:%d p.m.", hourD[i],minD[i],hourA[i],minA[i]);}
                            else{printf("Closest departure time is %d:%d a.m., arriving at %d:%d p.m.", hourD[i],minD[i],hourA[i],minA[i]);}
                        }
                        break;
                    }
                }

            }
        }

        printf("Enter 1 to continue or 0 to quit:");
        scanf("%d", &notQuit);
    }



    if(hour>24||hour<0){printf("Invalid input.");}
    else if(hour>12){printf("Equivalent 12-hour time: %d:%d PM.", hour-12, min);}
    else if(hour==12){printf("Equivalent 12-hour time: %d:%d PM.", hour, min);}
    else{printf("Equivalent 12-hour time: %d:%d AM.", hour, min);}
    return 0;
}
