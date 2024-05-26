#list = [34,"toto",3.15,[1,2,3],"a"]
#list contains list create a matrix or a table, which is of a multi-dimension
#print(list)

"""list = [1,2,3,4,5,6,7,8,9,0]
list[2,3]
list[1:]
list[:4]
print()
"""


"""n = int(input(": "))
values = []
for i in range(n):
    values.append(i*i)
print(values)"""


"""matrix = [[1,0,0],
          [0,1,0],
          [0,0,1]]
for element in matrix:
    print(element)"""


"""values = [101,40,69,55,33,12,0,101,99,59,34]
found = False
position = 0
for number in values:
    if number > 100:
        found = True
        break
    else:
        position = position+1
    #position += 1
if found:
    print("Found")
    print("The position is: ",position)
else:
    print("Not found")"""


"""a = [5]
b = [10]

def swap(a,b):
    temp = b[0]
    b [0]= a[0]
    a [0]= temp
#the function will not affect the original constant, which makes a copy in the function
#because the contents are actually contain its memory loscation than the interger itself
# b[0] stand for the interger stored on the first place(10), rather the routine

#                       THIS IS SUPER IMPORTANT!!!!!!!!!

swap(a,b)
print("a = ",a)
print("b = ",b)
"""


"""mylist = [10,11,12,13,14]

def puttozero(l):
    for i in range(0,len(l)):
        l[i] = 0

print(mylist)
puttozero(mylist)
print(mylist)"""


