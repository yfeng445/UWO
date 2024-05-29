		AREA myFirstExample,	CODE, READONLY
		ENTRY
		mov r0,#1
		mov r1,#2
		add r2,r1,r0
		add r2,r2,r2
		subs r1,r1,r1
loop	b loop

		
		END