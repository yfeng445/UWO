#!/bin/bash
#
# Run this script with the following command (N is the assignment #):
#   script -c 'run-assignment.sh N' assignment-N.out
#
ASSIGNMENT=${1}
clear

echo
echo "ASSIGNMENT ${ASSIGNMENT} STARTED - $(date)"

echo
echo "Cleaning environment"
echo "----------------------------"
make clean

echo
echo "Checking environment"
echo "----------------------------"
md5sum ${0}
md5sum assignment-${ASSIGNMENT}.c || exit 1;
md5sum -c <<<'
96330c4ddccfbd59297379d8622ae3b8  Makefile
df6db9be442b8decd0f4edfb724cca2c  assignment-4-input.csv' || exit 1;

echo
echo "Building environment"
echo "----------------------------"
make || exit 1;

echo
echo "Assignment ${ASSIGNMENT}"
echo "----------------------------"
echo
echo "-- Invalid parameters (0) --"
sleep 3
./assignment-${ASSIGNMENT}
echo
echo "-- Invalid parameters (4+) --"
sleep 3
./assignment-${ASSIGNMENT} 1 2 3 4
echo
echo "-- Invalid filename --"
sleep 3
./assignment-${ASSIGNMENT} -f file-does-not-exist.txt
echo
echo "-- First Come First Served --"
sleep 3
./assignment-${ASSIGNMENT} -f assignment-4-input.csv
echo
echo "-- Shortest Job First --"
sleep 3
./assignment-${ASSIGNMENT} -s assignment-4-input.csv
echo
echo "-- Round Robin (Time Quantum 3) --"
sleep 3
./assignment-${ASSIGNMENT} -r 3 assignment-4-input.csv
echo
echo "-- Round Robin (Time Quantum 12) --"
sleep 3
./assignment-${ASSIGNMENT} -r 12 assignment-4-input.csv
sleep 3

echo
echo "Cleaning environment"
echo "----------------------------"
make clean

echo
echo "ASSIGNMENT ${ASSIGNMENT} COMPLETED - $(date)"
