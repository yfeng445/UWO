#include <stdio.h>

int main() {
    char first[20] = {'{'};
    char last[20] = {'`'};
    char curr[20];
    int stop = 0;
    while(!stop){
        printf("Enter word:");
        gets(curr);
        if(curr[0]==0) break;

        int pos = 0;
        while(1){
            int b = 0;
            if(curr[pos] > last[pos]){
                //printf("update last\n");
                for(int i = 0; i<20; i++){
                    last[i] = curr[i];
                }
                b = 1;
            }
            if(curr[pos] < first[pos]){
                //printf("update first\n");
                for(int i = 0; i<20; i++){
                    first[i] = curr[i];
                }
                b = 1;
            }
            if(b) break;

            if(curr[pos] == last[pos]){
                pos++;
            }
            if(curr[pos] == first[pos]){
                pos++;
            }
            if(curr[pos] < last[pos]&&curr[pos]>first[pos]) break;
        }
        printf("%s, %s\n", first, last);
    }
    printf("First:%s\n", first);
    printf("Last:%s\n", last);

    return 0;
}
