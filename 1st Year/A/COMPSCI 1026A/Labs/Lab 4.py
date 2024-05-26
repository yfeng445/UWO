n = int(input("How many numbers do you want to use today? "))
m = 0
alist = []
while (True):
    if n>m:
        element = input("Enter the number: ")
        alist.append(int(element))
        m = m+1
    else:
        print("The average of the values is ", sum(alist)/len(alist))
        print("The smallest of the values is ", int(min(alist)))
        print("The largest of the values is ",int(max(alist)))
        print("The range of the values is ",int(max(alist))-int(min(alist)))
        break

