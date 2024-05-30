/* Reverses a series of numbers */

#include <stdio.h>

int main(void)
{
    int a[10], i;

    printf("Enter %d numbers: ", sizeof(a)/sizeof(a[0]));
    for (i = 0; i < sizeof(a)/sizeof(a[0]); i++)
        scanf("%d", &a[i]);

    printf("In reverse order:");
    for (i = sizeof(a)/sizeof(a[0]) - 1; i >= 0; i--)
        printf(" %d", a[i]);
    printf("\n");

    return 0;
}