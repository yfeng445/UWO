from Tools import Tool

class inventory:

    def __init__(self):

        self._inventory = {}

    def addTools(self,tool):

        self._inventory[tool.gettoolnumber()]
        #name of the dictionary

    def isTool(self,toolnumber):

        return toolnumber in self._inventory
        #index the inventory with tool number in line 9

tool1 = Tool(1,"hammer","shop")
tool2 = Tool(2,"Wrench","Basement")
tool3 = Tool(3,"Drill","Shed")

inv = inventory()

inv.addTools(tool1)
inv.addTools(tool2)
inv.addTools(tool3)

toto = inv.isTool(tool2.gettoolnumber())
if toto:
    print("We have a wrench")
