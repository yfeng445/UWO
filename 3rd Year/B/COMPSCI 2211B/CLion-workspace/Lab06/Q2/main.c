#include <stdio.h>

#define Jan 31
#define Mar 31
#define Apr 30
#define May 31
#define Jun 30
#define Jul 31
#define Aug 31
#define Sep 30
#define Oct 31
#define Nov 30
#define Dec 31

int day_of_year(int month, int day, int year){
    int return_day = 0;
    int Feb;
    if(year%400==0||(year%4==0&&!year%100==0)) Feb = 29;
    else Feb = 28;
    if(month==1) return_day = day;
    else if(month==2) return_day = Jan+day;
    else if(month==3) return_day = Jan+Feb+day;
    else if(month==4) return_day = Jan+Feb+Mar+day;
    else if(month==5) return_day = Jan+Feb+Mar+Apr+day;
    else if(month==6) return_day = Jan+Feb+Mar+Apr+May+day;
    else if(month==7) return_day = Jan+Feb+Mar+Apr+May+Jun+day;
    else if(month==8) return_day = Jan+Feb+Mar+Apr+May+Jun+Jul+day;
    else if(month==9) return_day = Jan+Feb+Mar+Apr+May+Jun+Jul+Aug+day;
    else if(month==10) return_day = Jan+Feb+Mar+Apr+May+Jun+Jul+Aug+Sep+day;
    else if(month==11) return_day = Jan+Feb+Mar+Apr+May+Jun+Jul+Aug+Sep+Oct+day;
    else if(month==12) return_day = Jan+Feb+Mar+Apr+May+Jun+Jul+Aug+Sep+Oct+Nov+day;
    else printf("invalid input.");
    return return_day;
}

int main() {
    int day, month, year;
    printf("Enter the day(MM/DD/YYYY):");
    scanf("%d/%d/%d", &month, &day, &year);
    printf("Day of year: %d", day_of_year(month, day, year));
    return 0;
}


