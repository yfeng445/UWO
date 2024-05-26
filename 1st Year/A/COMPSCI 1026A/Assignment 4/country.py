class Country:

    def __init__(self, name, pop, area, cont):
        self.name = name
        self.pop = pop
        self.area = area
        self.cont = cont
        #initilialization

    def getName(self):
        return self.name
    def getPopulation(self):
        return self.pop
    def getArea(self):
        return self.area
    def getContinent(self):
        return self.cont
    #getting methods

    def setPopulation(self, newPop):
        self.pop = newPop
    def setArea(self, newArea):
        self.area = newArea
    def setName(self, newName):
        self.name = newName
    def setContinent(self, newContinent):
        self.cont = newContinent
    #setting methods

    def __repr__(self):
        return "%s (pop: %s, size: %s) in %s" % (self.name, str(self.population).replace(",", ""), str(self.area).replace(",", ""), self.continent)
    #return country information in format
