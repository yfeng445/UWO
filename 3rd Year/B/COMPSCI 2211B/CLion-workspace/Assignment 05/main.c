#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
 * Author: Yulun Feng
 * ID: 251113989
 * Date: Nov 29, 2022
*/

//definition of player
struct player{
    int code;
    char name[50];
    int age;
    char club[50];
    struct player *next;
    struct player *prev;
};

//definition of team
struct team{
    int code;
    char name[25];
    char seeding[2];
    char colour;
    struct player *headPlayer;
    struct team *next;
};

struct team Team;
int teamCounter = 0; //counting the number of teams


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
    return duplicate;
}

//insert a new team
struct team *insertTeam(struct team *head){
    if(teamCounter == 32){
        printf("The list if full.\n");
        return head;
    }
    struct team *newTeam = (struct team *) malloc(sizeof(Team));
    int code = 32;
    printf("Please enter team code:");
    scanf("&d", &code);
    fflush(stdin);
    if(newTeam->code<0||newTeam->code>31){ //check the input is appropriate or not
        goto invalidInput;
    }
    char name[26] = {""}; //initialize the name with something
    printf("\tEnter team name:");
    fgets(name,25, stdin); //make sure the string is less than 26 characters
    for(int i = 0; i<25; i++){
        if(name[i] == '\n') name[i] = '\0'; //remove any possible newline character at the end of the input
    }
    fflush(stdin);
    //seeding
    char seeding[3] = {""}; //initialize the seeding
    printf("\tEnter group seeding:");
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
    printf("\tEnter the kit colour:");
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

    if(duplicateTeam(head, newTeam)){
        printf("At least one of these fields is duplicate\n");
        return head;
    }

    newTeam->next = NULL;
    newTeam->next = head->next;
    head->next = newTeam;
    newTeam = NULL;
    return head;

    invalidInput:
    printf("Invalid input.\n");
    return head;
}

//update the team with given code
struct team *updateTeam(struct team *head){
    int code;
    struct team *tmpHead;
    *tmpHead = *head;
    printf("Please enter the team code:");
    scanf("%d", &code);
    while(1){
        if(tmpHead->code == code){
            struct team *newTeam = (struct team *) malloc(sizeof(Team));
            int code = 32;
            printf("Please enter team code:");
            scanf("&d", &code);
            fflush(stdin);
            if(newTeam->code<0||newTeam->code>31){ //check the input is appropriate or not
                goto invalidInput;
            }
            char name[26] = {""}; //initialize the name with something
            printf("\tEnter team name:");
            fgets(name,25, stdin); //make sure the string is less than 26 characters
            for(int i = 0; i<25; i++){
                if(name[i] == '\n') name[i] = '\0'; //remove any possible newline character at the end of the input
            }
            fflush(stdin);
            //seeding
            char seeding[3] = {""}; //initialize the seeding
            printf("\tEnter group seeding:");
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
            printf("\tEnter the kit colour:");
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

            if(duplicateTeam(head, newTeam)){
                printf("At least one of these fields is duplicate\n");
                return head;
            }

            newTeam->next = NULL;
            newTeam->next = head->next;
            head->next = newTeam;
            newTeam = NULL;
            return head;

            invalidInput:
            printf("Invalid input.\n");
            return head;
        }
        if(tmpHead->next == NULL){
            printf("The team does not exist.");
            return head;
        }
    }
}

//print all team info
struct team *printTeam(struct team *head){
    struct team *tmpHead;
    *tmpHead = *head;
    while(1){
        printf("Team code: %d, Team name: %s, Team seeding: %s, Team colour: %c\n", tmpHead->code, tmpHead->name, tmpHead->seeding, tmpHead->colour);
        if(tmpHead->next == NULL) return head;
    }
}

// print the info of a team with given code
struct team *searchTeam(struct team *head){
    int code;
    struct team *tmpHead;
    *tmpHead = *head;
    printf("Please enter the team code:");
    scanf("%d", &code);
    while(1){
        if(tmpHead->code == code){
            printf("Team code: %d, Team name: %s, Team seeding: %s, Team colour: %c\n", tmpHead->code, tmpHead->name, tmpHead->seeding, tmpHead->colour);
            return head;
        }
        if(tmpHead->next == NULL){
            printf("The team does not exist.");
            return head;
        }
    }
}

//delete a team from the list
struct team *deleteTeam(struct team *head){
    int code;
    struct team *tmpHead;
    *tmpHead = *head;
    printf("Please enter the team code:");
    scanf("%d", &code);
    while(1){
        if(tmpHead->code == code){
            printf("Team code: %d, Team name: %s, Team seeding: %s, Team colour: %c\n", tmpHead->code, tmpHead->name, tmpHead->seeding, tmpHead->colour);
            return head;
        }
        if(tmpHead->next == NULL){
            printf("The team does not exist.");
            return head;
        }
    }
}

//control the operation of teams
struct team *controlTeam(struct team *head){
    char code;
    while(1) {
        fflush(stdin);
        printf("\nEnter operation code:");
        scanf(" %c", &code);//pick up operations
        switch (code) {
            case ('i'):
                insertTeam(head);
                break;
            case ('u'):
                updateTeam(head);
                break;
            case ('p'):
                printTeam(head);
                break;
            case ('s'):
                searchTeam(head);
                break;
            case ('d'):
                deleteTeam(head);
            default:
                printf("Please input a valid option.\n");
        }
    }
}

/// Player operations ///

//insert a new player
void insertPlayer(){

}

//update player information
void updatePlayer(){

}

//print all players information
void printPlayer(){

}

//delete a player from the list
void deletePlayer(){

}

//search a player
void searchPlayer(){

}


//control function that calls different operations
struct player *controlPlayer(struct player *head){
    char code;
    while(1) {
        fflush(stdin);
        printf("\nEnter operation code:");
        scanf(" %c", &code);//pick up operations
        switch (code) {
            case ('i'):
                insertPlayer(head);
                break;
            case ('u'):
                updatePlayer(head);
                break;
            case ('p'):
                printPlayer(head);
                break;
            case ('s'):
                searchPlayer(head);
                break;
            case ('d'):
                deletePlayer(head);
                break;
            default:
                printf("Please input a valid option.\n");
        }
    }
}

int main() {
    char code;
    printf("$ ./worldCupDB\n"
           "******************\n"
           "* 2211 World Cup *\n"
           "******************\n");
    struct team *teamHead;
    teamHead = (struct team *)malloc(sizeof(teamHead));
    teamHead->next = NULL;
    struct player *playerHead;
    playerHead = (struct team *)malloc(sizeof(playerHead));
    playerHead->next = NULL;
    playerHead->prev = NULL;
    while (1) {
        fflush(stdin);
        printf("\nEnter operation code:");
        scanf(" %c", &code);//pick up operations
        switch (code) {
            case ('h'):
                printf("To control team info, input 't'\n"
                       "To control player info, input, 'p'\n"
                       "To quit the program, input 'q'\n ");
                break;
            case ('t'):
                controlTeam(teamHead);
                break;
            case ('p'):
                controlPlayer(playerHead);
                break;
            case ('q'):
                free(teamHead);
                free(playerHead);
                printf("$");
                return 0;
            default:
                printf("Please input a valid option.\n");
        }
    }
}
