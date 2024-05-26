/* CS2211a 2020 */
/* Assignment 05 */
/* Yulun Feng */
/* 251113989 */
/* yfeng445 */
/* 2020.11.26 */

#ifndef __SENTENCE_H__
#define __SENTENCE_H__

#include "word.h"

typedef struct sentence {
    word *first;
    int length;  /* the exact number of words in this line */
    int line_no; /* the which line this is in the total collection */
    struct sentence *next;
} sentence;

sentence *newSentence(char *line, int length);
void freeSentence(sentence *s);

#endif  // __SENTENCE_H__
