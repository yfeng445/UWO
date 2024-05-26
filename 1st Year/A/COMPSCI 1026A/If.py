floor = input("Enter floor:")

if floor.isnumeric():
#This is to make sure the string will be a number other than a string
#Otherwise, it will crash
    floor = int(floor)
    #because the computer cannot compare a str and an int
    #It is very IMPORTANT!!!!!!!!!!!!

    actualfloor = floor

    if floor > 13:
        actualfloor = floor-1
    else:
        actualfloor = floor

    print("The actual floor will be",actualfloor)




    """floor >13:
    actualfloor = floor - 1
else:
    actualfloor = floor

print("The actual floor will be ",actualfloor)"""

"""A common error:
put print("The actual floor will be ", actualfloor)
because it will lead to a form error"""
