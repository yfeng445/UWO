"""一猫三吃现场"""

#string = "My cat's name is titi."

"""for letter in string:
    print(letter)"""

"""for i in range(0,len(string)):
    print(string[i])"""

"""n = len(string)
for i in range(0,n):
    print(string[i])"""
#炸电脑现场
"""for i in range(0,1000000):
    for j in range(0,1000000):
        for k in range(0,1000000):
            for l in range(0,1000000):
             print(i,j,k,l)
"""


"""
counter = 1
while counter <=10:
    print(counter)
    counter = counter+1
exit(0)
"""

"""
sum = 0
max = 0

data = input("Enter a anumber: ")
max = int(data)

while data.isnumeric():
    data = input("Enter a number: ")
    if data.isnumeric():
      if int(data) > max:
         max = int(data)

print("Maximun value is: ",max)
"""

"""
 exit(0)
 .isnumeric()   does not work for negative interger
 so you need to refer to a python dictionary to see what function works in this situation
"""

"""
while not done:
    data = input("Enter a number:")
    if data.isnumeric():
        sum = sum + int(data)
        #it just the same as "sum += int(data)"
    else:
        done = True

print("Sum is: ", sum)
"""

