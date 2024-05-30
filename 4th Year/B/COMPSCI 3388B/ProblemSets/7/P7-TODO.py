from OpenGL.GL import *
from OpenGL.arrays import vbo
import glfw
import glm

g_vert_buffer = \
    [-0.5, -0.5, 0.0,
	-0.5, 0.5, 0.0,
	0.5, 0.5, 0.0,
	0.5, -0.5, 0.0]

g_color_buffer = \
    [1.0, 0.0, 0.0,
    0.0, 1.0, 0.0,
    0.0, 0.0, 1.0,
    0.0, 0.5, 0.5]

indexArray = [0, 2, 1, 0, 3, 2]

screenW = 1400
screenH = 900

window = glfw.create_window(screenW, screenH, "Example")

glfw.make_context_current(window)

glewExperimental = True
VertexShaderID = gl.create_shader(GL_VERTEX_SHADER);
VERTEX_SHADER = shaders.compileShader("""#version 120
void main() {
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}""", GL_VERTEX_SHADER)
