infile = open("tweetwords.txt","r",encoding="utf‐8")
keywords = open("keywords.txt","r",encoding="utf‐8")
#input words

# 1
def tweetsprocessed(line):
        import re
        line = line.lower().strip()
        wordlist = line.split()
        wordlist = re.findall(r'\w+',line)
        return wordlist
        #this function can break tweets into single word

listkeywords = []
#需要提前定义
for lines in keywords:
    wordlist = tweetsprocessed(lines)
    listkeywords.append(wordlist)
    #it will create a list which contains list
    #这一步可直接用于主程序，不用函数化


# 2
word = []
value = []
#需要提前定义
def wordvalue(word,value):
    for list in listkeywords:
        string = list[0]
        number = int(list[1])
        word.append(string)
        value.append(number)
        #the keywords have beed converted into values
    return (word,value)
wordvalue(word,value)


# 3
def listarea(infile):
    listpacific = []
    listmountain = []
    listcentral = []
    listeastern = []
    for line in infile:
        wordlist = tweetsprocessed(line)
        longitude = float(str(wordlist[0])+"."+str(wordlist[1]))
        latitude = float(str(wordlist[2])+"."+str(wordlist[3]))
        if 24.660845<longitude<49.189787 and 87.518398>latitude>67.444574:
            listeastern.append(wordlist)
        elif 24.660845<longitude<49.189787 and 101.998892>latitude>87.518398:
            listcentral.append(wordlist)
        elif 24.660845<longitude<49.189787 and 115.236428>latitude>101.998892:
            listmountain.append(wordlist)
        elif 24.660845<longitude<49.189787 and 125.242264>latitude>115.236428:
            listpacific.append(wordlist)
    return([listpacific,listmountain,listcentral,listeastern])
listall = listarea(infile)
listpacific = listall[0]
listmountain = listall[1]
listcentral = listall[2]
listeastern = listall[3]
#数据接收


# 4
infile0 = open("tweetwords.txt","r",encoding="utf‐8")
def countarea(file):
    counteastern = 0
    countcentral = 0
    countmountain = 0
    countpacific = 0
    for line in file:
        listword = tweetsprocessed(line)
        x = float(str(listword[0])+"."+str(listword[1]))
        y = float(str(listword[2])+"."+str(listword[3]))
        if 24.660845<x<49.189787 and 87.518398>y>67.444574:
            counteastern = counteastern+1
        elif 24.660845<x<49.189787 and 101.998892>y>87.518398:
            countcentral = countcentral+1
        elif 24.660845<x<49.189787 and 115.236428>y>101.998892:
            countmountain = countmountain+1
        elif 24.660845<x<49.189787 and 125.242264>y>115.236428:
            countpacific = countpacific+1
    return([counteastern,countcentral,countmountain,countpacific])
countarealist = countarea(infile0)


# 5
def varea(listarea):
    areaall = []
    for list in listarea:
        for x in list:
            if not x.isnumeric():
                areaall.append(x)
    area = 0
    for str in areaall:
       if str in word:
            p=int(word.index(str))
            area = area+p
    return(area)


# 6
def carea(listarea):
   keywordnumber = 0
   for list in listarea:
        for x in list:
            if x in word:
                keywordnumber+=1
   return(keywordnumber)



tupeastern = (varea(listeastern),carea(listeastern),countarealist[0])
tupcentral = (varea(listcentral),carea(listcentral),countarealist[1])
tupmountain = (varea(listmountain),carea(listmountain),countarealist[2])
tuppacific = (varea(listpacific),carea(listpacific),countarealist[3])

print(tupeastern)
print(tupcentral)
print(tupmountain)
print(tuppacific)






