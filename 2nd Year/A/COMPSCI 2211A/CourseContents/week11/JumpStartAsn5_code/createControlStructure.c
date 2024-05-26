#include "headers.h"

CONTROL* createControlStructure (void)
{
//	Local Definitions
	CONTROL* myList;

//	Statements
	myList = (CONTROL*) malloc( sizeof (CONTROL));
	if (myList)
	   {
	    myList->nodeCount = 0;
	    myList->firstNode   = NULL;
	   } // if
	return myList;
}	// createControlStructure
