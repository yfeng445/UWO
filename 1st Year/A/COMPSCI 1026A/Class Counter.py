class counter:

    _nclickers = 0
    #outside so that it wil not be initialized

    def __init__(self,n = 0):
        self._value = n
        counter._nclickers +=1
        #tell the python the position of the varible

    def click(self):
        self._value +=1

    def reset(self):
        self._value = 0

    def getvalue(self):
        return self._value

    def getnumber(self):
        return counter._nclickers

counter1 = counter()
#the first time the class was excuted
counter2 = counter()
counter3 = counter()


print(counter1.getvalue())
print(counter2.getvalue())
print(counter3.getvalue())
print(counter1.getnumber())


counter1 = None
counter2 = None
counter3 = None
#No assess to the
