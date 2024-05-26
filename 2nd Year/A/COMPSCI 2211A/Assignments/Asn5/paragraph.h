/* CS2211a 2020 */
/* Assignment 05 */
/* Yulun Feng */
/* 251113989 */
/* yfeng445 */
/* 2020.11.26 */

#ifndef __PARAGRAPH_H__
#define __PARAGRAPH_H__

#include "sentence.h"

// The control structure
typedef struct {
    sentence *first;
    sentence *last;
    int length;
} paragraph;

paragraph *newParagraph();
void freeParagraph(paragraph *p);
void addSentence(paragraph *p, sentence *s);
void printParagraph(paragraph *p);
int searchParagraph(paragraph *p, const char *word_str);
int deleteLine(paragraph *p, int line_no);

#endif  // __PARAGRAPH_H__
