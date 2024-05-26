infile = open("rawdata.txt","r")
dic1 = {}
dic2 = {}
for lines in infile:
    lines = lines.replace(":"," ")
    lines = lines.split()

    countryname = ""
    for i in range(1,len(lines)-1):
        countryname = countryname+lines[i]+""
        countryname = countryname.upper()

    try:
        dic2[countryname[0]].append(countryname)
    except:
        dic2[countryname[0]] = []
        dic2[countryname[0]].append(countryname)
    dic1[countryname] = lines[-1]

print(dic1)
print(dic2)

while(True):
    word = input("Enter something (Enter quit to quit): ")
    word = word.upper()
    if word in dic1:
        print(word,":",dic1[word])
    elif word in dic2:
        print(word,":",dic2[word])
    elif word == "QUIT":
        break
    else:
        print("Does not exist")


