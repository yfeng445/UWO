import time

EST = 0
GMT = (EST+5)%24
CST = (EST+11)%24
while(EST<24):
    print("EST: ",EST,"GMT: ",GMT,"CST: ",CST)
    EST+=1
    GMT = (EST+5)%24
    CST = (EST+11)%24