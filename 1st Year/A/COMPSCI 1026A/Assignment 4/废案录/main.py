
from processUpdates import *

def main():
    # Main simply makes use of the function processUpdates to process the files.
    # ... main provides two files: a "data file" with the geographical information and an "update file" with
    #      the list of updates
    # ... the function processUpdates ensures that each exists and IF NOT, it will prompt for new file names or
    #      give the user the option to quit.  If they do exist, then it tries to process them.  It should also
    #      skip lines if there is a problem processing the line, e.g. bad values, incorrect format, etc.
    #
    # Main prints an initial message, prompts for the two file names and then invokes the function processUpdates

    print()
    print(40*"*")
    print("*** Updating country files")
    print()
    cntryFileName = input("Enter name of file with country data: ")
    updateFileName = input("Enter name of file with country updates: ")
    result = processUpdates(cntryFileName,updateFileName)
    print()
    print(40*"*")
    if result:
        print("*** Updating successfully completed")
    else:
        print("*** Updating NOT successfully completed")

main()
