from catalouge import *
from country import *
import os.path


def processUpdates(cntryFileName, updateFileName):
    while not os.path.isfile(cntryFileName):
        fileInput = input("The file " + cntryFileName + " does not exist. Would you like to quit?(Y/N): ")
        fileInput.upper()
        if fileInput == "Y":
            file = open("output.txt", "w")
            file.write("Update Unsuccessful\n")
            file.close()
            return False
        cntryFileName = input("What is the name of the country file: ")

    cntryCat = CountryCatalogue(cntryFileName)

    while not os.path.isfile(updateFileName):
        fileInput = input("The file " + updateFileName + " does not exist. Would you like to quit?(Y/N) ")
        fileInput.upper()
        if fileInput == "Y":
            file = open("output.txt", "w")
            file.write("Update Unsuccessful\n")
            file.close()
            return False
        updateFileName = input("What is the name of the update file: ")
    file = open(updateFileName, "r")
    file.readline()
    for line in file:
        category = line.split(";")
        #print(category)
        name = category[0]
        #print(name)
        for value in category[1:3]:
            value = value.strip("\n")
            value = value.split("=")
            letter = value[0]
            letter = letter.strip(" ")
            print(letter)
            number = value[1]
            number = number.strip(" ")
            number = number.replace(",", "")
            print(number)
            if letter == "A":
                area = int(number)
                cntryCat.setAreaOfCountry(name, area)
            if letter == "C":
                continent = str(number)
                cntryCat.setContinentOfCountry(name, continent)
            if letter == "P":
                population = int(number)
                cntryCat.setPopulationOfCountry(name, population)

    return cntryCat.saveCountryCatalogue("output.txt")
