import csv

my_dict = {}

with open('test.txt', 'r') as csvfile:
    A = csv.reader(csvfile)
    for row in A:
        print(row)

print(my_dict)






