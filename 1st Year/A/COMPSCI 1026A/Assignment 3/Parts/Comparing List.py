"""alist = []
blist = []

astring = "a a a a a bwa weih ewiaei awoihfo ed edned ed ed "
a = astring.split()
for word in a:
    alist.append(word)
bstring = "bwa widn a ed"
b = bstring.split()
for word in b:
    blist.append(word)

n = 0
for i in range(len(blist)):
    for i in range(len(alist)):
        m = 0
        counter = 0
        if str(blist[n]) == str(alist[m]):
            counter = counter+1
            print(counter)
        m = m+1
        print(m)
    print(blist[n],": ",counter)
    n = n+1"""


"""for i in range(len(d)):
    n = 0
    listn.append(d[n])
    n+=1
print(listn)"""


"""import collections
lst = []    # lst存放所谓的100万个元素
d = collections.Counter(lst)
"""

"""List = [1,2,3,4,5,3,2,1,4,5,6,4,2,3,4,6,2,2]
List_set = set(List) #List_set是另外一个列表，里面的内容是List里面的无重复项
for item in List_set:
    print("the %d has found %d" %(item,List.count(item)))"""

listA = [1,1,2,2,3,3,3,4]
listB = [2,4]
lista = []
listb = []
def diff(listA,listB):
    retA = [i for i in listA if i in listB]
    retB = list(set(listA).intersection(set(listB)))

print(diff(listA,listB))



"""lista = lista in diff(listA,listB)
def times(list):
    List_set = set(list)
    for item in List_set:
        print("the %d has found %d" %(item,list.count(item)))
times(lista)
times(listb)
"""
