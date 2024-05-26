"""def countvowels(astring):

    counter = 0
    for letter in astring:
        if letter in "aeiou":
            counter = counter+1

    return counter

def main():

    inputstring = input("Enter test: ")
    nvowels = countvowels(inputstring)
    print("Number of vowels in text is:",nvowels)

main()"""
# if you don't add the main(), the python will not process the function which is not called.
# the main() is the call of the function

"""def cubevolume(sidelength):
    if (sidelength < 0):
        return 0
    return sidelength**3"""

"""def cubevolume(side):
    
    if( side > 0):
        return side**3

print(cubevolume(-10.0))

and it run out to be none"""


"""def increment(number):
    return number +1
mynumber = 10
print(increment(mynumber))
print(mynumber)"""
#the function will not affect the original number

def boxstring(contents):
    n = len(contents)
    print("-"*(n+2))
    print(":"+ contents +":")
    print("-"*(n+2))
    return""

a = input("enter something: ")

print(boxstring(a))

