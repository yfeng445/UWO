#include <stdio.h>

int main() {
    char a,b,c;
    printf("Enter a three-digit number:");
    scanf("%c%c%c", &a,&b,&c);
    printf("The reversal is:%c%c%c", c,b,a);
    return 0;
}
