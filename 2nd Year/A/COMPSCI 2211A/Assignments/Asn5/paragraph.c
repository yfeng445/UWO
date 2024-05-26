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

#include "paragraph.h"

/* Create an empty paragraph. */
paragraph *newParagraph() {
    /* the new paragraph pointer */
    paragraph *p = (paragraph *)calloc(1, sizeof(paragraph));
    assert(p);
    return p;
}

/* Free the paragraph. */
void freeParagraph(paragraph *p) {
    sentence *s = p->first; /* sentence node in paragraph */
    while (s) {
        p->first = s->next;
        freeSentence(s);
        s = p->first;
    }
    free(p);
}

/* Add a sentence to the end of the paragraph. */
void addSentence(paragraph *p, sentence *s) {
    if (!p->first) {
        p->first = s;
        p->last = s;
    } else {
        p->last->next = s;
        p->last = s;
    }
    s->line_no = ++p->length;
}

/* Print the paragraph. */
void printParagraph(paragraph *p) {
    sentence *s = p->first; /* sentence node in paragraph */
    while (s) {
        printf("#%d", s->line_no);
        word *w = s->first; /* word node in the sentence */
        while (w) {
            printf(" %s", w->str);
            w = w->next;
        }
        printf("\n");
        s = s->next;
    }
}

/* Search all occurrences of the given word in the paragraph. Return number of
 * occurrences found.  */
int searchParagraph(paragraph *p, const char *word_str) {
    int cnt = 0;
    sentence *s = p->first; /* sentence node in paragraph */
    while (s) {
        word *w = s->first;/* word node in the sentence */
        while (w) {
            if (strcmp(w->str, word_str) == 0) {
                printf("found \"%s\" at %d:%d\n", word_str, s->line_no,
                       w->pos_in_sentence);
                ++cnt;
            }
            w = w->next;
        }
        s = s->next;
    }
    if (!cnt) {
        printf("word \"%s\" is not found!\n", word_str);
    }
    return cnt;
}

/* Delete the given line in the paragraph. Return -1 if no such line, return 0
 * if deleted. */
int deleteLine(paragraph *p, int line_no) {
    if (line_no > p->length) {
        printf("No such line!\n");
        return -1;
    }

    sentence *s = p->first; /* sentence node in paragraph */
    sentence *next;         /* next sentence node of the deleted node */
    if (s->line_no == line_no) {
        // delete the first line
        p->first = s->next;
        next = s->next;
        freeSentence(s);
    } else {
        while (s->next && s->next->line_no != line_no) {
            s = s->next;
        }

        if (s->next) {
            // delete next line
            next = s->next->next;
            freeSentence(s->next);
            s->next = next;
        }
    }

    // Change the line_no of all the nodes after the deleted node
    while (next) {
        --next->line_no;
        next = next->next;
    }
    --p->length;

    printf("Line %d has been deleted!\n", line_no);

    printParagraph(p);

    return 0;
}
