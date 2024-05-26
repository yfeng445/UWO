#Question: How many years does it take to double the balance?


INITIAL_BALANCE = 10000
RATE = 1.05
year = 0

balance = INITIAL_BALANCE

while (balance<2*INITIAL_BALANCE):
    balance = balance*RATE
    year = year+1
print(balance)
print(year)
