import volume
import summarize
listcube = []
listpyramid = []
listellipsoid = []


testNumber = input("Please enter the test number: ")

while(True):

    shape = input("Please enter the shape: ")
    shape = shape.replace(" ","").lower()

    if str(shape) == "cube" or str(shape) == "c" :
        a = int(input("Please input the length of a side: "))
        vcube = volume.cube(a)
        listcube.append(vcube)
        listcube.sort()
        c = listcube



    elif str(shape) == "pyramid" or str(shape) == "p":
        b = int(input("Please input the length of base: "))
        h = int(input("Please input the height: "))
        vpyramid = "%.2f" % volume.pyramid(b,h)
        listpyramid.append(vpyramid)
        listpyramid.sort()
        p = listpyramid


    elif str(shape) == "ellipsoid"or str(shape) =="e":
        r1 = int(input("Please input the firse radius: "))
        r2 = int(input("Please input the second radius: "))
        r3 = int(input("Please input the third radius: "))
        vellipsoid = "%.2f" % volume.ellipsoid(r1,r2,r3)
        listellipsoid.append(vellipsoid)
        listellipsoid.sort()
        e = listellipsoid


    elif str(shape) == "quit" or str(shape) == "q":
        import summarize
        if listcube == [] and listpyramid == [] and listellipsoid == []:
            print("You have reached the end of your session.")
            print("You did not perform and volume calculations")

        else:
            print("You have reached the end of your session.")
            print("The volumes calculated for each shape sre:")
            if listcube == []:
                listcube.append("No shape entered")
            if listpyramid == []:
                listpyramid.append("No shape entered")
            if listellipsoid == []:
                listellipsoid.append("No shape entered")
            print("Cube:",listcube)
            print("Pyramid:",listpyramid)
            print("Ellipsoid",listellipsoid)
        if listcube == ["No shape entered"]:
            listcube = []
        if listpyramid == ["No shape entered"]:
            listpyramid = []
        if listellipsoid == ["No shape entered"]:
            listellipsoid = []
        summarize.summarize(listcube,listpyramid,listellipsoid,testNumber)

        break

    else:
        print("error")
