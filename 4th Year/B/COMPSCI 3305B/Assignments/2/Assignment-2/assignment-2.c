#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

/**
 * @author Yulun Feng
 * @id 251113989
 * @date Feb 13, 2023
 */

int Multiply(int i, int j){
    return i*j;
}

int main(int argc,char* argv[]) {
    printf("Your integers are %d %d\n", atoi(argv[1]), atoi(argv[2]));

    if(argc!=3){ // make sure there are exactly two input parameters
        return 0;
    }
    if(atoi(argv[1])<10){ // if length(a)<=1 then return a*b
        printf(": %d\n", atoi(argv[1])*atoi(argv[2]));
    }
    int a1, a2, b1, b2;
    int A, B, C, D, X, Y, Z;
    int fd0[2], fd1[2], fd2[2], fd3[2], fd4[2];

    // breaking two input into a1, a2, b1, b2
    a1 = atoi(argv[1])/100;
    a2 = atoi(argv[1])%100;
    b1 = atoi(argv[2])/100;
    b2 = atoi(argv[2])%100;


    // A = Multiply(a1, b1)
    if(pipe(fd0) < 0){
        printf("pipe(fd0) failed");
        exit(1);
    }
    pid_t first = fork();
    printf("Parent (PID %d): Created child (PID %d)\n\n", getpid(), first);
    if(first < 0){
        perror("fork() failed");
        exit(1);
    }
    if(first > 0) {
        printf("###\n");
        printf("# Calculating X\n");
        printf("###\n");
        write(fd0[1], &a1, sizeof(a1));
        printf("Parent (PID %d): Sending %d to child\n", getpid(), a1);
        write(fd0[1], &b1, sizeof(b1));
        printf("Parent (PID %d): Sending %d to child\n", getpid(), b1);
        wait(NULL);
        read(fd0[0], &A, sizeof(A));
        printf("Parent (PID %d): Received %d from child\n\n", getpid(), A);
        X = A*10000; // X = A*10^4

        printf("###\n");
        printf("# Calculating Y\n");
        printf("###\n");

        // B = Multiply(a2, b1)
        if(pipe(fd1) < 0){
            printf("pipe(fd1) failed");
            exit(1);
        }
        pid_t second = fork();
        if(second < 0){
            perror("fork(second) failed");
            exit(1);
        }
        if(second > 0){
            write(fd1[1], &a2, sizeof(a2));
            printf("Parent (PID %d): Sending %d to child\n", getpid(), a2);
            write(fd1[1], &b1, sizeof(b1));
            printf("Parent (PID %d): Sending %d to child\n", getpid(), b1);
            wait(NULL);
            read(fd1[0], &B, sizeof(B));
            printf("Parent (PID %d): Received %d from child\n\n", getpid(), B);

            // C = Multiply(a1, b2)
            if(pipe(fd2) < 0){
                printf("pipe(fd2) failed");
                exit(1);
            }
            pid_t third = fork();
            if(third < 0){
                perror("fork(third) failed");
                exit(1);
            }
            if(third > 0) {
                write(fd2[1], &a1, sizeof(a1));
                printf("Parent (PID %d): Sending %d to child\n", getpid(), a1);
                write(fd2[1], &b2, sizeof(b2));
                printf("Parent (PID %d): Sending %d to child\n", getpid(), b2);
                wait(NULL);
                read(fd2[0], &C, sizeof(C));
                printf("Parent (PID %d): Received %d from child\n\n", getpid(), C);
                Y = (B+C)*100; // Y = (B+C)*10^2

                printf("###\n");
                printf("# Calculating Z\n");
                printf("###\n");
                // D = Multiply(a2, b2)
                if(pipe(fd3) < 0){
                    printf("pipe(fd3) failed");
                    exit(1);
                }
                pid_t fourth = fork();
                if(fourth < 0){
                    perror("fork(fourth) failed");
                    exit(1);
                }
                if(fourth > 0) {
                    write(fd3[1], &a2, sizeof(a2));
                    printf("Parent (PID %d): Sending %d to child\n", getpid(), a2);
                    write(fd3[1], &b2, sizeof(b2));
                    printf("Parent (PID %d): Sending %d to child\n", getpid(), b2);
                    wait(NULL);
                    read(fd3[0], &D, sizeof(D));
                    printf("Parent (PID %d): Received %d from child\n\n", getpid(), D);
                    Z = D; // Z = D*1
                    printf("%d*%d == %d + %d + %d == %d\n", atoi(argv[1]), atoi(argv[2]), X, Y, Z, X+Y+Z);
                }
                else{
                    int i, j;
                    read(fd3[0], &i, sizeof(i));
                    printf("\tChild (PID %d): Received %d from parent\n", getpid(), i);
                    read(fd3[0], &j, sizeof(j));
                    printf("\tChild (PID %d): Received %d from parent\n", getpid(), j);
                    int d = Multiply(i, j);
                    write(fd3[1], &d, sizeof(d));
                    printf("\tChild (PID %d): Sending %d to parent\n", getpid(), d);
                }
            }
            else{
                int i, j;
                read(fd2[0], &i, sizeof(i));
                printf("\tChild (PID %d): Received %d from parent\n", getpid(), i);
                read(fd2[0], &j, sizeof(j));
                printf("\tChild (PID %d): Received %d from parent\n", getpid(), j);
                int c = Multiply(i, j);
                write(fd2[1], &c, sizeof(c));
                printf("\tChild (PID %d): Sending %d to parent\n", getpid(), c);
            }
        }
        else{
            int i, j;
            read(fd1[0], &i, sizeof(i));
            printf("\tChild (PID %d): Received %d from parent\n", getpid(), i);
            read(fd1[0], &j, sizeof(j));
            printf("\tChild (PID %d): Received %d from parent\n", getpid(), j);
            int b = Multiply(i, j);
            write(fd1[1], &b, sizeof(b));
            printf("\tChild (PID %d): Sending %d to parent\n", getpid(), b);
        }
    }
    else{
        int i, j;
        read(fd0[0], &i, sizeof(i));
        printf("\tChild (PID %d): Received %d from parent\n", getpid(), i);
        read(fd0[0], &j, sizeof(j));
        printf("\tChild (PID %d): Received %d from parent\n", getpid(), j);
        int a = Multiply(i, j);
        write(fd0[1], &a, sizeof(a));
        printf("\tChild (PID %d): Sending %d to parent\n", getpid(), a);
    }

    return X+Y+Z;
}