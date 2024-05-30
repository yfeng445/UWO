#include <stdio.h>

/*
 * @ Author Yulun Feng
 * @ Student ID 251113989
 * @ Date Oct 21st, 2022
 */

void Part1(int size, int array[]){
    printf("\nPart 1:\n\tYour array is: ");
    for(int i = 0; i<size; i++){ //print the value and the position
        if(i!=size-1) printf("[%d] = %d, ", i, array[i]);
        else printf("[%d] = %d", i, array[i]);
    }
    printf("\n");
}

void Part2(int size, int array[]){
    printf("Part 2:\n\t");
    int maximum = array[0];
    for(int i = 0; i<size; i++){ // go through the array to get the maximum value
        if(array[i]>maximum) maximum = array[i];
    }
    printf("The largest value in your array is: %d\n", maximum);
}

void Part3(int size, int array[]){
    printf("Part 3:\n\tYour array in reverse is: ");
    for(int i = size-1; i>=0; i--) printf("%d ", array[i]); // print the array in reverse order
    printf("\b\n");
}

void Part4(int size, int array[]){
    printf("Part 4:\n\t");
    int sum = 0;
    for(int i = 0; i<size; i++) sum+= array[i]; // accumulate to get the sum of the values in array
    printf("The sum of all values in your array is: %d\n", sum);
}

void Part5(int size, int array[]){
    printf("Part 5:\n\tYour array in sorted order is: ");
    int max = array[0];
    for(int i = 0; i<size; i++){ //get the largest value in the array
        if(array[i]>max) max = array[i];
    }
    for(int i = 0; i<size; i++){ //go through all values in the array
        int tempMax = 0;
        for(int j = 0; j<size; j++){
            if(array[j]<max&&array[j]>tempMax) tempMax = array[j]; //get the largest not printed value in the array
        }
        printf("%d ", max);
        max = tempMax; //update the max to get the largest not printed value in the next loop
    }
    printf("\n");
}

void Part6(int size, int array[]){
    printf("Part 6:\n\tYour array with first and last element switched is: ");
    printf("%d ", array[size-1]); // print the last value
    for(int i = 1; i<size-1; i++){ // print rest of the values in normal order
        printf("%d ", array[i]);
    }
    printf("%d\n", array[0]); // print the first value
}
int main() {
    int size;
    printf("Please enter the number of integers to process:");
    scanf("%d", &size); // get number of inputs
    if(size<13&&size>4) printf( "There is enough room in your array for %d integers (%d bytes)\n", size, size*4);
    else printf("Invalid numbers of input");
    int array[size];
    printf("Please enter your integers separated by spaces:", sizeof(array)/sizeof(array[0]));
    for(int i = 0; i<sizeof(array)/sizeof(array[0]); i++){
        scanf("%d", &array[i]); // input values
    }
    Part1(size, array);
    Part2(size, array);
    Part3(size, array);
    Part4(size, array);
    Part5(size, array);
    Part6(size, array);

    return 0;
}
