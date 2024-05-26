class Tool:

    def __init__(self,number,desc = "",location = ""):

        self._number = number
        self._desc = desc
        self._location = location

    def __repr__(self):

        return str(self._number)+" "+self._desc+" "+self._location

    def gettoolnumber(self):
        return self._number

    def getdesc(self):
        return self._desc

    def getlocation(self):
        return self._location

    def setloaction(self,location):
        #beacuse the location is a new variable, so there is self and  location in the bracket
        self._loaction = location



screwdriver = Tool(1,"screwdriver","basement")
wrench = Tool(2,"wrench","shop")
print(screwdriver)
print(wrench)

