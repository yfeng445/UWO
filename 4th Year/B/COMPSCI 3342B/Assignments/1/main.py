"""
@ Author: Yulun Feng
@ ID:251113989
@ Date: Feb 6, 2023
"""



def main():
    contents = []
    output = open("inputC_rm.cpp", "w")

    # read file
    with open("inputC.cpp", "r") as input:
        contentsIn = input.read()
        for lines in contentsIn:
            contents.append(lines)

    inSingleline = False
    inMultiLine = False

    for i in range(0, len(contents)-1):

        # //
        if contents[i] == contents[i+1] == "/":
            inSingleline = True
        if inSingleline:
            if contents[i-1] == "\n":
                if not contents[i] == contents[i+1] == "/":
                    output.write("\n")
                    inSingleline = False

        if not inSingleline:
            # /* ... */
            if contents[i] == "/" and contents[i+1] == "*":
                inMultiLine = True
            if contents[i-2] == "*" and contents[i-1] == "/":
                inMultiLine = False


        if not inSingleline and not inMultiLine:
            output.write(contents[i])

main()
