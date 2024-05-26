import math

class fraction:
    #if n is not given , it is set to 0. so does the d
    def __init__(self,n= 0,d= 1):

        if not inistance(n,int) or not isinstance(d,int):
            raise TypeError

        if d == 0:
            raise ZeroDivisionError("Denominator cannot be zero.")
        else:
            self._d = d
            self._n = n

        if (n<0 and d>=0 or n>=0 and d<0):
            sign = -1
        else:
            sign = 1

        self._n*=sign
        gcd = math.gcd(self._n,self._d)
        self._n //= gcd
        self._d //= gcd

    def __repr__(self):
        return str(self._n) + "/" + str(self._d)

    def __eq__(self,rhs):

        return self._n == rhs._n \
            and self._d == rhs._d

    def __mul__(self, rhs):
        return fraction(self._n*rhs,self._d*rhs)

    def __add__(self, rhs):
        numer = self._n*rhs + self._d*rhs
        denom = self._d*rhs
        return fraction(numer,denom)
b = fraction(1,4)

print(a)
print(b)

x = a*b

print(x)
