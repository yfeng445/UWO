#Steve mode
import re

inputfile = open("sometext.txt","r")
list = []
wordlist = []
for line in inputfile:
    print(line,end = "")
    line = line.lower().strip()
    wordlist = line.split()
    wordlist = re.findall(r'\w+',line)
    #remove all the punctuation from the text



"""import re
punctuation = '!,;:?"\'@/()$#&'
def removePunctuation(text):
    text = re.sub(r'[{}]+'.format(punctuation),'',text)
    return text.strip().lower()

infile = open("tweetwords.txt","r")
for line in infile:
    sentence = str(line)
    print(removePunctuation(sentence))"""
