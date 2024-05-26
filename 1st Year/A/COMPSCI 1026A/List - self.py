list=[]
s = 0
while(True):
    a = input ("?")
    if str(a) == "q":
        break
    else:
        list.append(a)
        s = s+1
c = 0
while c<s:
    print(list[c],c+1)
    c = c+1

