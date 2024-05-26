class CountryCatalogue:
    from country import Country

    def __init__(self,countryFile):
        infile = open(countryFile,"r")
        countryCat = []
        import re
        for lines in infile:
            lines = lines.replace("\n","").split("|")
            countryCat.append(lines)
        self._countryFile = countryCat
        CountryCatalogue._countryFile = countryCat


    def setPopulationOfCountry(self,country,Population):
        self._Population = Population
        for lists in self._countryFile:
            if lists[0] == country:
                lists[2] = Population

    def setAreaOfCountry(self,country,Area):
        self._Area = Area
        for lists in self._countryFile:
            if lists[0] == country:
                lists[3] = Area

    def setContinentOfCountry(self,country,Continent):
        self._Continent = Continent
        for lists in self._countryFile:
            if lists[0] == country:
                lists[1] = Continent

    def findCountry(self,country):
        find = 0
        for lists in self._countryFile:
            if lists[0] == country:
                print(lists)
                find = 1
            if find == 0:
                print("NONE")

    def addCountry(self,countryName,pop,area,cont):
        countryName = countryName
        for lists in self._countryFile:
            if lists[0] == countryName:
                print("False")
                return False
            else:
                self._countryFile.append([countryName,cont,pop,area])
                print("True")
                return True

    def printCountryCatalogue(country):
        super().__repr__()
        string = CountryCatalogue._countryFile
        print(string)
        return

    def saveCountryCatalogue(self,fname):
        file = open(fname,"w")
        try:
            count = 0
            for lists in self._countryFile:
                file.write(str(lists).replace("[","").replace("]","").replace("'","").replace(", ","|"))
                file.write("\n")
                count+=1
            return "The number added to the file is: ",count
        except Exception as error:
            return -1

c = CountryCatalogue("data.txt")
c.printCountryCatalogue()




