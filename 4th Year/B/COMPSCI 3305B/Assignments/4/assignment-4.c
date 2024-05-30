#include <stdio.h>
#include <getopt.h>
#include <string.h>
#include <stdlib.h>
#include <limits.h>

#define MAX_ROWS 100
#define MAX_COLS 2
#define MAX_FIELD_LEN 128
#define MAX_LINE_LEN (MAX_COLS * MAX_FIELD_LEN + MAX_COLS)
#define LINE_NUM 20

/*
 * Preprocess the CSV file, put the data into an array and return
 */
char ***readCSV(char* filePath) {
    char line[MAX_LINE_LEN];
    char *token;
    char data[MAX_ROWS][MAX_COLS][MAX_FIELD_LEN];
    int row = 0;
    FILE *csv_file = fopen(filePath, "r");
    if (csv_file == NULL) {
        printf("Failed to open CSV file.\n");
        return NULL;
    }
    while (fgets(line, MAX_LINE_LEN, csv_file) != NULL) {
        token = strtok(line, ",");
        int col = 0;
        while (token != NULL) {
            strcpy(data[row][col], token);
            token = strtok(NULL, ",");
            col++;
        }
        row++;
    }
    fclose(csv_file);
    char*** returnArr = malloc(MAX_ROWS*sizeof(char*));
    for(int i = 0; i<MAX_ROWS; i++){
        returnArr[i] = malloc(MAX_COLS*sizeof(char*));
    }
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < MAX_COLS; j++) {
            returnArr[i][j] = data[i][j];
        }
    }
    return returnArr;
}

int waitingTime[LINE_NUM];
int turnaroundTime[LINE_NUM];
int taskTime[LINE_NUM];

/*
 * return true if all numbers in array equals to zero, false otherwise
 * It is used to determined whether a working list is clear or not
 */
int done(int arr[]){
    for(int i = 0; i<LINE_NUM; i++){
        if(arr[i]!=0){
            return 0;
        }
    }
    return 1;
}

/*
 * First Come First Served, finishing task in coming order
 */
int FCFS(){
    printf("First Come First Served\n");
    int taskNO = 0;
    int time = 1;
    int totalWaitingTime[LINE_NUM];
    int totalTurnaroundTime[LINE_NUM];
    while(!done(taskTime)){
        if(taskTime[taskNO]==0){
            taskNO++; // move to next task if current task is done
            for(int i = taskNO; i<LINE_NUM; i++){
                waitingTime[i]--;
                turnaroundTime[i]--;
            }
        }
        else{
            totalWaitingTime[taskNO] = waitingTime[taskNO];
            totalTurnaroundTime[taskNO] = turnaroundTime[taskNO];
            printf("T%d : P%d - Burst left %d, Wait time %d, Turnaround Time %d\n", time, taskNO, taskTime[taskNO], waitingTime[taskNO], turnaroundTime[taskNO]);
            taskTime[taskNO]--; // simulates processing task
            for(int i = taskNO; i<LINE_NUM; i++){
                turnaroundTime[i]++;
            }
            for(int i = taskNO+1; i<LINE_NUM; i++){
                waitingTime[i]++;
            }
            time++;
        }
    }
    int TWT = 0;
    int TAT = 0;
    for(int i = 0; i<LINE_NUM; i++){
        TWT+=totalWaitingTime[i];
        TAT+=totalTurnaroundTime[i];
    }
    for(int i = 0; i<LINE_NUM; i++){
        printf("P%d\n\tAverage waiting time:\t%d\n\tAverage turnaround time:\t%d\n",i, waitingTime[i], turnaroundTime[i]);
    }
    printf("Total average waiting time:\t%.1f\nTotal average turnaround time:\t%.1f\n", (float)TWT/LINE_NUM, (float)TAT/LINE_NUM);
}

/*
 * Get the index of smallest non-zero value in list
 */
int indexSmallest(int arr[]){
    int min = INT_MAX;
    int pos = 0;
    for(int i = 0; i<LINE_NUM; i++){
        if(arr[i]>0 && arr[i]<min){
            pos = i;
            min = arr[i];
        }
    }
    return pos;
}

