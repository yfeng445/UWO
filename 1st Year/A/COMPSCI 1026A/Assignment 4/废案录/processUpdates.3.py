from catalouge import *
from country import *
import os.path


def processUpdates(cntryFileName, updateFileName):
    while not os.path.isfile(cntryFileName):
        infile = input("The file " + cntryFileName + " does not exist. Would you like to quit?(Y/N): ")
        infile.upper()
        if infile == "Y":
            file = open("output.txt", "w")
            file.write("Update Unsuccessful\n")
            file.close()
            return False
        cntryFileName = input("What is the name of the country file: ")

    cntryCat = CountryCatalogue(cntryFileName)

    while not os.path.isfile(updateFileName):
        infile = input("The file " + updateFileName + " does not exist. Would you like to quit?(Y/N) ")
        infile.upper()
        if infile == "Y":
            file = open("output.txt", "w")
            file.write("Update Unsuccessful\n")
            file.close()
            return False
        updateFileName = input("What is the name of the update file: ")
    file = open(updateFileName, "r")
    file.readline()
    #open the file


    for line in file:
        category = line.split(";")
        country = category[0]
        for value in category[1:3]:
            value = value.strip("\n").split("=")
            letter = value[0]
            letter = letter.strip(" ")
            print(letter)
            number = value[1].strip(" ").replace(",", "")
            print(number)
    #process the file
            if letter == "A":
                area = int(number)
                cntryCat.setAreaOfCountry(country, area)
            if letter == "C":
                continent = str(number)
                cntryCat.setContinentOfCountry(country, continent)
            if letter == "P":
                population = int(number)
                cntryCat.setPopulationOfCountry(country, population)
        #process the updates

    return cntryCat.saveCountryCatalogue("output.txt")
