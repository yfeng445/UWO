"""infile = open("input.txt","r")

line = infile.readline()
while line !="":
    print(line,end ="")
    #if the line is print(line), there will be an empty line between those sentences
    line = infile.readline()

infile.close()"""

infile = open("text3.txt","r")
outfile = open("output.txt","w")
total = 0
i = 0
for line in infile:
    # it will scan all these lines
    total = total + int(line)
    i = i+1
average = '%.2f'%(total/i)

print(total)
print(i)
print(average)
outfile.write("Average is: ")
outfile.write(str(average))
outfile.write(str("Number of the number is: "))
outfile.write(str(i))


infile.close()
outfile.close()
