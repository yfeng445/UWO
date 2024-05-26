/* CS2211a 2020 */
/* Assignment 05 */
/* Yulun Feng */
/* 251113989 */
/* yfeng445 */
/* 2020.11.26 */

#ifndef __WORD_H__
#define __WORD_H__

typedef struct word {
    char *str;
    int length;          /* the number of characters in the word */
    int pos_in_sentence; /* the position of the word in the sentence it is
                            contained in */
    struct word *next;
} word;

word *newWord(const char *str, int length, int pos_in_sentence);
void freeWord(word *w);

#endif  // __WORD_H__
