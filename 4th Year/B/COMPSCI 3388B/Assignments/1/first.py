from OpenGL.GL import *
import glfw

glfw.init()
window = glfw.create_window(1280, 1000, "Hello World", None, None)
glfw.make_context_current(window)

while not glfw.window_should_close(window):
    glfw.poll_events()
    glClear(GL_COLOR_BUFFER_BIT)
    glBegin(GL_TRIANGLES)
    glColor3ub(11,45,14)
    glVertex2f(0.,0.5)
    glVertex2f(0.5,-0.25)
    glVertex2f(-0.5,-0.25)
    glEnd()
    glfw.swap_buffers(window)
glfw.terminate()
