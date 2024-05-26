class Vehicle:

    def __init__(self,plate_number = ""):

        self._plate_number = plate_number

    def getPlatenmuber(self):

        return self._plate_number

    def setPlateNumber(self,plate_number):
        self._plate_number = plate_number

    def display(self):
        print("Plate number:",self._plate_number," ")

class Car(Vehicle):

    def __init__(self,plate_number = "",number_passengers = 0):
        super().__init__(plate_number)
        self._number_passengers = number_passengers

    def display(self):

        super().display()
        print("Number of the passenger is:",self._number_passengers," ")

class SportsCar(Car):

    def __init__(self,plate_number,number_passengers,horsepower):
        super().__init__(plate_number,number_passengers)
        self.horsepower = horsepower

    def display(self):

        super().display()
        print("Horsepower of the car is:",self.horsepower," ")

def displayItem(item):
    item.display()
    print()


a = SportsCar("SSNI-618",5,600)
b = Car("SNS-48",2)
c = SportsCar("ABX 02",2,600)
displayItem(a)
displayItem(b)
displayItem(c)

