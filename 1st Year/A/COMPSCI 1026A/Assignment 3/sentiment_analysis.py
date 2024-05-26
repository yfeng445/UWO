infile = open("tweetwords.txt","r")
keywords = open("keywords.txt","r")
#input words


def compute_tweets(line):
        import re
        line = line.lower().strip()
        wordlist = line.split()
        wordlist = re.findall(r'\w+',line)
        return wordlist
        #this function can break tweets into single word

listkeywords = []

for lines in keywords:
    wordlist = compute_tweets(lines)
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

for line in infile:
    wordlist = compute_tweets(line)
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


pacificall = []
for list in listpacific:
    for x in list:
        if not x.isnumeric():
            pacificall.append(x)
pacific = 0
for str in pacificall:
   if str in word:
        p=int(word.index(str))
        pacific = pacific+p


mountainall = []
for list in listmountain:
    for x in list:
        if not x.isnumeric():
            mountainall.append(x)
mountain = 0
for str in mountainall:
   if str in word:
        p=int(word.index(str))
        mountain = mountain+p


centralall = []
for list in listcentral:
    for x in list:
        if not x.isnumeric():
            centralall.append(x)
central = 0
for str in centralall:
   if str in word:
        p=int(word.index(str))
        central = central+p


easternall = []
for list in listeastern:
    for x in list:
        if not x.isnumeric():
            easternall.append(x)
eastern = 0
for str in easternall:
   if str in word:
        p=int(word.index(str))
        eastern = eastern+p


ceastern = 0
ccentral = 0
cmountain = 0
cpacific = 0
#carea is the number of keyword tweets in theese area

for list in listeastern:
    for x in list:
        if x in word:
            ceastern+=1


for list in listcentral:
    for x in list:
        if x in word:
            ccentral+=1

for list in listmountain:
    for x in list:
        if x in word:
            cmountain+=1

for list in listpacific:
    for x in list:
        if x in word:
            cpacific+=1

tupeastern = (eastern,ceastern,counteastern)
tupcentral = (central,ccentral,countcentral)
tupmountain = (mountain,cmountain,countmountain)
tuppacific = (pacific,cpacific,countpacific)

"""print(tupeastern)
print(tupcentral)
print(tupmountain)
print(tuppacific)"""
print("Happiness score in eastern is: ",eastern)
print("Number of tweets: ",counteastern)
print("Number of keyword tweets: ",ceastern)
print("")
print("Happiness score in central is: ",central)
print("Number of tweets: ",countcentral)
print("Number of keyword tweets: ",central)
print("")
print("Happiness score in mountain is: ",mountain)
print("Number of tweets: ",countmountain)
print("Number of keyword tweets: ",cmountain)
print("")
print("Happiness score in pacific is: ",pacific)
print("Number of tweets: ",countpacific)
print("Number of keyword tweets: ",cpacific)

