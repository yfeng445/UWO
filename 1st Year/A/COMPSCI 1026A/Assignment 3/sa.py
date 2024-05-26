infile = open("tweetwords.txt","r")
keywords = open("keywords.txt","r")
def format(line):
        import re
        line = line.lower().strip()
        wordlist = line.split()
        wordlist = re.findall(r'\w+',line)
        return wordlist
        #this function can break tweets into single word

listkeywords = []
for lines in keywords:
    wordlist = format(lines)
    listkeywords.append(wordlist)
    #it will create a list which contains list


word = []
value = []
for list in listkeywords:
    string = list[0]
    number = int(list[1])
    word.append(string)
    value.append(number)
    #the keywords have beed converted into values


listpacific = []
listmountain = []
listcentral = []
listeastern = []
#create lists for different area

counteastern = 0
countcentral = 0
countmountain = 0
countpacific = 0
#countarea is the total number of the tweets in this area

def ktarea(infile):
    countarea = 0
    for line in infile:
        wordlist = format(line)
        longitude = float(str(wordlist[0])+"."+str(wordlist[1]))
        latitude = float(str(wordlist[2])+"."+str(wordlist[3]))
        if 24.660845<longitude<49.189787 and 87.518398>latitude>67.444574:
            counteastern = counteastern+1
        elif 24.660845<longitude<49.189787 and 101.998892>latitude>87.518398:
            countcentral = countcentral+1
        elif 24.660845<longitude<49.189787 and 115.236428>latitude>101.998892:
            countmountain = countmountain+1
        elif 24.660845<longitude<49.189787 and 125.242264>latitude>115.236428:
            countpacific = countpacific+1
    if not counteastern = 0:

for line in infile:
    wordlist = format(line)
    longitude = float(str(wordlist[0])+"."+str(wordlist[1]))
    latitude = float(str(wordlist[2])+"."+str(wordlist[3]))
    if 24.660845<longitude<49.189787 and 87.518398>latitude>67.444574:
        listeastern.append(wordlist)
        counteastern = counteastern+1
    elif 24.660845<longitude<49.189787 and 101.998892>latitude>87.518398:
        listcentral.append(wordlist)
        countcentral = countcentral+1
    elif 24.660845<longitude<49.189787 and 115.236428>latitude>101.998892:
        listmountain.append(wordlist)
        countmountain = countmountain+1
    elif 24.660845<longitude<49.189787 and 125.242264>latitude>115.236428:
        listpacific.append(wordlist)
        countpacific = countpacific+1
    #this loop can put words into lists which stand for different area

"""def double(infile):
    listpacific = []
    listmountain = []
    listcentral = []
    listeastern = []
    #create lists for different area

    counteastern = 0
    countcentral = 0
    countmountain = 0
    countpacific = 0
    #countarea is the total number of the tweets in this area

    for line in infile:
        wordlist = format(line)
        longitude = float(str(wordlist[0])+"."+str(wordlist[1]))
        latitude = float(str(wordlist[2])+"."+str(wordlist[3]))
        if 24.660845<longitude<49.189787 and 87.518398>latitude>67.444574:
            listeastern.append(wordlist)
            counteastern = counteastern+1
        elif 24.660845<longitude<49.189787 and 101.998892>latitude>87.518398:
            listcentral.append(wordlist)
            countcentral = countcentral+1
        elif 24.660845<longitude<49.189787 and 115.236428>latitude>101.998892:
            listmountain.append(wordlist)
            countmountain = countmountain+1
        elif 24.660845<longitude<49.189787 and 125.242264>latitude>115.236428:
            listpacific.append(wordlist)
            countpacific = countpacific+1
        #this loop can put words into lists which stand for different area
    return(listeastern,listcentral,listmountain,listpacific,counteastern,countcentral,countmountain,countpacific)"""

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
print(varea(listeastern))


nktarea = 0
def carea(listarea,nktarea):
   for list in listarea:
        for x in list:
            if x in word:
                nktarea+=1
   return(nktarea)
print(carea(listeastern,nktarea))

print(counteastern)



