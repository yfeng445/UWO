def processUpdates(cntryFileName,updateFileName):
    from catalogue import CountryCatalogue
    from country import Country

    success = True

    while(True):
        try:
            open(cntryFileName,"r")
        except IOError:
            print("File does not exist.")
            option = input("Enter Y/N to determine quit or not: ")
            if option == "Y":
                output = open("output.txt","w")
                output.write("Update Unsuccessful\n")
            if option == "N":










"""        cntryFile = open(cntryFileName,"r")
        updateFile = open(updateFileName,"r")
        if IOError:
            while(True):
                option = input("File does not exist, do you want to quit or not? ")
                if option == "N":
                    while(True):
                        cntryFileName = input("Enter name of file with country data: ")
                        updateFileName = input("Enter name of file with country updates: ")
                        if IOError:
                            while(True):
                                option = input("File does not exist, do you want to quit or not? ")
                                if option == "Y":
                                    output = open("output.txt","w")
                                    output.write("Update Unsuccessful\n")
                                    success = False
                                    break
                                if option == "N":
                                    break
                if option == "Y":
                    output = open("output.txt","w")
                    output.write("Update Unsuccessful\n")
                    success = False
                    break"""








