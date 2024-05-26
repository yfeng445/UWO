
class A:
    def __init__(self,xx,yy):
        self.x=xx
        self.y=yy

    def add(self):
        self._final = self.x*self.y

    def __repr__(self):
        print(self._final)
        return

x = A(2,3)
x.__repr__()
