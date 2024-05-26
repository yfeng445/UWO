#include "headers.h"

int main()
{

	CONTROL* myList ;

	// Create and initialize a myList Control Structure in Dynamic Memory
	myList = createControlStructure();

	// add nodes to the end of the list
	populateList( myList );

	// print the list in the order they were entered
	printList( myList );

	// release all the dynamically allocated memory
    releaseList(  myList );

    return (0);

}
