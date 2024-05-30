#include <stdio.h>
#include <math.h>

int main() {
    int INT_MAX = pow(2, 32)-1;
    int x;
    printf("Enter a number x:");
    scanf("%d",&x);
    int output = 3*pow(x, 5)+ 2*pow(x, 4)-5*pow(x, 3)-pow(x, 2)+ 7*x-6;
    if(output==-2147483648) printf("Overflow");
    else printf("the result is: %d", output);
}
