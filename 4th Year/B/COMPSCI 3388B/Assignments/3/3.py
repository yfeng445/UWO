import numpy
import math
import glfw
import sys
from OpenGL.GL import *

W = int(sys.argv[1])
H = int(sys.argv[2])
nodes = []
controlPoints = []

# setup
glfw.window_hint(glfw.CONTEXT_VERSION_MAJOR, 4)
glfw.init()
window = glfw.create_window(W, H, "ASMT3", None, None)
glfw.make_context_current(window)
glMatrixMode(GL_PROJECTION)
glLoadIdentity()
glOrtho(0, W, 0, H, -1, 1)
glViewport(0, 0, W, H)


# rendering
while not glfw.window_should_close(window):
    glfw.poll_events()
    glEnable(GL_LINE_SMOOTH)
    glEnable(GL_BLEND)
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
    glClearColor(1.0, 1.0, 1.0, 1.0)
    glClear(GL_COLOR_BUFFER_BIT)
    glBegin(GL_LINE_STRIP)




    glEnd()
    glfw.swap_buffers(window)
glfw.terminate()




