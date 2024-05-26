import math

class Fraction:

    def __init__(self,numerator = 0,denominator=1):

        if not isinstance(numerator,int) or not isinstance(denominator,int):
            raise TypeError("Integer data type only")

        if denominator == 0:
            raise ZeroDivisionError("Denominator cannot be zero.")
        else:
            self._numerator = numerator
            self._denominator = denominator

        if (numerator < 0 and denominator >= 0 or numerator >= 0 and denominator < 0):
            sign = -1
        else:
            sign = 1

        self._numerator *= sign
        self._denominator = denominator

        gcd = math.gcd(self._numerator,self._denominator)
        self._numerator //= gcd
        self._denominator //= gcd

    def __repr__(self):
        return str(self._numerator) + "/" + str(self._denominator)

    def __eq__(self,rhsValue):

        return self._numerator == rhsValue._numerator \
               and  self._denominator == rhsValue._denominator

    def __mul__(self,rhsValue):
        return Fraction(self._numerator * rhsValue._numerator,self._denominator * rhsValue._denominator)

    def __truediv__(self,rhsValue):
        return Fraction(self._numerator * rhsValue._denominator,self._denominator * rhsValue._numerator)

    def __add__(self,rhsValue):
        denom = self._denominator * rhsValue._denominator
        numer = self._numerator * rhsValue._denominator + self._denominator * rhsValue._numerator
        return Fraction(numer,denom)

    def __sub__(self,rhsValue):
        denom = self._denominator * rhsValue._denominator
        numer = self._numerator * rhsValue._denominator - self._denominator * rhsValue._numerator
        return Fraction(numer, denom)

    def __float__(self):
        return self._numerator / self._denominator

    def __lt__(self,rhsValue):
        return self._numerator * rhsValue._denominator < self._denominator * rhsValue._numerator

    def __le__(self,rhsValue):
        return self._numerator * rhsValue._denominator <= self._denominator * rhsValue._numerator

    def __gt__(self,rhsValue):
        return self._numerator * rhsValue._denominator > self._denominator * rhsValue._numerator

    def __ge__(self,rhsValue):
        return self._numerator * rhsValue._denominator >= self._denominator * rhsValue._numerator

ITERATIONS = 100

sum = Fraction(0,1)
for i in range(0,ITERATIONS):
    sum += Fraction(1,2**i)

print(float(sum))
