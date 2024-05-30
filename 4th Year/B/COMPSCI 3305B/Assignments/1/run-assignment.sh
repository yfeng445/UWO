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
d165a1e541b2fda394b4c96bfb3cbd1b  checkPassword.o
29cc5c2deb1bb1288b7d97e7f76e1a17  hackme
5aebbf06316d7bbec571b7f4471cd646  Makefile
0f8afbea00103006936b30c0c8f42949  checkPassword.h' || exit 1;

echo
echo "Building environment"
echo "----------------------------"
make || exit 1;

echo
echo "Assignment ${ASSIGNMENT} (without forking)"
echo "----------------------------"
start=$(date +%s)
./assignment-${ASSIGNMENT} &
sleep 1
pstree -ap $!
while [ $(pidwait -x assignment-${ASSIGNMENT}) ]; do : ; done;
end=$(date +%s)
echo $(($end-$start)) "Seconds"

echo
echo "Assignment ${ASSIGNMENT} (with forking)"
echo "-------------------------"
start=$(date +%s)
./assignment-${ASSIGNMENT} -f &
sleep 1
pstree -ap $!
while [ $(pidwait -x assignment-${ASSIGNMENT}) ]; do : ; done;
end=$(date +%s)
echo $(($end-$start)) "Seconds"

echo
read -p 'Enter your guess: ' guess;
echo "Trying: ${guess}"
./hackme <<< ${guess}
if [ $? -ne 0 ]; then
    echo "ASSIGNMENT ${ASSIGNMENT} FAILED - $(date)"
    exit 1
fi;

echo
echo "Cleaning environment"
echo "----------------------------"
make clean

echo
echo "ASSIGNMENT ${ASSIGNMENT} COMPLETED - $(date)"
