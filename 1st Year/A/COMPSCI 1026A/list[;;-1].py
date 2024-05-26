import random
ls = []
list = []
for i in range(20):
    ls.append(random.randint(50,99))
ls.sort()
list = ls[::-1]
print(list)
print(max(ls))
print(min(ls))
