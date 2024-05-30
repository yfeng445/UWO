#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

/*  
This program forks a process.  The child and parent processes print to terminal to identify themselves.
*/

int main ()
{
	pid_t pid;

	pid=fork();

	if (pid <0) // fork unsuccessful
 	{
   		printf("fork unsuccessful");
		exit(1);
 	}

	if (pid>0) //parent
  		printf("I am parent\n");
	
	//wait(NULL);

	if (pid==0) //child
	  	printf("I am a child\n");
       
return 0;
}
