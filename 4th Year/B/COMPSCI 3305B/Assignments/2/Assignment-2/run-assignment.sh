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
0fbeb8ec1ea67bf95720438478ca5687  Makefile' || exit 1;

echo
echo "Building environment"
echo "----------------------------"
make || exit 1;

echo
echo "Assignment ${ASSIGNMENT}"
echo "----------------------------"
./assignment-${ASSIGNMENT} 1 2
./assignment-${ASSIGNMENT} 1234 5678 90
./assignment-${ASSIGNMENT} 1234 5678
./assignment-${ASSIGNMENT} 9999 9999
./assignment-${ASSIGNMENT} 1000 1000

echo
echo "Cleaning environment"
echo "----------------------------"
make clean

echo
echo "ASSIGNMENT ${ASSIGNMENT} COMPLETED - $(date)"
