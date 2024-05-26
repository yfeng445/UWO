"""#this program could fighre out how many unique words are there in a textline
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
    for word in wordlist:
        if word not in list:
            list.append(word)
            #add a word to the list if the word is not in the list
            #with a binary tree, things will become much easier
print(list)
print(len(list))

inputfile.close()"""


"""#here is an exception:
try:
    infile = open("text3.txt","r")
    #process the data
    infile.close()
    #if the open failed, it will right print sth
    #if the file exist, it will finish with exit code 0
    

except Exception as error:
    #if the try comes out error, the program will prerform the following of the excepttion
    #exception is the output of the python when it is crashed, and it can be save as a string
    print(str(error))
except IOError:
    print("LOL")
except RuntimeError:
    print("It is a runtime error")"""

"""finally:
    infile.close()
    #no matter what happen, either try or except works, the program will try to close the file"""


def readdata(infile):
    line = infile.readline()
    number = int(line)
    deta = []
    for i in range(number):
        line = infile = infile.readline()
        value = int(value)
        data.append(value)

    line = infile.readline()
    if line !="":
        raise Runtimeerror("End of file expected")
    return data

