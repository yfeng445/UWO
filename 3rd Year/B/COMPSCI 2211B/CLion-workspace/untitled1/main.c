#include <stdio.h>

int func(int i){
    int count = i;
    while (i!=1){
        //printf("%d\n", i);
        count = count*(i-1);
        i--;
    }
    return count;
}

int main() {
   // int i = 1;
    for(int i = 1; i<10; i++){
        printf("%d\n", func(i));
    }
    return 0;
}
