import sys
import math
import glfw
import numpy
from OpenGL import *

def isDiagonal(corner, anotherCorner, width, length):
    if corner[0]**2+corner[1]**2+anotherCorner[0]**2+anotherCorner[1]**2 == width**2+length**2:
        return True
    else:
        return False

def main():
    N = int(sys.argv[1])
    width = int(sys.argv[2])
    height = int(sys.argv[2])
    c0 = [0, 0]
    c1 = [0, height]
    c2 = [width, height]
    c3 = [width, 0]
    cs = [c0, c1, c2, c3]
    dia_len_sq = width**2+height**2
    p0 = [math.random(0, width), math.random(0, height)]
    for i in range(1, N):
        if not isDiagonal(cs[i], cs[i-1], width, height):


















main()
