import sys

import numpy
import math
import glfw
from OpenGL.GL import *

width = int(sys.argv[1])
height = int(sys.argv[2])




glfw.init()
window = glfw.create_window(width, height, "A3", None, None)
glfw.make_context_current(window)
glMatrixMode(GL_PROJECTION)
glLoadIdentity()

glOrtho(0, width, 0, height, -1, 1)
glViewport(0, 0, width, height)

while not glfw.window_should_close(window):
    glfw.poll_events()


    glEnd()
    glfw.swap_buffers(window)
glfw.terminate()
