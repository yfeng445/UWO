choice = str(input("Do you want wine?(yes/no))"))
if choice == "no":
    print("ok, i will serve you")
else:
    age = input("how old are you?")
    age = int(age)
    if age>=19:
        print("ok, i will serve you")
    else:
        print("I'm gonna to read you the law!")
