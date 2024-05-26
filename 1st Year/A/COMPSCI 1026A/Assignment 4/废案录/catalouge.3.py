import country


def cSort(cntry):
    return cntry.getName()


class CountryCatalogue:
    def __init__(self, countryFile):
        self.countryFile = countryFile
        self.countryCat = []
        infile = open(countryFile, "r")
        infile.readline()
        for line in infile:
            category = line.strip("\n").split("|")
            if category[1] == "":
                category[1] = ""
            else:
                category[1] = category[1]

            try:
                category[2] = category[2].replace(",", "")
            except IndexError:
                category[2] = ""

            try:
                category[3] = category[3].replace(",", "")
            except IndexError:
                category[3] = ""

            finally:
                cntry = country.Country(category[0], category[2], category[3], category[1])
                self.countryCat.append(cntry)
        infile.close()
        #this function can process the word and create a country if the country does not exist

    def setPopulationOfCountry(self, countryName, pop):
        for cntry in self.countryCat:
            if countryName == cntry.getName():
                cntry.setPopulation(pop)

    def setAreaOfCountry(self, countryName, area):
        for cntry in self.countryCat:
            if countryName == cntry.getName():
                cntry.setArea(area)

    def setContinentOfCountry(self, countryName, cont):
        for cntry in self.countryCat:
            if countryName == cntry.getName():
                cntry.setContinent(cont)

        #setting methods

    def findCountry(self, countryName):
        for cntry in self.countryCat:
            if countryName == cntry.getName():
                return cntry
        return None

    def addCountry(self, countryName, pop, area, cont):
        cntry = country.Country(countryName, pop, area, cont)
        exist = False
        for cntry in self.countryCat:
            if countryName == cntry.getName():
                exist = True
        if not exist:
            self.countryCat.append(cntry)
            return True
        return False
        #add a country

    def printCountryCatalogue(self):
        print(self.countryCat)

    def saveCountryCatalogue(self, fname):
        self.countryCat = sorted(self.countryCat, key=cSort)
        count = 0
        try:
            outfile = open(fname, "w")
            outfile.write("Country|Continent|Population|Area\n")
            for cntry in self.countryCat:
                outfile.write(cntry.getName() + "|" + cntry.getContinent() + "|" + str(cntry.getPopulation()) + "|" + str(cntry.getArea()) + "\n")
                count += 1
            outfile.close()
            return count
        except IOError:
            return -1
