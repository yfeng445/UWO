#include "headers.h"

/* Function to populate a linked list with letters of the alphabet */
void populateList(  CONTROL* myList  )
{
    NODE* listNode;
    DTA *newData;
    int iNodeTotals;
    NODE *currentNode;

	printf("\nEnter the size of list: ");
	scanf("%d", &iNodeTotals);

    for ( int i = 1; i <= iNodeTotals; i++)
    {
        newData = (DTA*)malloc (sizeof (DTA));
        newData->iDataPos = i;

        newData->iValue = i + 64;
        listNode = createNode(newData);
        listNode->iPos = i;

        printf("IN CREATE: NODE: %d with a value of %c\n", i, i + 64);

        << ENTER YOUR CODE HERE TO PLACE THE NEW NODE AT THE END OF THE LIST >>
		
    }
}
