from country import Country
class CountryCatalogue:
    def __init__(self,countryfile):
        self.CountryCat = {}
        self.cfile = countryfile
        self.cfile = open(self.cfile, 'r', encoding="utf‐8")
        for lines in self.cfile.readlines()[1:]:
            lines = lines.rstrip('\n')
            lines = lines.split('|')
            self.CountryCat[lines[0]] = [lines[1],lines[2],lines[3]]
            #open the country file and processed it
            #after processing, add the words into a dictionary


    def setPopulationOfCountry(self,population):
        self._populationOfCountry = population
    def setAreaOfCountry(self,area):
        self.areaOfCountry = area
    def setContinentOfCountry(self,continent):
        self.continentOfCountry = continent
        #setter methods


    def findCountry(self,country):
        self._nameOfcountry = country
        if str(country) in self.CountryCat:
            return country
        else:
            return None
        #find if the country is in the dictionary


    def addCountry(self,countryName,pop,area,cont):
        self.newname, self.newpop, self.newarea, self.newcont = countryName, pop, area, cont
        if CountryCatalogue.findCountry(self.newname) == None:
            self.CountryCat[self.newname] = [self.newcont,self.newpop,self.newarea]
            return True
        else:
            return False
        #add country to the dictionary, if the country does not exist, then one will be created

    def printCountryCatalogue(self):
        for i in self.CountryCat:
            print(Country(i,self.CountryCat[i][1],self.CountryCat[i][2],self.CountryCat[i][0]))
            #print the country information


    def saveCountryCatalogue(self,fname):
        try:
            self.writefile = open(fname, 'w', encoding= 'utf-8')
            sorted(self.CountryCat.keys())
            self.writefile.write('Country|Continent|Population|Area\n')
            for i in self.CountryCat:
                self.writefile.write('%s|%s|%s|%s\n' % (i,self.CountryCat[i][0],str(self.CountryCat[i][1]),str(self.CountryCat[i][2])))    #wirte the dictionary in special format in writefile
            return len(self.CountryCat)+1
        except:
            return -1
        #save the countries in an alphabetical order and save it

