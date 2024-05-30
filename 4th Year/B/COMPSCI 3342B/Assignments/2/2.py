"""
@ Author: Yulun Feng
@ ID: 251113989
@ Date: Feb 16, 2023
"""

import sys

str = sys.argv[1]
d = 0


def hasBalanced(inputStr):
    for i in range(len(inputStr)-1):
        if(inputStr[i] == '(' and inputStr[i+1] == ')' ):
            return True;
    return False


def rmBalanced(inputStr):
    str = inputStr
    hasBalance = True
    while(hasBalance):
        str = str.replace("()", "")
        hasBalance = hasBalanced(str)
    return str


simplestStr = rmBalanced(str)
L = 0
R = 0
for i in range(len(simplestStr)):
    if simplestStr[i] == ')':
        R += 1
    else:
        L += 1
d = int(L/2+R/2+L%2+R%2)

countL = 0
countR = 0

balancedStr = ""

for char in str:
    if char == ')' and countL == 0:
       balancedStr = balancedStr + ')'
       countR += 1
    if char == ')' and countL > 0:
       balancedStr = balancedStr + ')'
       countL -= 1
    if char == '(':
       balancedStr = balancedStr + '('
       countL += 1
    print("L:", countL, "R:", countR)
if (countL + countR)%2 == 1:
    if countL > countR:
        balancedStr = balancedStr.replace('(', '', 1)
    else:
        balancedStr = balancedStr.replace(')', '', 1)

if countL > countR:
    balancedStr = balancedStr.replace('(', ')', int(abs(countL-countR)/2))
else:
    balancedStr = balancedStr.replace(')', '(', int(abs(countL-countR)/2))

for i in range(0, len(balancedStr), 2):
    if (balancedStr[i] == '(' and balancedStr[i+1] == '(')\
            or (balancedStr[i] == ')' and balancedStr[i+1] == ')')\
            or (balancedStr[i] == ')' and balancedStr[i+1] == '('):
        balancedStr = balancedStr[:i] + "()" +balancedStr[i+2:]

print("d =", d, "\b, balanced string:", "\"", balancedStr, "\b\"")




