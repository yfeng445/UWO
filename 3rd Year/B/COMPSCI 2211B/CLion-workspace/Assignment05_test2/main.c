#include <stdio.h>
#include <string.h>
#include <stdlib.h>


struct player{
    int code;
    char name[50];
    int age;
    char club[50];
    struct player *next;
    struct player *prev;
};


struct team{
    int code;
    char name[25];
    char seeding[2];
    char colour;
    struct player *headPlayer;
    struct team *next;
    struct team *prev;
};

struct team Team;

int teamCounter = 0;

/// Team operations ///

//return true if one of the element of the new team is duplicate
int duplicateTeam(struct team *head, struct team *node){
    int duplicate = 0;
    do{
        duplicate = duplicate||
                    head->code==node->code||
                    !strcmp(head->name, node->name)||
                    !strcmp(head->seeding, node->seeding);
        head = head->next;
    }while (head->next!=NULL);
    printf("duplicate: %d", duplicate);
    return duplicate;
}
struct team *insertTeam(struct team *head){
    if(teamCounter == 32){
        printf("The list if full.\n");
        return head;
    }
    struct team *newTeam = (struct team *) malloc(sizeof(Team));
    int code = 32;
    printf("Please enter team code:");
    scanf("%d", &code);
    fflush(stdin);
    if(code<0||code>31){ //check the input is appropriate or not
        goto invalidInput;
    }
    char name[26] = {""}; //initialize the name with something
    printf("Enter team name:");
    fgets(name,25, stdin); //make sure the string is less than 26 characters
    for(int i = 0; i<25; i++){
        if(name[i] == '\n') name[i] = '\0'; //remove any possible newline character at the end of the input
    }
    fflush(stdin);
    //seeding
    char seeding[3] = {""}; //initialize the seeding
    printf("Enter group seeding:");
    scanf("%s", &seeding);
    for(int i = 0; i<2; i++){ // make sure the length of the string is not more than 2
        if(seeding[i] == '\n') seeding[i] = '\0';
    }
    fflush(stdin);
    char seeding0[8] = {'A','B','C','D', 'E', 'F', 'G', 'H'};
    char seeding1[4] = {'1','2','3','4'};
    int validS0 = 0;
    int validS1 = 0;
    for(int i = 0; i<8; i++){ //check the first character
        validS0 = validS0||(seeding[0] == seeding0[i]);
    }
    for(int i = 0; i<4; i++){ //check the second character
        validS1 = validS1||(seeding[1] == seeding1[i]);
    }
    if(!(validS0&&validS1)){ //the input is valid only if both of the characters are valid
        goto invalidInput;
    }
    char colour;
    printf("Enter the kit colour:");
    scanf(" %c", &colour);
    char colour0[7] = {'R', 'O', 'Y', 'G', 'B', 'I','V'};
    int validC = 0;
    for(int i = 0; i<7; i++){ //check the colour is valid or not
        validC = validC||(colour == colour0[i]);
    }
    if(!validC) goto invalidInput;

    newTeam->code = code;
    strcpy(newTeam->name, name);
    strcpy(newTeam->seeding, seeding);
    newTeam->colour = colour;
    teamCounter++; //increment the counter

    printf("newNode info: %d, ", newTeam->code);
    printf("%s, ", newTeam->name);
    printf("%s, ", newTeam->seeding);
    printf("%s\n", newTeam->colour);
    fflush(stdin);
    /*
    if(duplicateTeam(head, newTeam)){
        printf("At least one of these fields is duplicate\n");
        return head;
    }
    printf("newNode is created successfully");

     */
    printf("something.....");
    newTeam->next = NULL;  //可以省略
    newTeam->next = head->next;
    head->next = newTeam;
    newTeam = NULL; //要置空，防止成为野指针

    printf("head info: %d, ", head->code);
    printf("%s, ", head->name);
    printf("%s, ", head->seeding);
    printf("%s\n", head->colour);



    return head;

    invalidInput:
    printf("Invalid input.\n");
    return head;
}

int main() {
    struct team *teamHead;
    teamHead = (struct team *)malloc(sizeof(Team));
    teamHead->next = NULL;
    struct player *playerHead;
    playerHead = (struct team *)malloc(sizeof(playerHead));
    playerHead->next = NULL;
    for(int i = 0; i<5; i++){
        insertTeam(teamHead);
    }
    return 0;
}
