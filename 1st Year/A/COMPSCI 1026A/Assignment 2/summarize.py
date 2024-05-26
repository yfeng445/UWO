
#
# Assignment #2
# Output of list elements for testing program
#

# Function summarize simply outputs the lists for each shape with 2 decimal place accuracy
#

NEWLINE = "\n"
DASH = "-"
SPACE = " "

def summarize(clist,plist,elist,testNumber):
    fname = "Asgn2TestCase" + str(testNumber)
    print()
    print("*** Creating output for test "+ str(testNumber))
    print()
    outf = open(fname,"w")
    output_list(clist,outf)
    output_list(plist,outf)
    output_list(elist,outf)
    outf.close()

def output_list(lst,outfile):
    lngth = len(lst)
    if lngth > 0:
        for item in lst:
            str = "%6.2f" % item
            outfile.write(str+SPACE)
        outfile.write(NEWLINE)
    else:
        outfile.write(DASH+NEWLINE)
