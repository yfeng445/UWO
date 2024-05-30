#include <stdio.h>


int sum_two_dimensional_array(const int *a, int size){
    int sum = 0;
    for(int i = 0; i<size; i++){
        sum += *(a+i);
    }
    return sum;
}


int main() {
    int a[3][3] = {{1,2,3},{4,5,6},{7,8,9}};
    int (*p) = a;
    int (*q) = p;
    int size = sizeof(a)/sizeof(int);
    int sum = sum_two_dimensional_array(q, size);
    printf("%d", sum);
    return 0;
}
