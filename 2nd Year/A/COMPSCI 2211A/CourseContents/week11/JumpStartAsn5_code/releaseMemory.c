#include "headers.h"


//void releaseLinks( void* startNode )
void releaseList( CONTROL* myList )
{
    NODE *listNode;
    NODE *delNode;
    DTA *newData;

    << ENTER CODE HERE TO RELEASE ALL DYNAMIC ALLOCATED MEMORY
	   THE LOOP MUST CONTAIN THE FOLLOWING PRINT OUT
       printf("Memory for NODE: %d has been released. Current Stack Count: %d.\n",myList->nodeCount + 1,myList->nodeCount);
     >>


    printf("\nMemory for the control structure CONTROL has also been successfully released.\n");

    printf("\nAll Dynamically Allocated memory has been successfully released.\n\n");

}
