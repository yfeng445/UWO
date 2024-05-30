	AREA Asmt1, CODE, READONLY
	ENTRY
	ADR R1, UPC3
	MOV R11, #0;define counter
	MOV R10, #0;define 1st sum
	MOV R9, #0; define 2nd sum
	LDRB R2, [R1, #11]; get Check Number
	SUB R2, #48; convert check number from ASCII to HEX
Loo 
	LDRB R3, [R1,R11];get the first value in string
	TST R11, #1; test it is odd or not
	BNE Odd
	ADD R9, R9, R3; accumulate the values to the first sum
	SUB R9, R9, #48; convert value from ASCII to HEX 
	B Cont;
Odd 
	ADD R10, R10, R3; accumulate values to the second sum
	SUB R10, R10, #48; convert value from ASCII to HEX 
Cont 
	ADD R11, R11, #1; update the counter
	CMP R11, #11; check if the loop is done or not
	BEQ Next
	B Loo
Next 
	MOV r7, #3; data definition
	MLA R8, R9, R7, R10; 
	ADD R8, R2; get the sum
Lo	CMP R8,#10; check if it equal to 10
	BLT Invalid; the value is less than 10; it cannot be a multiple of 10
	BEQ Valid;
	SUB R8, #10; the value is too large, go to the next loop
	BGT Lo
Invalid 
	MOV R0, #1; data definition in Invalid case
	B Continue
Valid 
	MOV R0, #2; data definition in Valid case
Continue

Loop B Loop

UPC DCB "013800150738" ;correct UPC string
UPC2 DCB "060383755577" ;correct UPC string
UPC3 DCB "065633454712" ;correct UPC string 
	 END