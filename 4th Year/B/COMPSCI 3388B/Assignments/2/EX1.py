import numpy
import math
import glfw
from OpenGL.GL import *

# readfile and convert it into a list
with open("./dog.txt", "r") as f:
    data = f.readline()
    dogfile = data[:-1].split(' ')

glfw.init()
window = glfw.create_window(1000, 1000, "Exercise1", None, None)
glfw.make_context_current(window)
glMatrixMode(GL_PROJECTION)
glLoadIdentity()

glOrtho(0, 60, 0, 60, -1, 1)
glTranslate(30,30,1)


while not glfw.window_should_close(window):
    glfw.poll_events()

    # Render Steps
    glClearColor(1.0, 1.0, 1.0, 1.0)
    glClear(GL_COLOR_BUFFER_BIT)
    glBegin(GL_LINE_STRIP)
    glColor3f(.0, .0, .0)
    for j in range(0, 8):
        for i in range(0, len(dogfile), 2):
            glColor3f(.0, .0, .0)
            glVertex2f(float(dogfile[i])+25*math.sin(math.pi/4*j), float(dogfile[i+1])+25*math.cos(math.pi/4*j))
        #glColor3f(1.0, 1.0, 1.0)
        #glVertex2f(float(len(dogfile)-2) + 25 * math.sin(math.pi / 4 * j), float(len(dogfile)-1) + 25 * math.cos(math.pi / 4 * j))


    glEnd()
    glfw.swap_buffers(window)
glfw.terminate()


#def draw(self):


