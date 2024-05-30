#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

#include "checkPassword.h"

char intToAscii(int i){
    return i;
}

const char* check(int position){
    for(int i = 33; i<127; i++) {
        for (int j = 33; j < 127; j++) {
            for (int k = 33; k < 127; k++) {
                char pw0 = intToAscii(i);
                char pw1 = intToAscii(j);
                char pw2 = intToAscii(k);
                char pw[3];
                pw[0] = pw0;
                pw[1] = pw1;
                pw[2] = pw2;
                int pass = checkPassword(pw, position);
                if (pass == 0) {
                    //printf("%s\n", pw);
                    return pw;
                }
            }
        }
    }
    return NULL;
}

int main (int argc, char* argv[]){
    int ch;
    const char* password[3];
    while ((ch = getopt(argc, argv, "f::p::")) != -1){
        switch(ch) {
            case 'f':
                printf("Fork()\n");
                break;
            case 'p':
                printf("Parent()\n");
                break;
            default:
                for (int i = 0; i < 4; i++) {
                    char *pw = check(i * 3);
                    password[i] = pw;
                }
        }
    }


    return 0;
}
