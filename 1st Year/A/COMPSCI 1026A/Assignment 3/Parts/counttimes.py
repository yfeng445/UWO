list1 = [[1,2,3,],[2,3,3],[4,5,6,2],[4,6,1]]
st = [1,2,4]
for i in st:
    count = 0
    for list in list1:

        if i in list:
            count+=1
    print("word: ",i,"   times: ",count)