/*
 * Shortest Job First, after finishing current job, next job to finish is the shortest one in queue
 */
int SJF(){
    printf("Shortest Time First\n");
    int taskNO = 0; //The starting task would only be P0 if it is preemptive.
    int time = 0;
    int totalWaitingTime[LINE_NUM];
    int totalTurnaroundTime[LINE_NUM];
    int subTaskTime[LINE_NUM];
    for(int i = 0; i<LINE_NUM; i++){
        subTaskTime[i] = 0;
    }
    while(!done(taskTime)){
        if(time<LINE_NUM){
            subTaskTime[time] = taskTime[time];
            taskNO = indexSmallest(subTaskTime);
            totalWaitingTime[taskNO] = waitingTime[taskNO];
            totalTurnaroundTime[taskNO] = turnaroundTime[taskNO];
            printf("T%d : P%d - Burst left %d, Wait time %d, Turnaround Time %d\n", time, taskNO, taskTime[taskNO],waitingTime[taskNO], turnaroundTime[taskNO]);
            time++;
            taskTime[taskNO]--; // simulates processing task
            for (int i = 0; i < time; i++) {
                if (taskTime[i] != 0 && i!=taskNO) {
                    waitingTime[i]++;
                }
            }
            for (int i = 0; i < time; i++) {
                if (taskTime[i] != 0) {
                    turnaroundTime[i]++;
                }
            }
            if(taskTime[taskNO]==0){
                subTaskTime[taskNO] = 0;
            }
        }
        else{
            taskNO = indexSmallest(taskTime);
            totalWaitingTime[taskNO] = waitingTime[taskNO];
            totalTurnaroundTime[taskNO] = turnaroundTime[taskNO];
            printf("T%d : P%d - Burst left %d, Wait time %d, Turnaround Time %d\n", ++time, taskNO, taskTime[taskNO],waitingTime[taskNO], turnaroundTime[taskNO]);
            taskTime[taskNO]--; // simulates processing task
            for (int i = 0; i < LINE_NUM; i++) {
                if (taskTime[i] != 0 && i!=taskNO) {
                    waitingTime[i]++;
                }
            }
            for (int i = 0; i < LINE_NUM; i++) {
                if (taskTime[i] != 0) {
                    turnaroundTime[i]++;
                }
            }
        }
    }
    for(int i = 0; i<LINE_NUM; i++){
        turnaroundTime[i]++;
    }
    int TWT, TAT = 0;
    for(int i = 0; i<LINE_NUM; i++){
        TWT+=totalWaitingTime[i];
        TAT+=totalTurnaroundTime[i];
    }
    for(int i = 0; i<LINE_NUM; i++){
        printf("P%d\n\tAverage waiting time:\t%d\n\tAverage turnaround time:\t%d\n",i, waitingTime[i], turnaroundTime[i]);
    }
    printf("Total average waiting time:\t%.1f\nTotal average turnaround time:\t%.1f\n", (float)TWT/LINE_NUM, (float)TAT/LINE_NUM+1);
}

/*
 * Get the index of next non zero value, rotated
 */
int nextIndex(int currIndex, int arr[]){
    if(done(arr)) return -1;
    int i = currIndex+1;
    if(i == LINE_NUM) i=0;
    while(arr[i]==0){
        i++;
        if(i == LINE_NUM) i=0;
    }
    return i;
}

/*
 * Round Robin, processing each process for given quantum time, rotately
 */
