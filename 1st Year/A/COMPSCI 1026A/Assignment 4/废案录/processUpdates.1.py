

def processUpdates(cntryFileName,updateFileName):
    from catalogue import CountryCatalogue
    from country import Country
    def fail():
        output = open("output.txt","w")
        output.write("Update Unsuccessful\n")


    while(True):
        try:
            open(cntryFileName,"r")
        except IOError:
            print("File does not exist.")
            option = input("Enter Y/N to determine quit or not: ")
            if option == "N":
                cntryFileName = input("Enter name of file with country data: ")
                continue
                #执行最近的loop
            else:
                fail()
                return False

        else:
            cc = CountryCatalogue(cntryFileName)
            break

    while(True):
        try:
            open(updateFileName,"r")
        except IOError:
            print("File does not exist.")
            option = input("Enter Y/N to determine quit or not: ")
            if option == "N":
                updateFileName = input("Enter name of file with update data: ")
                continue
                #执行最近的loop
            else:
                fail()
                return False
        else:
            updates = open(updateFileName,"r")
            break

    for line in updates:
        line = line.strip().split(";")
        pop = ""
        area = ""
        cont = ""
        for info in line:
            info = info.strip()
            if info.startswith("P="):
                pop = info.strip("P=")
            elif info.startswith("A="):
                area = info.strip("A=")
            elif info.startswith("C="):
                cont = info.strip("C=")

        c = Country(line[0],"","","")
        if not cc.findCountry(c):
            cc.addCountry(line[0],pop,area,cont)
        else:
            if not pop == "":
                cc.setPopulationOfCountry(line[0],pop)
            if not area == "":
                cc.setAreaOfCountry(line[0],area)
            if not cont == "":
                cc.setContinentOfCountry(line[0],cont)

    cc.saveCountryCatalogue("output.txt")
    return True





