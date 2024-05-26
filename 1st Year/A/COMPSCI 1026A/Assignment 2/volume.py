def cube(a):
    vcube = a**3
    return vcube

def pyramid(b,h):
    vpyramid = (b**2)*h/3
    return vpyramid

def ellipsoid(r1,r2,r3):
    import math
    pi = math.pi
    vellipsoid = 4 * pi * r1 * r2 * r3
    return vellipsoid


