#include <stdio.h>

int sum_array(const int *p, int size)
{
    int i, sum = 0;
    for ( i = 0; i < size; i++)
        sum += *(p+i);
    return sum;
}


int main() {
    int a[] = {1,2,3,4,5};
    int *p = a;
    int size = sizeof(a)/sizeof(int);
    int sum = sum_array(a, size);
    printf("The sum is %d\n", sum);
    return 0;
}
