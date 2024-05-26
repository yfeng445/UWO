"""alist = ["hello", "how","you"]
blist = alist
print(alist)
print(blist)
#alist and blist are a same list, so there's only one list in this file
alist[0] = "toto"
print(alist)
print(blist)
#the change in alist will also affect blist"""

"""alist = ["1","2","3"]
blist = list(alist)
print(alist)
print(blist)
alist[0] = "toto"
print(alist)
print(blist)
#in this condition, the change in alist will not affect the content of the blist
#so the list(xlist) makes a copy of xlist"""

#as for a list = ["1","2","3","4"], the list[1] is equal to list[-3]

alist = []
finished = False
while not finished:
    element = input("Enter a number: ")
    if element.isnumeric():
        alist.append(int(element))
    else:
        finish = True
print("Max is: ",max(alist))
print("Min is:",min(alist))
print("Sum is:",sum(alist))
alist.sort()
print(alist)
