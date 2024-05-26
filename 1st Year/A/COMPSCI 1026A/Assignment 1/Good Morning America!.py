"""By Yulun Feng"""
"""This program calculate the price of the customers' order in the restaurant. It bascially based on a series of if and elif, accumulating the price and print it out"""



pegg = 0
pbacon = 0
psausage = 0
ptoast = 0
phash = 0
pcoffee = 0
ptea = 0
psmall = 0
pregular = 0
pbig = 0
price = 0
psegg = 0.99
psbacon = 0.49
pssausage = 1.49
pshash = 1.19
pstoast = 0.79
pscoffee = 1.09
pstea = 0.89


while(True):
    raw = input ("Enter item (q to terminate): small breakfast, regular breakfast, big breakfast, egg, bacon, sausage, hash brown, toast, coffee, tea:")    
    order = raw.replace(" ","").lower()


    if str(order) == ("egg"):
        negg = input("Enter quantity :")
        #negg = number of egg
        if negg.isnumeric():
            psegg = 0.99
            #psegg = price of a single egg
            pegg = int(negg)*psegg
            #pegg = price of the egg
    

    elif str(order) == ("bacon"):
        nbacon = input("Enter quantity :")
        #nbacon = number of bacon
        if nbacon.isnumeric():
            psbacon = 0.49
            #psbacon = price of a single strip of bacon
            pbacon = int(nbacon)*psbacon
            #pbacon = price of the bacon
        

    elif str(order) == ("sausage"):
        nsausage = input("Enter quantity :")
        #nsausage = number of sausage
        if nsausage.isnumeric():
            pssausage = 1.49
            #pssausage = price of a single sausage
            psausage = int(nsausage)*pssausage
            #psausage = price of sausage
        

    elif str(order) == ("hashbrown"):
        nhash = input("Enter quantity :")
        #nhash = number of hash brown
        if nhash.isnumeric():
            pshash = 1.19
            #pshash = price of a single hash brown
            phash = int(nhash)*pshash
            #phash = price of the hash brown
       

    elif str(order) == ("toast"):
        ntoast = input("Enter quantity :")
        #ntoast = number of toast
        if ntoast.isnumeric():
            pstoast = 0.79
            #pstoast = price of a single slice of toast
            ptoast = int(ntoast)*pstoast
            #ptoast = price of the toast
       
    elif str(order) == ("coffee"):
        ncoffee = input("Enter quantity :")
        #ncoffee = number of coffee
        if ncoffee.isnumeric():
            pscoffee = 1.09
            #pscoffee = price of a single cup of coffee
            pcoffee = int(ncoffee)*pscoffee
            #pcoffee = price of the coffee
      

    elif str(order) == ("tea"):
        ntea = input("Enter quantity :")
        #ntea = number of tea
        if ntea.isnumeric():
            pstea = 0.89
            #pstea = price of a single bag of tea
            ptea = int(ntea)*pstea
            #ptea = price of the tea
       
    elif str(order) == ("smallbreakfast"):
        nsmall = input("Enter quantity :")
        #nsmall = number of small
        if nsmall.isnumeric():
            pssmall = psegg*1 + pshash*1 + pstoast*2 + psbacon*2 + pssausage*1
            #pssmall should be 6.23
            psmall = int(nsmall)*pssmall
            #psmall = price of small
        

    elif str(order) == ("regularbreakfast"):
        nregular = input("Enter quantity :")
        #nregular = number of regular
        if nregular.isnumeric():
            psregular =  psegg*2 + pshash*1 + pstoast*2 + psbacon*4 + pssausage*2
            #psregular should be 9.69
            pregular = int(nregular)*psregular
            #pregular = price of regular
        
    elif str(order) == ("bigbreakfast"):
        nbig = input("Enter quantity :")
        #nbig = number of big
        if nbig.isnumeric():
            psbig = psegg*3 + pshash*2 + pstoast*4 + psbacon*6 + pssausage*3
            #ps big should be 15.92
            pbig = int(nbig)*psbig
            #pbig = price of big

    elif str(order) == "q":
        break

    else:
        print("error")
  
price = pegg + pbacon + psausage + phash + ptoast + pcoffee + ptea + psmall + pregular + pbig


print("Cost :" + str("%.2f" % price))

tax = price*0.13
print("Tax :" + str("%.2f" % tax))

total = price*1.13
print("Total :" + str("%.2f" % total))

