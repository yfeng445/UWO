class Car:

    def __init__(self,efficiency):
        self._efficiency = efficiency
        self._gas = 0

    def addGas(self,amount):
        self._gas += amount

    def drive(self,miles):
        consumption = int(miles)/self._efficiency
        self._gas -= consumption

    def getGasLevel(self):
        return self._gas

myHybrid = Car(50)
myHybrid.addGas(20)
myHybrid.drive(100)
print(myHybrid.getGasLevel())
