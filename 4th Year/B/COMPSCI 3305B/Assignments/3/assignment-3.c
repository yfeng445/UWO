#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#define min(x, y) (((x) < (y)) ? (x) : (y))

/**
 * @Author Yulun Feng
 * @ID 251113989
 * @DATE Mar 3rd, 2023
 */


int numThread;
int maxNum;
int totalCount = 0;
int prevCount = 0;
float totalSum= 0;
float prevSum = 0;
int loop = 0;

//check a number is an integer or not
int isPrime(int n){
    if(n == 0||n==1) return 0;
    for(int i = 2; i<n; i++){
        if(n%i == 0) return 0;
    }
    return 1;
}

//get the sum and count of primes in a specific block of numbers
void *getPrimes(void *in){
    int low = *(int* )in;
    fflush(stdout);
    int high = min(maxNum, low+maxNum/numThread); // get high boundary
    prevCount = totalCount;
    prevSum = totalSum;
    for(int i = low; i<high+1; i++){
        if(isPrime(i)){ // store values
            totalCount += 1;
            totalSum += i;
        }
    }
    fflush(stdout);
    printf("Sum of thread %d is %.0f, Count is %d\n",loop, totalSum-prevSum, totalCount-prevCount);
    loop+=1;
    return 0;
}

int main( int argc, char *argv[] ) {
    if (argc != 3) { //make sure there are only 2 inputs
        printf("Invalid input.\n");
        return 0;
    }
    numThread = atoi(argv[1]);
    maxNum = atoi(argv[2]);
    int step; //numbers of value in each block
    if(maxNum%numThread==0) step = maxNum/numThread;
    else step = (maxNum/numThread)+1;
    pthread_t threads[numThread];
    int inputList[numThread];
    for(int i = 0; i<numThread; i++){
        inputList[i] = i*step;
    }
    for(int i = 0; i<numThread; i++){ //create threads for each part
        printf("Thread #%d is finding primes from low=%d to high=%d\n", loop, inputList[i], min(inputList[i]+step, maxNum));
        pthread_create(&threads[i], NULL, getPrimes, &inputList[i]);
        pthread_join(threads[i], NULL);
    }

    printf("\tGRAND SUM IS %.0f, COUNT IS %d\n", totalSum, totalCount);
}