class counter:

    def __init__(self,n = 0):
        self._value = n
        #initialization

    def click(self):
        self._value +=1

    def reset(self):
        self._value = 0

    def getvalue(self):
        return self._value

tally = counter()
print(tally.getvalue())
#according to line 4, the value should be 0
tally.click()
print(tally.getvalue())
#it should print 1
toto = counter(100)
print(toto.getvalue())
#100

