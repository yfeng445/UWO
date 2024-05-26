from tools import Tool

class Screwdriver(Tool):

    def __init__(self,number,desc = "",location = "",type = ""):
        super().__init__(number,desc,location)
        self._type =type
