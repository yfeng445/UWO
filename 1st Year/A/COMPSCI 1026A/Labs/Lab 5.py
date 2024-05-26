def allthesame(x,y,z):
    if x == y == z:
        print("True")
    else:
        print("False")

def countvowels(astring):
    counter = 0
    for letter in astring:
        if letter in "aeiou":
            counter = counter+1

    print("The number of the vowel in the string is: ",counter)

