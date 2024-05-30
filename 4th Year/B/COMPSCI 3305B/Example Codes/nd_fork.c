#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main()
{ 
    pid_t pid;          
    int i;
    pid = fork();
    if( pid > 0 ) 
    {	
	   /* parent */
	
        for( i=0; i < 100; i++ ) 
            printf("\t\t\t PARENT %d\n", i);
     }

 else 
 {   		
 	/* child */
      for( i=0; i < 100; i++ )       
	   printf( "\nCHILD %d", i );  
 }
 return 0;
}

