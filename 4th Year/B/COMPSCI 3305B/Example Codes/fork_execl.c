#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
 

/*
  Simple program that uses execl

*/

int main()
{

  int status;
  pid_t pid;
  
  pid = fork();

  	if (pid>0) // parent
  	{
  	printf("Hello from parent...\n");
  	wait(NULL);
  	printf("\nHello from parent after child is completed\n");
	}

	if (pid == 0) //child
	{
  	printf("Hello from child...going to call execl now..\n");
  	status = execl("external", "p1", "p2", NULL);
  	//status = execl("/home/ahaque/3305/external", "", NULL);
  	if (status < 0)
    	printf("\n From child: execl failed");
	printf("\n From child after execl call");
  	}    

}
