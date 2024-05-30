#include <stdio.h>
    
int t0, t1, t2, t3, t4, t5;
int *a0;
int *a1;

int exer1(a0, a1){
    t0 = *(&a0+0); //lw $t0, 0 ($a0)
    t1 = *(&a0+4); //lw $t1, 4 ($a0)
    t2 = *(&a1+0); //lw $t2 , 0 ($a1)
    t3 = *(&a1+4); //lw $t3 , 4 ($a1)
    if(t0<t2) t4 = 1; //slt $t4 , $t0 , $t2
    else t4 = 0;
    if(t1<t3) t5 = 1; //slt $t5 , $t1 , $t3
    else t5 = 0;
    t4 = t4 & t5; //and $t4 , $t4 , $t5
    if(t4 == 0) foo(); //beq $t4 , $0, foo
    t3 = t3-t2; //sub $t3 , $t3 , $t2
    bar(a0); //jal bar
}

void foo(){
    t3 = t2-t3; //sub $t3 , $t2 , $t3
}

void bar(a0){
    *(&t3+0) = a0; //sw $t3 , 0 ($a0)
}