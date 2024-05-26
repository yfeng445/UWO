NEWLINE = "\n"
DASH = "-"
SPACE = " "

def output_list(lst, outfile):
    lngth = len(lst)
    if lngth > 0:
        for item in lst:
 #           item = float(item)
            str = "%6.2f" % item
            outfile.write(str + SPACE)
        outfile.write(NEWLINE)
    else:
        outfile.write(DASH + NEWLINE)

def summarize(listcube, listpyramid, listellipsoid, testNumber):
    fname = "Asgn2TestCase" + str(testNumber)
    print()
    print("*** Creating output for test " + str(testNumber))
    print()
    outf = open(fname, "w")
    output_list(listcube, outf)
    output_list(listpyramid, outf)
    output_list(listellipsoid, outf)
    outf.close()

"""def blank(listcube, listpyramid, listellipsoid, testNumber):
    fname = "Asgn2TestCase" + str(testNumber)
    print()
    print("*** Creating output for test " + str(testNumber))
    print()
    outf = open(fname, "w")
    output_list("You have reached the end of your session.")
    output_list("You did not perform and volume calculations")
    outf.close()
"""





"""心疼一下自己打的那么多东西。。。。
明明都快做完了的。。。。。
突然发现打白工了。。。。。"""

"""def summarize(c,p,e,testNumber):
    fname = "Asgn2TestCase" + str(testNumber)
    print()
    print("*** Creating output for test " + str(testNumber))
    print()
    f = open(fname,"w")
    f. write("You have reached the end of your session.")
    f.write('\n'"The volumes calculated for each shape are:   ")
    c = "".join(c)
    p = "".join(p)
    e = "".join(e)
    f.write('\n'"Cube: ")
    f.write(c)
    f.write('\n'"Pyramid: ")
    f.write(p)
    f.write('\n'"Ellipsoid: ")
    f.write(e)
    f.close()

def blank(c,p,e,testNumber):
    fname = "Asgn2TestCase" + str(testNumber)
    print()
    print("*** Creating output for test " + str(testNumber))
    print()
    f = open(fname, "w")
    f.write("You have reached the end of your session.")
    f.write('\n'"You did not perform any volume calculations")
    f.close()

"""
