class Calculator:
    #The first letter of the class namd should be capital
    name = "aabb"
    #the name is the attribute of the class, which is considered as a variable

    def add(self,x,y):
        return(x+y)
    #the def means the function of the class

    def minus(self,x,y):
        return(x-y)

    def name(self):
        return self.name
cal = Calculator()
print(cal.name)
#print the name of the calculator
