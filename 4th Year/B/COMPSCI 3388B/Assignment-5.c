#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#define NUM_THREADS 2

/*
 * @Author Yulun Feng
 * @ID 251113989
 * @Date Apr 6th, 2023
 */



pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
int stepCount = 0;
#define STEP 100

// {ID, PassengerCount, isAvailable}
int stationList[5][3] = {{0, 500, 1},
                     {1, -50, 1},
                     {2, -100, 1},
                     {3, -250, 1},
                     {4, -100, 1}};

// {ID, Capacity, PassengerCount, StationID}
int trainList[2][4] = {{0, 100, 0, 0},
                   {1, 50, 0, 0}};

int goBack(int trainID){
    printf("Train %d LEAVES Station %d\n", trainID, trainList[trainID][3]);
    if(trainList[trainID][3] > 0){
        trainList[trainID][3]--;
    }
    printf("Train %d ENTERS Station %d\n", trainID, trainList[trainID][3]);
    return 0;
}

int dropOff(int trainID) {
    int stationID = trainList[trainID][3];
    int notEmpty = trainList[trainID][3] > 0;
    int notDone = stationList[stationID][1] < 0;
    while(!(notEmpty)+notDone){
        int timeWait;
        printf("\tStation %d has %d passengers to drop of  \n", trainList[trainID][3], 0-stationList[trainList[trainID][3]][2]);
        if(stationList[stationID][1] < -10){
            timeWait = 10;
            stationList[stationID][1]+=10;
            trainList[trainID][2]-=10;
        }
        else{
            timeWait = stationList[stationID][1];
            trainList[trainID][2]-=stationList[0][1];
            stationList[stationID][1] = 0;
        }
        printf("\tUploading passengers...");

        sleep(timeWait);

        printf("\tTrain %d is at Station %d and has %d/%d passengers\n", trainID, trainList[trainID][3], trainList[trainID][2], trainList[trainID][1]);
        printf("\tStation %d has %d passengers to drop off\n", stationID, stationList[stationID][1]);
        notEmpty = trainList[trainID][3] > 0;
        notDone = stationList[stationID][1] < 0;
    }
    return 0;
}

int pickUp(int trainID){
    printf("Train %d ENTERS Station 0\n", trainID);
    while(1){
        int timeWait;
        printf("\tStation 0 has %d passengers to pick up\n", stationList[0][1]);
        if(stationList[0][1]>10){
            timeWait = 10;
            stationList[0][1]-=10;
            trainList[trainID][2]+=10;
        }
        else{
            timeWait = stationList[0][1];
            trainList[trainID][2]+=stationList[0][1];
            stationList[0][1] = 0;
        }
        printf("\tPicking up passengers...\n");
        /*
        sleep(timeWait);
         */
        printf("\tTrain %d is at Station %d and has %d/%d passengers\n", trainID, trainList[trainID][3], trainList[trainID][2], trainList[trainID][1]);
        printf("\tStation %d has %d passengers to pick up\n", 0, stationList[0][1]);
        if(trainList[trainID]);
    }
    return 0;
}

void goNext(int trainID){
    printf("Train %d LEAVES Station %d\n", trainID, trainList[trainID][3]);
    if(trainList[trainID][3] < 3 ) trainList[trainID][3]++;
    printf("Train %d ENTERS Station %d\n", trainID, trainList[trainID][3]);
}

void *trainStart(void *id){
    int ID = *(int*)id;
    int notDone = stationList[0][2] > 0;
    int notEmpty = trainList[ID][2] > 0;
    while(notDone){
        stepCount++;
        printf("Step Count: %d\n", stepCount);
        if(stepCount > STEP){
            return NULL;
        }
        pthread_mutex_lock(&mutex);
        pickUp(ID);
        printf("Train #%d, passengerCount %d, atStation %d\n", ID, trainList[ID][2], trainList[ID][3]);
        goNext(ID);
        pthread_mutex_unlock(&mutex);
        while(notEmpty){
            stepCount++;
            printf("dropOff&GoNext -> Step Count: %d\n", stepCount);
            if(stepCount > STEP){
                return NULL;
            }
            pthread_mutex_lock(&mutex);
            dropOff(ID);
            goNext(ID);
            pthread_mutex_unlock(&mutex);
            notEmpty = trainList[ID][2] > 0;
        }
        while(trainList[ID][3]!= 0){
            stepCount++;
            printf("GoBack -> Step Count: %d\n", stepCount);
            if(stepCount > STEP){
                return NULL;
            }
            pthread_mutex_lock(&mutex);
            goBack(ID);
            pthread_mutex_unlock(&mutex);
        }
        notDone = stationList[0][2] > 0;
        notEmpty = trainList[ID][2] > 0;

    }
    return NULL;
}


int main(){
    pthread_t trains[NUM_THREADS]; // simulation of trains
    int trainIDs[NUM_THREADS]; //{0, 1} => indicator of train ids
    for(int i = 0; i<NUM_THREADS; i++){
        trainIDs[i] = i;
    }
    for(int i = 0; i<NUM_THREADS; i++){
        pthread_create(&trains[i], NULL, trainStart, &trainIDs[i]);
    }
    for(int i = 0; i<NUM_THREADS; i++){
        pthread_join(trains[i], NULL);
    }

    return 0;
}
