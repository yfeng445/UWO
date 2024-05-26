a = int(input("input the first number"))
b = int(input("input the second number"))
c = int(input("input the third number"))

if a>b>c:
    print("If the input is ",a,"  ",b,"  ",c,"  ","your program should output ascending")
elif a<b<c:
    print("If the input is ",a,"  ",b,"  ",c,"  ","your program should output descending")
else:
    print("If the input is ", a, "  ", b, "  ", c, "  ", "your program should output not in order")