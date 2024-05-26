#include "headers.h"

/*	=================== createNode ====================
	Creates a new node structure in dynamic memory
	   Pre  itemPtr is pointer to data to be stored.
	   Post nodePtr created and its address returned.
*/
NODE* createNode (void* itemPtr)
{
	NODE* nodePtr;
	nodePtr = (NODE*) malloc (sizeof (NODE));
	nodePtr->dataPtr = itemPtr;
	nodePtr->nextNode    = NULL;

	return nodePtr;

}	// end of createNode
