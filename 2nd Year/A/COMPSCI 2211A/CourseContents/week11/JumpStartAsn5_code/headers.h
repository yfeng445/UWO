#ifndef HEADERS_H_INCLUDED
#define HEADERS_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>

typedef struct dataStruct  // holds the data
{
    int iDataPos;
    char iValue;
} DTA;

typedef struct listNode // an individual node with a generic pointer to data
{
    void* dataPtr;
    int iPos;
    struct listNode* nextNode;
//    struct listNode* prev;
} NODE;

typedef struct  // control structure - retains address of first node in list
{
   int     nodeCount;
   struct listNode*   firstNode;
} CONTROL;



CONTROL* createControlStructure (void);
NODE* createNode (void* );
void populateList(  CONTROL*   );
void printList(  CONTROL*  );
void releaseList( CONTROL*  );



#endif // HEADERS_H_INCLUDED
