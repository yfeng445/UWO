from catalogue import CountryCatalogue


def unsuccessfulOutput():
    output = open("output.txt",'w')
    output.write("Update Unsuccessful\n")
    output.close()
    #function for unsuccessful opening

def processfile(line):
    for i in line:
        index = line.index(i)
        i = i.replace(' ','')
        line[index] = i
    return line


def addupdate(line,list,str):
    string = 0
    for i in line:
        if str in i:
            string = 1
            PAC = i[2:]
            list.append(PAC)
    if string ==0:
        list.append('')
    #add new information

def update(line,dictionary,str,b):
    for i in line:
        if str in i:
            PAC = i[2:]
            dictionary[line[0]][b] = PAC
    #update the data

def processUpdates(cntryFileName, updateFileName):
    success = 1
    option = 1
    while True:
        try:
            open(cntryFileName, "r")
            success = 0
        except IOError:
            #process the error condition
            quit = input("Enter N/n to continue")
            if quit.lower() == "n" :
                cntryFileName = input("Enter name of file with country data: ")
                continue
            else:
                unsuccessfulOutput()
                return False
        else:
            k = CountryCatalogue(cntryFileName)
            success = 0
            break
    #process the cntryfile

    while True:
        try:
            open(updateFileName, "r")
        except IOError:
            #process the error condition
            quit = input("Enter N/n to continue")
            if quit.lower() == "n" :
                updateFileName = input("Enter name of file with country updates: ")
                continue
            else:
                output = open("output.txt", "w")
                output.write("Update Unsucessful\n")
                output.close()
                return False
        else:
            updates = open(updateFileName, "r")
            break
    #process the update file

    if option == 0:
        invalidfile = open('output.txt', 'w', encoding = 'utf-8')
        invalidfile.write("UpdateUnsuccessful\n")
    elif option == 1:
        list = CountryCatalogue(cntryFileName)
        file = open(updateFileName, 'r', encoding='utf-8')
        for lines in file:
            listforNewcountry = []
            lines = lines.rstrip()
            lines = lines.split(';')
            processfile(lines)
            if lines[0] in list.CountryCat:
                update(lines,list.CountryCat,'C=',0)
                update(lines,list.CountryCat,'P=',1)
                update(lines,list.CountryCat,'A=',2)
            elif lines[0] not in list.CountryCat:
                addupdate(lines,listforNewcountry,'C=')
                addupdate(lines,listforNewcountry,'P=')
                addupdate(lines,listforNewcountry,'A=')
                list.CountryCat[lines[0]] = listforNewcountry
                sorted(list.CountryCat.keys())
                listforNewcountry = []


    sorted(list.CountryCat.keys())
    #sorted the countryfile
    a = list
    a.saveCountryCatalogue("output.txt")
    a.printCountryCatalogue()
    return list.CountryCat




