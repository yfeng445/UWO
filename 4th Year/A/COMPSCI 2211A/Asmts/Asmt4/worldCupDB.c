#include <stdio.h>
#include <string.h>
/*
 * @Author: Yulun Feng
 * @ID: 251113989
 * @Date: Nov 15, 2022
 */

struct Team{ //structure of a team
    int code;
    char* name[25];
    char* seeding[2];
    char colour;
}Teams[32];

int counter = 0; //counter indicate the number of teams in the array

//insert a new team
void insertTeam(void){
    //code
    if(counter == 32){ //check the array is full or not
        printf("The list is full.\n");
        return;
    }
    int code = 32; //initialize the code with some number
    printf("\tEnter team code:");
    scanf("%d", &code);
    fflush(stdin);
    if(code<0||code>31){ //check the input is appropriate or not
        goto invalidInput;
    }
    for(int i = 0; i<counter; i++) { //check the input code is already in list or not
        if (Teams[i].code == code) {
            printf("Team already exists. Duplicate code.\n");
            return;
        }
    }

    //name
    char name[26] = {""}; //initialize the name with something
    printf("\tEnter team name:");
    fgets(name,25, stdin); //make sure the string is less than 26 characters
    for(int i = 0; i<25; i++){
        if(name[i] == '\n') name[i] = '\0'; //remove any possible newline character at the end of the input
    }
    fflush(stdin);
    for(int i = 0; i<counter; i++){ // check the name is duplicate or not
        if(!strcmp(name, Teams[i].name)){
            printf("Team already exists. Duplicate name.\n");
            return;
        }
    }

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
    for(int i = 0; i<counter; i++){ //check the seeding is duplicate or not
        if(!strcmp(seeding, Teams[i].seeding)){
            printf("Team already exists. Duplicate seeding.\n");
            return;
        }
    }

    //colour
    char colour;
    printf("\tEnter the kit colour:");
    scanf(" %c", &colour);
    char colour0[7] = {'R', 'O', 'Y', 'G', 'B', 'I','V'};
    int validC = 0;
    for(int i = 0; i<7; i++){ //check the colour is valid or not
        validC = validC||(colour == colour0[i]);
    }
    if(!validC) goto invalidInput;

    //add inputs to the structure
    Teams[counter].code = code;
    strcpy(Teams[counter].name, name);
    strcpy(Teams[counter].seeding, seeding);
    Teams[counter].colour = colour;
    counter++; //increment the counter
    return;

    //invalid inputs
    invalidInput:
    printf("Invalid input.\n");
    return;
}


//find a team with a given code
void searchTeam(void){
    int code;
    printf("\tEnter team code: ");
    scanf("%d", &code);
    for(int i = 0; i<counter; i++){ //find the code is in the array or not
        if(Teams[i].code==code){
            printf("Team Code\tTeam Name\tGroup Seeding\tPrimary Kit Colour\n");
            printf("%d\t\t%s\t\t%s\t\t",Teams[i].code, Teams[i].name, Teams[i].seeding);
            switch(Teams[i].colour){ //handling the colour, using switch...cases to speed up the search
                case 'R':
                    printf("Red\n");
                    break;
                case 'O':
                    printf("Orange\n");
                    break;
                case 'Y':
                    printf("Yellow\n");
                    break;
                case 'G':
                    printf("Green\n");
                    break;
                case 'B':
                    printf("Blue\n");
                    break;
                case 'I':
                    printf("Indigo\n");
                    break;
                case 'V':
                    printf("Violet\n");
                    break;
                default: ;
            }
        }
    }
}


//update a team with given code
void updateTeam(void){
    int code = 32;
    printf("\tEnter team code:");
    scanf("%d", &code);
    fflush(stdin);
    for(int j = 0; j<counter; j++){ //make sure the code is in the array
        if(code == Teams[j].code){ //since the code is the searching target, no need of validification of the code as an input\
            //name, almost the same as the one in insertTeam()
            char name[26] = {""};
            printf("\tEnter team name:");
            fgets(name,26, stdin);
            for(int i = 0; i<26; i++){
                if(name[i] == '\n') name[i] = '\0';
            }
            fflush(stdin);
            for(int i = 0; i<counter; i++){
                if(!strcmp(name, Teams[i].name)&&i!=j){ //exclude the selected structure
                    printf("Team already exists. Duplicate name.\n");
                    return;
                }
            }
            //seeding, almost the same as the on in insertTeam()
            char seeding[3] = {""};
            printf("\tEnter group seeding:");
            scanf("%s", &seeding);
            for(int i = 0; i<2; i++){
                if(seeding[i] == '\n') seeding[i] = '\0';
            }
            fflush(stdin);
            char seeding0[8] = {'A','B','C','D', 'E', 'F', 'G', 'H'};
            char seeding1[4] = {'1','2','3','4'};
            int validS0 = 0;
            int validS1 = 0;
            for(int i = 0; i<8; i++){
                validS0 = validS0||(seeding[0] == seeding0[i]);
            }
            for(int i = 0; i<4; i++){
                validS1 = validS1||(seeding[1] == seeding1[i]);
            }
            if(!(validS0&&validS1)){
                goto invalidInput;
            }
            for(int i = 0; i<counter; i++){
                if(!strcmp(seeding, Teams[i].seeding)&&i!=j){ //exclude the selected structure
                    printf("Team already exists. Duplicate seeding.\n");
                    return;
                }
            }
            //colour, almost the same as the on in insertTeam()
            char colour;
            printf("\tEnter the kit colour:");
            scanf(" %c", &colour);
            char colour0[7] = {'R', 'O', 'Y', 'G', 'B', 'I','V'};
            int validC = 0;
            for(int i = 0; i<7; i++){
                validC = validC||(colour == colour0[i]);
            }
            if(!validC) goto invalidInput;
            //update the structure
            Teams[j].code = code;
            strcpy(Teams[j].name, name);
            strcpy(Teams[j].seeding, seeding);
            Teams[j].colour = colour;
            return;

            invalidInput:
            printf("Invalid input.\n");
            return;
            }
        //update team information
        }
    printf("Cannot find the team in the list.");
    return;
}


//print a list of the team information in the array
//very likely to searchTeams() function
void printTeams(void){
    printf("Team Code\tTeam Name\tGroup Seeding\tPrimary Kit Colour\n");
    for(int i = 0; i<counter; i++){
        printf("%d\t\t%s\t\t%s\t\t",Teams[i].code, Teams[i].name, Teams[i].seeding);
        switch(Teams[i].colour){
            case 'R':
                printf("Red\n");
                break;
            case 'O':
                printf("Orange\n");
                break;
            case 'Y':
                printf("Yellow\n");
                break;
            case 'G':
                printf("Green\n");
                break;
            case 'B':
                printf("Blue\n");
                break;
            case 'I':
                printf("Indigo\n");
                break;
            case 'V':
                printf("Violet\n");
                break;
            default: ;
        }
    }
}

int main() {
    char code;
    printf("$ ./worldCupDB\n"
            "******************\n"
           "* 2211 World Cup *\n"
           "******************\n");
    while(1) {
        fflush(stdin);
        printf("\nEnter operation code:");
        scanf(" %c", &code);//pick up operations
        switch (code) {
            case ('i'):
                insertTeam();
                break;
            case ('u'):
                updateTeam();
                break;
            case ('p'):
                printTeams();
                break;
            case ('s'):
                searchTeam();
                break;
            case ('q'):
                printf("$");
                return 0;
            default:
                printf("Please input a valid option.\n");
        }
    }
}
