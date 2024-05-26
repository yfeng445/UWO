class CountryCatalogue:
    import country

    def __init__(self,countryFile):
        self.data = countryFile
        self.countryCat = []
        data = open(self.data,"r")
        for line in data:
            line = line.strip().split("|")
            country = Country(line[0],line[2],line[3],line[4])
            self.countryCat[line[0]] = country
        data.close()


    def setPopulationOfCountry(self,country,Population):
        self._Population = Population
        for lists in self._countryFile:
            if lists[0] == country:
                lists[2] = Population

    def setAreaOfCountry(self,country,Area):
        self._Area = Area
        for lists in self.countryCat:
            if lists[0] == country:
                lists[3] = Area

    def setContinentOfCountry(self,country,Continent):
        self._Continent = Continent
        for lists in self.countryCat:
            if lists[0] == country:
                lists[1] = Continent

    def findCountry(self,country):
        find = 0
        for lists in self.countryCat:
            print(lists)
            if lists[0] == country:
                print(lists)
                find = 1
            if find == 0:
                print("NONE")

    def addCountry(self,countryName,pop,area,cont):
        countryName = countryName
        for lists in self.countryCat:
            if lists[0] == countryName:
                print("False")
                return False
            else:
                self.countryCat.append([countryName,cont,pop,area])
                print("True")
                return True

    def printCountryCatalogue(country):
        super().__repr__()
        string = CountryCatalogue.countryCat
        print(string)
        return

    def saveCountryCatalogue(self,fname):
        file = open(fname,"w")
        try:
            count = 0
            for lists in self.countryCat:
                file.write(str(lists).replace("[","").replace("]","").replace("'","").replace(", ","|"))
                file.write("\n")
                count+=1
            return "The number added to the file is: ",count
        except Exception as error:
            return -1







