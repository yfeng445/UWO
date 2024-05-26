cast = {"a","b","c","eiufgi"}
names = ["a","b","c","d","e","f"]
"""cast = set(names)"""
#you cannot cretate an empty set with {}
#instead,you can cast = st() to create an empty set

"""for characters in cast:
    print(characters)"""

"""for characters in sorted(cast):
    print(actor)"""

"""cast.remove("a")
cast.discard("a")
#discard works the same as remove
cast.add("x")"""

#examples on ch08

"""
issubset()
==
union() 并集
intersection() 交集
difference() 补集
"""

"""import re

infile = open("text.txt","r")

uniquewords = set()

for line in infile:
    wordlist = re.findall(r"\w+",line)
    for word in wordlist:
        uniquewords.add(word)

for word in sorted(uniquewords):
    print(word)
print(len(uniquewords))"""
#with a set, you can work much faster then list
