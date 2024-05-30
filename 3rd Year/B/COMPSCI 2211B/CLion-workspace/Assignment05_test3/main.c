#include <stdio.h>

int main() {
    int code = 32;
    printf("Please enter team code:");
    scanf("%d", &code);
    //fflush(stdin);
    printf("%d", code);
    return 0;
}
