total = 0
number = 0
list = []
while(number<10):
    value = input("Please enter a number: ")
    if not value in list:
        list.append(value)
        number = number+1
print(list)



