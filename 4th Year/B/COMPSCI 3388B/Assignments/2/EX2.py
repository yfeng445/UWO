import sys
import math
import glfw
import numpy
from OpenGL.GL import *


def isDiagonal(corner, anotherCorner, width, length):
    if corner[0] ** 2 + corner[1] ** 2 + anotherCorner[0] ** 2 + anotherCorner[1] ** 2 == width ** 2 + length ** 2:
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
    cList = [c0, c1, c2, c3]
    p0 = [math.random(0, width), math.random(0, height)]
    pList = [p0]

    glfw.init()
    window = glfw.create_window(1000, 1000, "Exercise2", None, None)
    glfw.make_context_current(window)
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    glOrtho(-1.1, 1.1, -1.1, 1.1, -1, 1)
    glClearColor(1.0, 1.0, 1.0, 1.0)
    glClear(GL_COLOR_BUFFER_BIT)
    glBegin(GL_LINE_STRIP)
    glColor3f(.0, .0, .0)
    glPointSize(2.0)

    glfw.swap_buffers(window)

    for i in range(1, N):
        if not isDiagonal(cList[i], cList[i - 1], width, height):
            cTemp = cList[i]
            pTemp = [(cTemp[0]+pList[i-1][0])/2, (cTemp[1]+pList[i-1][1])/2]

            pList.append(pTemp)


main()
