CC=gcc
CFLAGS=-Wall -Wpedantic -Wextra -std=gnu17

default:
	make all

all: checkPassword.o assignment-1.o
	$(CC) -o assignment-1 checkPassword.o assignment-1.o $(CFLAGS)

assignment-1.o: assignment-1.c
	$(CC) -c assignment-1.c $(CFLAGS)

clean:
	rm -f assignment-1.o assignment-1

