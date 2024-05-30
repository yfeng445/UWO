#include <stdio.h>

int main()
{
    char str[50];

    printf("enter something:");
    gets(str);

    printf("what you entered is:%s", str);

    return(0);
}
