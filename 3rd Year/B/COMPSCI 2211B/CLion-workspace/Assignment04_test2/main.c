#include<stdio.h>
#include <string.h>

struct Team{
    int code;
    char* name[25];
    char* seeding[2];
    char colour;
}Teams[32];

int counter = 0;

void insertTeam(void){
    if(counter == 32){
        printf("The list is full.\n");
        return;
    }
    int code = 32;
    printf("\tEnter team code:");
    scanf("%d", &code);
    fflush(stdin);
    char name[26] = {""};
    printf("\tEnter team name:");
    fgets(name,26, stdin);
    for(int i = 0; i<26; i++){
        if(name[i] == '\n') name[i] = '\0';
    }
    fflush(stdin);
    char seeding[3] = {""};
    printf("\tEnter group seeding:");
    scanf("%s", &seeding);
    for(int i = 0; i<2; i++){
        if(seeding[i] == '\n') seeding[i] = '\0';
    }
    fflush(stdin);
    char colour;
    printf("\tEnter the kit colour:");
    scanf(" %c", &colour);

    if(code<0||code>31){ //check range of the code
        goto invalidInput;
    }
    char seeding0[8] = {'A','B','C','D', 'E', 'F', 'G', 'H'};
    char seeding1[4] = {'1','2','3','4'};
    int validS0 = 0;
    int validS1 = 0;
    for(int i = 0; i<8; i++){ //check the first character of the seeding
        printf("Seeding0:%c\n", seeding0[i]);
        printf("Valid:%d\n", validS0);
        printf("seeding[0] == seeding0[i]:%d\n", seeding[0] == seeding0[i]);
        validS0 = validS0||(seeding[0] == seeding0[i]); //validS0 = 1 if one of the

    }
    printf("Valid0:%d", validS0);
    for(int i = 0; i<4; i++){ //check the second character of the seeding
        validS1 = validS1||(seeding[1] == seeding1[i]);
    }
    printf("Valid1:%d\n", validS1);
    if(!(validS0&&validS1)){
        printf("Branching...\n");
        goto invalidInput; //goto the invalid input if any one of the characters is not in the range
    }
    char colour0[7] = {'R', 'O', 'Y', 'G', 'B', 'I','V'};
    int validC = 0;
    for(int i = 0; i<7; i++){
        validC = validC||(colour == colour0[i]);
    }
    if(!validC) goto invalidInput;
    for(int i = 0; i<counter; i++){
        printf("%d", Teams[i].code);
        if(Teams[i].code == code){
            printf("Team already exists. Duplicate code.\n");
            return;
        }
        if(!strcmp(name, Teams[i].name)){
            printf("Team already exists. Duplicate name.\n");
            return;
        }
        if(!strcmp(seeding, Teams[i].seeding)){
            printf("Team already exists. Duplicate seeding.\n");
            return;
        }
    }
    Teams[counter].code = code;
    strcpy(Teams[counter].name, name);
    strcpy(Teams[counter].seeding, seeding);
    Teams[counter].colour = colour;
    counter++;
    return;

    invalidInput:
    printf("Invalid input.\n");
    return;
}

int main(){
    while(1){
        insertTeam();
    }
    return (0);
}