int RR(int Quantum){
    printf("Round Robin with Quantum %d\n", Quantum);
    int time = 0;
    int taskNO = 0;
    int totalWaitingTime[LINE_NUM];
    int totalTurnaroundTime[LINE_NUM];
    while(!done(taskTime)){
        if(taskTime[taskNO]==0){
            taskNO = nextIndex(taskNO, taskTime); // move to next task if current task is done
            for(int i = taskNO; i<LINE_NUM; i++){
                waitingTime[i]--;
                turnaroundTime[i]--;
            }
        }
        else{
            totalWaitingTime[taskNO] = waitingTime[taskNO];
            totalTurnaroundTime[taskNO] = turnaroundTime[taskNO];
            if(taskTime[taskNO]>Quantum){ // if the task could not be finished in one round
                for(int j = 0; j<Quantum; j++){
                    printf("T%d : P%d - Burst left %d, Wait time %d, Turnaround Time %d\n", time, taskNO, taskTime[taskNO], waitingTime[taskNO], turnaroundTime[taskNO]);
                    taskTime[taskNO]--; // simulates processing task
                    for(int i = 0; i<LINE_NUM; i++){
                        if (taskTime[i] != 0) {
                            turnaroundTime[i]++;
                        }
                    }
                    for(int i = 0; i<LINE_NUM; i++){
                        if (taskTime[i] != 0 && i!=taskNO) {
                            waitingTime[i]++;
                        }
                    }
                    time++;
                }
                for (int i = 0; i < LINE_NUM; i++) {
                    if (taskTime[i] != 0 && i!=taskNO) {
                        waitingTime[i]--;
                        turnaroundTime[i]--;
                    }
                }
                taskNO = nextIndex(taskNO, waitingTime);
            }
            else{
                while(taskTime[taskNO]!=0){ // if the task could be finish in one round
                    printf("T%d : P%d - Burst left %d, Wait time %d, Turnaround Time %d\n", time, taskNO, taskTime[taskNO], waitingTime[taskNO], turnaroundTime[taskNO]);
                    taskTime[taskNO]--; // simulates processing task
                    for(int i = taskNO; i<LINE_NUM; i++){
                        if (taskTime[i] != 0) {
                            turnaroundTime[i]++;
                        }
                    }
                    for(int i = taskNO+1; i<LINE_NUM; i++){
                        if (taskTime[i] != 0 && i!=taskNO) {
                            waitingTime[i]++;
                        }
                    }
                    time++;
                }
                for (int i = 0; i < LINE_NUM; i++) {
                    if (taskTime[i] != 0 && i!=taskNO) {
                        waitingTime[i]--;
                        turnaroundTime[i]--;
                    }
                }
                taskNO = nextIndex(taskNO, waitingTime);
            }
        }
    }    int TWT = 0;
    int TAT = 0;
    for(int i = 0; i<LINE_NUM; i++){
        TWT+=totalWaitingTime[i];
        TAT+=totalTurnaroundTime[i];
    }
    for(int i = 0; i<LINE_NUM; i++){
        printf("\nP%d\n\tAverage waiting time:\t%d\n\tAverage turnaround time:\t%d\n",i, waitingTime[i], turnaroundTime[i]);
    }
    printf("\nTotal average waiting time:\t%.1f\nTotal average turnaround time:\t%.1f\n", (float)TWT/LINE_NUM, (float)TAT/LINE_NUM);
}

int main(int argc, char* argv[]) {
    int c;
    while((c=getopt(argc,argv, "f:s:r:"))!= -1 ) {  // Get command line param
        switch (c) {
            case 'f':{ // First Come First Served
                char*** data = readCSV(argv[2]);
                //initializing data
                for(int i = 0; i<LINE_NUM; i++){
                    waitingTime[i] = 0;
                    turnaroundTime[i] = 0;
                    taskTime[i] = atoi(data[i][1]);
                }
                FCFS();
                break;
            }
            case 's':{ // Shortest Job First
                char*** data = readCSV(argv[2]);
                //initializing data
                for(int i = 0; i<LINE_NUM; i++){
                    waitingTime[i] = 0;
                    turnaroundTime[i] = 0;
                    taskTime[i] = atoi(data[i][1]);
                }
                SJF();
                break;
            }
            case 'r':{ // Round-Robin
                int Quantum = atoi(argv[2]);
                char*** data = readCSV(argv[3]);
                //initializing data
                for(int i = 0; i<LINE_NUM; i++){
                    waitingTime[i] = 0;
                    turnaroundTime[i] = 0;
                    taskTime[i] = atoi(data[i][1]);
                }
                RR(Quantum);
                break;
            }
        }
    }
    return 0;
}
