def dayup(df):
    dayup = 1
    for i in range (365):
        if i % 5 in [4,0]:
            dayup = dayup*(1-0.01)
        else:
            dayup = dayup*(1+df)
    return dayup
dayfactor = 0.01
while dayup(dayfactor)<37.78:
    dayfactor +=0.001
print("三天打鱼两天晒网的努力参数是:{:.3f}".format(dayfactor))
