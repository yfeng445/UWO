"""def merge1(list1,list2):
    list3 = []
    while len(list1)>0 and len(list2)>0:
        if list1[0]<list2[0]:
            list3.append(list1.pop(0))
        else:
            list3.append(list2.pop(0))
        # the pop function will move the element from list1/2 to list 3
        # so the original [0] position will be the next element
    list3 = list3+list1+list2
    return(list3)


list1 = [1,1,2,3,13,16]
list2 = [4,5,7,8,9,14,19,27,36,43]
print(merge1(list1,list2))


def merge2(list1,list2):
    return sorted(list1+list2)
#this function works exactly the same as the merge1 function
"""

list = [1,1,1,1,1,2,2,2,2,3,4,4,5,5,5,5,5,5]
"""def count(list):
    lst = []
    counter = []
    for element in list:
        if not element in lst:
            counter.append(list.count(element))
            lst.append(element)
    return(lst,counter)
print(count(list[0]))
print(count(list[1]))"""



def frequency(list):
    item = []
    freq = []

    for i in range(0,len(list)):
        if list[i] not in item:
            item.append(list[i])
            counter = 0
            for element in list:
                if element == list[i]:
                    counter+=1
            freq.append(counter)
    return (item,freq)
a,b = frequency(list)
print(frequency(list)[0])
print(frequency(list)[1])
print(a)
print(b)
