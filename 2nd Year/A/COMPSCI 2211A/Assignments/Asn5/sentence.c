/* CS2211a 2020 */
/* Assignment 05 */
/* Yulun Feng */
/* 251113989 */
/* yfeng445 */
/* 2020.11.26 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <assert.h>

#include "sentence.h"

/* Create a sentence node containing words in the given line. */
sentence *newSentence(char *line, int length) {
    int i;              /* iteration var */
    int haveWord = 0;   /* if there is a word now */
    int wordBegin = -1; /* the begin index of that word */
    sentence *s = (sentence *)malloc(sizeof(sentence)); /* new sentence node */
    s->first = NULL;
    s->length = 0;
    s->line_no = -1;
    s->next = NULL;
    word **word = &s->first; /* the current word node */
    for (i = 0; i < length; i++) {
        if (isblank(line[i])) {
            if (haveWord) {
                line[i] = '\0';
                *word = newWord(&line[wordBegin], i - wordBegin, wordBegin + 1);
                word = &(*word)->next;
                ++s->length;
                haveWord = 0;
            }
        } else {
            if (!haveWord) {
                haveWord = 1;
                wordBegin = i;
            }
        }
    }
    if (haveWord) { /* deal with the last word if any */
        *word = newWord(&line[wordBegin], i - wordBegin, wordBegin);
        ++s->length;
    }
    return s;
}

/* Free the sentence. */
void freeSentence(sentence *s) {
    word *word = s->first; /* word node in the sentence */
    while (word) {
        s->first = word->next;
        freeWord(word);
        word = s->first;
    }
    free(s);
}
