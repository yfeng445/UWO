class Country:
    def __init__(self, name, pop, area, continent):
        self.name = name
        self.pop = pop
        self.area = area
        self.continent = continent

    def getName(self):
        return self.name

    def getPopulation(self):
        return self.pop

    def getArea(self):
        return self.area

    def getContinent(self):
        return self.continent

    #getting methods

    def setPopulation(self, pop):
        self.pop = pop

    def setArea(self, area):
        self.area = area

    def setContinent(self, continent):
        self.continent = continent

    #setting methods

    def __repr__(self):
        result = 'Name:' + str(self.name) + " " + "(pop:" + str(self.pop) + ", size:" + " " + str(self.area) + ") in " + " " + str(self.continent) + "."''
        return result
