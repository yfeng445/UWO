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

/* Print a prompt. */
void prompt() { printf(">"); }

/* Read in a line into the buffer, if its capacity is not engough, increase its
 * capacity. Return the length of the line read. */
int getLine(char **buff, int *cap) {
    int c;          /* the character read from screen */
    int length = 0; /* length of the line */
    while (c = getchar(), c != '\n' && c != EOF) {
        if (length >= *cap - 1) {
            if (*cap == 0) {
                *cap = 16;
            } else {
                *cap += *cap / 2;
            }
            /* The buffer is not enough to hold the line, increase its size */
            /* Since we can't use realloc, ... */
            char *newBuff = (char *)malloc(sizeof(char) * *cap);
            assert(newBuff);
            memcpy(newBuff, *buff, sizeof(char) * length);
            free(*buff);
            *buff = newBuff;
        }
        (*buff)[length++] = c;
    }
    (*buff)[length] = '\0';
    return length;
}

/* The program main control. */
void begin() {
    char *buff = NULL; /* the line buffer */
    int cap = 0;       /* the capacity of the buffer */
    int length;        /* lenghth of the line */

    // Prompt the user for a paragrah
    printf("Please enter lines, enter an empty line to quit\n");
    paragraph *p = newParagraph(); /* the control structure */
    while (prompt(), (length = getLine(&buff, &cap)) > 0) {
        addSentence(p, newSentence(buff, length));
    }
    printParagraph(p);

    // Prompt the user for a word to search
    printf("Please enter a word to search, enter an empty line to quit\n");
    while (prompt(), (length = getLine(&buff, &cap)) > 0) {
        sentence *s = newSentence(buff, length);
        if (s->length == 1) {
            // Search the first word entered
            searchParagraph(p, s->first->str);
        } else {
            printf("Wrong input!\n");
        }
        freeSentence(s);
    }

    // Prompt the user for a line to delete
    printf("Please enter a line to delete, enter an empty line to quit\n");
    while (prompt(), (length = getLine(&buff, &cap)) > 0) {
        sentence *s = newSentence(buff, length);
        char *end;
        int line_no;
        if (s->length == 1 &&
            (line_no = strtol(s->first->str, &end, 10), *end == '\0')) {
            // Delete the first line entered
            deleteLine(p, line_no);
        } else {
            printf("Wrong input!\n");
        }
        freeSentence(s);
    }

    free(buff);
    freeParagraph(p);
}

int main() {
    begin();

    return 0;
}
