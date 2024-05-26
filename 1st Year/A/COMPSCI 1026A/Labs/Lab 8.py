a = True
while(a):
    try:
        file = input("Please enter the file name: ")
        i = open(file,"r")
        list = []
        for line in i:
            import re
            line = line.strip()
            wordlist = line.split()
            wordlist = re.findall(r'\w+',line)
            list.append(wordlist)
        value = 0
        for lst in list:
            value+=int(lst[-1])
        print("The total population is: ",value)
        break

    except IOError :
        print("Error: file not found.")

    except ValueError :
        print("Error: file contents invalid.")

    except RuntimeError as error :
        print("Error:", str(error))


