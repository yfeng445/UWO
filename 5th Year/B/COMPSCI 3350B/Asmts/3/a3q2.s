exer2a:
    li $t0, 0          # i = 0
    lw $t1, 0($a0)     
loop_exer2a:
    beqz $t1, end_exer2a  # while (v!=0) loop
    move $a0, $t1         # v = exer2b(v)
    jal exer2b            
    move $t1, $v0         
    addi $t0, $t0, 1      # i++
    j loop_exer2a         
end_exer2a:
    move $v0, $t0         
    jr $ra                # return 

exer2b:
    addi $v0, $a0, 1    # t = v + 1
    srl $v0, $v0, 2     # t >> 2 
    jr $ra              # return

