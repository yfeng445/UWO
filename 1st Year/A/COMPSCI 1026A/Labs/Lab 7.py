keywords = open("keywords.txt","r",encoding="utf‐8")
tweets = input("Please enter your tweets: ")
def tweetsprocessed(line):
        import re
        wordlist = re.findall(r'\w+',line)
        return wordlist
listkeywords = []
for lines in keywords:
    wordlist = tweetsprocessed(lines)
    listkeywords.append(wordlist)
word = []
value = []
def wordvalue(word,value):
    for list in listkeywords:
        string = list[0]
        number = int(list[1])
        word.append(string)
        value.append(number)
    return (word,value)
wordvalue(word,value)
listt = tweetsprocessed(tweets)
v = 0
for x in value:
    if x == 10:
        value[value.index(x)] = -10
pos = []
neg = []
net = []
positive = []
negative = []
netural = []
for i in range(len(word)):
    if value[i-1] == -10:
        pos.append(word[i-1])
    if value[i-1] == 20:
        neg.append(word[i-1])
    if value[i-1] == 0:
        net.append(word[i-1])
v = 0
for x in listt:
    if x in pos:
        positive.append(x)
        v+=20
    if x in net:
        netural.append(x)
    if x in neg:
        negative.append(x)
        v-=10
print("The positive keywords are",positive)
print("The negative keywords are",negative )
print("The neutral keywords are",netural)
print("The sentiment of the tweet is",v)
for i in range(len(word)):
    print(word[i-1],"   ",value[i-1])

