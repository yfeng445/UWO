list = []
while(True):
    v = 0
    try:
        a = input("Please enter the file name: ")
        infile = open(a,"r")
        for lines in infile:
            a = lines
            list.append(a)
        print(list)
        #process the data
        infile.close()
        #if the open failed, it will right print sth
        #if the file exist, it will finish with exit code 0
        v = 1
    except Exception as error:
        #if the try comes out error, the program will prerform the following of the excepttion
        #exception is the output of the python when it is crashed, and it can be save as a string
        v = 0

    if v == 1:
        break
        print("out")
