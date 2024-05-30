#include <stdio.h>

int main() {
    int a, b, c,d,numerator,denominator;
    printf("Enter two fractions separated by a plus sign:");
    scanf("%d/%d+%d/%d",&a,&b,&c,&d);
    numerator = a*d+b*c;
    denominator = b*d;
    for(int i = 2;i<numerator+1; i++ ){
        if(numerator%i==0&&denominator%i==0){
            numerator = numerator/i;
            denominator = denominator/i;
        }
    }
    printf("The sum is: %d", numerator/denominator);
    return 0;
}
