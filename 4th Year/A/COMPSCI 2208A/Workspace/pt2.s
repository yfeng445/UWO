	AREA pt2, CODE, READONLY
		ENTRY
		LDR R1, A
		LDR R2, B
		LDR R3, C
		LDR R4, D
		ADD R5, R1,R2
		MUL R6, R3, R4
		SUB R0, R5, R6
		END
	A	DCD 6
	B	DCD 4
	C	DCD 6
	D	DCD	7
