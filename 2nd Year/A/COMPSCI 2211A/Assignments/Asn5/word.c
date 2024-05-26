/* CS2211a 2020 */
/* Assignment 05 */
/* Yulun Feng */
/* 251113989 */
/* yfeng445 */
/* 2020.11.26 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "word.h"

/* Create an word node and initialize it with the given arguments. */
word *newWord(const char *str, int length, int pos_in_sentence) {
    word *w = (word *)malloc(sizeof(word)); /* new word node */
    w->str = strdup(str);
    w->length = length;
    w->pos_in_sentence = pos_in_sentence;
    w->next = NULL;
    return w;
}

/* Free the word node. */
void freeWord(word *w) {
    free(w->str);
    free(w);
}
