list = []
indata = open("LinksHouse/Bottles.ply", "r")
line = indata.readline()
while line:
    print(line)
    line = line.split(" ")
    if(len(line)) == 8:
        for j in range(len(line)):
            line[j] = float(line[j])
        list.append(line)
    line = indata.readline()

print(list)
print(len(list))
