exe r1 :
    lw $t0, 0 ($a0)
    lw $t1, 4 ($a0)
    lw $t2, 0 ($a1)
    lw $t3, 4 ($a1)
    slt $t4, $t0, $t2
    slt $t5, $t1, $t3
    and $t4, $t4, $t5
    beq $t4, $0, foo
    sub $t3, $t3, $t2
    j bar

foo :
    sub $t3, $t2, $t3
    
bar :
    sw $t3, 0 ($a0)
    jr $ra
