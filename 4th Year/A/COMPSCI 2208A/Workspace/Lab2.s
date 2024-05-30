 AREA prog, CODE, READWRITE
        ENTRY
        LDR SP, =STACK
        MOV r0, #0x11
        MOV r1, #0x22
PUSH_R0 STR R0,[SP,#-4]! 
PUSH_R1 STR R1, [SP, #-4]!
POP_R0  LDR R2, [SP], #4 
POP_R1  LDR R3, [SP], #4 
Loop B Loop
      space 32
STACK DCD 0x0
      space 32
     END