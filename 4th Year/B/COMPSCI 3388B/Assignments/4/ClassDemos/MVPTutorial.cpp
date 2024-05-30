// Include standard headers
#include <stdio.h>
#include <stdlib.h>

#include <GL/glew.h>

// Include GLFW
#include <GLFW/glfw3.h>
GLFWwindow* window;


// Include GLM
#include <glm/glm.hpp>
#include <glm/gtc/type_ptr.hpp>
#include <glm/gtx/string_cast.hpp>
#include <glm/gtc/matrix_transform.hpp>
using namespace glm;

#include "shader.hpp"
#include <iostream>

//Nope. You'll have to do this yourself for assignment 5
//#include "CamControls.hpp"
void cameraControlsGlobe(glm::mat4& V) {

}

//////////////////////////////////////////////////////////////////////////////
// Vertex Data
//////////////////////////////////////////////////////////////////////////////

static const GLfloat g_vertex_buffer_data[] = { 
	
	//bottom face first tri
	0.0f, 0.0f, 0.0f, 
	1.0f, 0.0f, -1.0f,
	1.0f, 0.0f, 0.0f, 
	//bottom face second tri
	0.0f, 0.0f, 0.0f, 
	0.0f, 0.0f, -1.0f, 
	1.0f, 0.0f, -1.0f, 

	//back face first tri
	0.0f, 0.0f, -1.0f,
	1.0f, 1.0f, -1.0f, 
	1.0f, 0.0f, -1.0f,

	//back fact second tri
	0.0f, 0.0f, -1.0f,
	0.0f, 1.0f, -1.0f,
	1.0f, 1.0f, -1.0f,

	//right face first
	1.0f, 0.0f, 0.0f, 
	1.0f, 0.0f, -1.0f, 
	1.0f, 1.0f, 0.0f,

	//right face second tri
	1.0f, 0.0f, -1.0f, 
	1.0f, 1.0f, -1.0f, 
	1.0f, 1.0f, 0.0f,

	//left fact first
	0.0f, 0.0f, 0.0f, 
	0.0f, 1.0f, 0.0f, 
	0.0f, 0.0f, -1.0f, 

	//left fact second
	0.0f, 1.0f, 0.0f,
	0.0f, 1.0f, -1.0f,
	0.0f, 0.0f, -1.0f,

	//top face first
	0.0f, 1.0f, 0.0f, 
	1.0f, 1.0f, 0.0f, 
	1.0f, 1.0f, -1.0f,

	//top face second
	0.0f, 1.0f, 0.0f,
	1.0f, 1.0f, -1.0f, 
	0.0f, 1.0f, -1.0f,

	//front face first
	0.0f, 0.0f, 0.0f, 
	1.0f, 0.0f, 0.0f, 
	1.0f, 1.0f, 0.0f, 

	//front face second
	0.0f, 0.0f, 0.0f, 
	1.0f, 1.0f, 0.0f, 
	0.0f, 1.0f, 0.0f
};

static const GLfloat g_color_buffer_data[] = { 
	
	//bottom face first tri
	0.5f, 0.5, 0.0f,
	0.5f, 0.5, 0.0f,
	0.5f, 0.5, 0.0f,

	//bottom face second tri
	0.5f, 0.5, 0.0f,
	0.5f, 0.5, 0.0f,
	0.5f, 0.5, 0.0f,

	//back face first tri
	1.0f, 0.5, 0.0f,
	1.0f, 0.5, 0.0f,
	1.0f, 0.5, 0.0f,

	//back face second tri
	1.0f, 0.5, 0.0f,
	1.0f, 0.5, 0.0f,
	1.0f, 0.5, 0.0f,

	//right face first
	0.5f, 0.0f, 0.5f,
	0.5f, 0.0f, 0.5f,
	0.5f, 0.0f, 0.5f,

	//right face second tri
	0.5f, 0.0f, 0.5f,
	0.5f, 0.0f, 0.5f,
	0.5f, 0.0f, 0.5f,

	//left fact first
	0.0f, 0.5f, 0.5f,
	0.0f, 0.5f, 0.5f,
	0.0f, 0.5f, 0.5f,

	//left fact second
	0.0f, 0.5f, 0.5f,
	0.0f, 0.5f, 0.5f,
	0.0f, 0.5f, 0.5f,

	//top face first
	1.0f, 0.5f, 1.0f,
	1.0f, 0.5f, 1.0f,
	1.0f, 0.5f, 1.0f,

	//top face second
	1.0f, 0.5f, 1.0f,
	1.0f, 0.5f, 1.0f,
	1.0f, 0.5f, 1.0f,

	//front face first
	0.25f, 0.5f, 0.25f, 
	0.25f, 0.5f, 0.25f, 
	0.25f, 0.5f, 0.25f, 

	//front face second
	0.25f, 0.5f, 0.25f, 
	0.25f, 0.5f, 0.25f, 
	0.25f, 0.5f, 0.25f
};


//////////////////////////////////////////////////////////////////////////////
// Helper Objects
//////////////////////////////////////////////////////////////////////////////


class Plane {

public:
	enum PLANE_WHICH {
		x,
		y,
		z
	};

private:
	PLANE_WHICH plane = PLANE_WHICH::x;

	glm::vec4 color = glm::vec4(0.9f, 0.9f, 0.9f, 0.1f);

	GLfloat size;

public:



	Plane(GLfloat sz) : size(sz) {} 

	void draw() {

		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		if (plane == PLANE_WHICH::x) {
		glBegin(GL_QUADS);

		glColor4f(color.x, color.y, color.z, color.w);
			glVertex3f(-size, 0.0f, -size);
			glVertex3f(size, 0.0f, -size);
			glVertex3f(size, 0.0f, size);
			glVertex3f(-size, 0.0f, size);
		
		glEnd();

		glBegin(GL_LINES);
		glColor4f(color.x, color.y, color.z, color.w+0.2f);

			for (int i = -size; i < size; ++i) {
				glVertex3f(1.0f*i, 0.0f, -size);				
				glVertex3f(1.0f*i, 0.0f, size);	
				glVertex3f(size, 0.0f, 1.0f*i);				
				glVertex3f(-size, 0.0f, 1.0f*i);				
			}
		glEnd();

		}


		glPopMatrix();
		glDisable(GL_BLEND);
	}

};

class Axes {

	glm::vec3 origin;
	glm::vec3 extents;

	glm::vec3 xcol = glm::vec3(1.0f, 0.0f, 0.0f);
	glm::vec3 ycol = glm::vec3(0.0f, 1.0f, 0.0f);
	glm::vec3 zcol = glm::vec3(0.0f, 0.0f, 1.0f);

public:

	Axes(glm::vec3 orig, glm::vec3 ex) : origin(orig), extents(ex) {}

	void draw() {

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();


		glLineWidth(2.0f);
		glBegin(GL_LINES);
		glColor3f(xcol.x, xcol.y, xcol.z);
		glVertex3f(origin.x, origin.y, origin.z);
		glVertex3f(origin.x + extents.x, origin.y, origin.z);

		glVertex3f(origin.x + extents.x, origin.y, origin.z);
		glVertex3f(origin.x + extents.x, origin.y, origin.z+0.1);
		glVertex3f(origin.x + extents.x, origin.y, origin.z);
		glVertex3f(origin.x + extents.x, origin.y, origin.z-0.1);


		// glVertex3f(origin.x, origin.y, origin.z);
		// glVertex3f(origin.x - extents.x, origin.y, origin.z);


		glColor3f(ycol.x, ycol.y, ycol.z);
		glVertex3f(origin.x, origin.y, origin.z);
		glVertex3f(origin.x, origin.y + extents.y, origin.z);

		glVertex3f(origin.x, origin.y + extents.y, origin.z);
		glVertex3f(origin.x, origin.y + extents.y, origin.z+0.1);
		glVertex3f(origin.x, origin.y + extents.y, origin.z);
		glVertex3f(origin.x, origin.y + extents.y, origin.z-0.1);
		
		// glVertex3f(origin.x, origin.y, origin.z);
		// glVertex3f(origin.x, origin.y - extents.y, origin.z);



		glColor3f(zcol.x, zcol.y, zcol.z);
		glVertex3f(origin.x, origin.y, origin.z);
		glVertex3f(origin.x, origin.y, origin.z + extents.z);
		
		glVertex3f(origin.x, origin.y, origin.z + extents.z);
		glVertex3f(origin.x+0.1, origin.y, origin.z + extents.z);

		glVertex3f(origin.x, origin.y, origin.z + extents.z);
		glVertex3f(origin.x-0.1, origin.y, origin.z + extents.z);

		// glVertex3f(origin.x, origin.y, origin.z);
		// glVertex3f(origin.x, origin.y, origin.z - extents.z);

		glEnd();


		glPopMatrix();
	}

};






//////////////////////////////////////////////////////////////////////////////
// Main
//////////////////////////////////////////////////////////////////////////////

int main( int argc, char* argv[])
{

	///////////////////////////////////////////////////////
	int currentStep = 1;
	int substep = 1;
	if (argc > 1 ) {
		currentStep = atoi(argv[1]);
	}
	if (argc > 2) {
		substep = atoi(argv[2]);
	}
	///////////////////////////////////////////////////////

	// Initialise GLFW
	if( !glfwInit() )
	{
		fprintf( stderr, "Failed to initialize GLFW\n" );
		getchar();
		return -1;
	}

	glfwWindowHint(GLFW_SAMPLES, 4);
	// glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	// glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	// glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // To make MacOS happy; should not be needed
	// glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

	// Open a window and create its OpenGL context
	float screenW = 1400;
	float screenH = 900;
	window = glfwCreateWindow( screenW, screenH, "An Example", NULL, NULL);
	if( window == NULL ){
		fprintf( stderr, "Failed to open GLFW window. If you have an Intel GPU, they are not 3.3 compatible. Try the 2.1 version of the tutorials.\n" );
		getchar();
		glfwTerminate();
		return -1;
	}
	glfwMakeContextCurrent(window);

	// Initialize GLEW
	glewExperimental = true; // Needed for core profile
	if (glewInit() != GLEW_OK) {
		fprintf(stderr, "Failed to initialize GLEW\n");
		getchar();
		glfwTerminate();
		return -1;
	}



	// Ensure we can capture the escape key being pressed below
	glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);

	// Dark blue background
	glClearColor(0.2f, 0.2f, 0.3f, 0.0f);

	///////////////////////////////////////
	// if (currentStep < 3 || currentStep > 6) {
		// Enable depth test
		glEnable(GL_DEPTH_TEST);
		// Accept fragment if it closer to the camera than the former one
		 glDepthFunc(GL_LESS);
	// }
	///////////////////////////////////////

	GLuint VertexArrayID;
	glGenVertexArrays(1, &VertexArrayID);
	glBindVertexArray(VertexArrayID);

	// Create and compile our GLSL program from the shaders
	GLuint programID = LoadShaders( "TransformVertexShader.vertexshader", "ColorFragmentShader.fragmentshader" );

	// Get a handle for our "MVP" uniform
	GLuint MatrixID = glGetUniformLocation(programID, "MVP");
	glm::mat4 MVP;

	GLuint vertexbuffer;
	glGenBuffers(1, &vertexbuffer);
	glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
	glBufferData(GL_ARRAY_BUFFER, sizeof(g_vertex_buffer_data), g_vertex_buffer_data, GL_STATIC_DRAW);

	GLuint colorbuffer;
	glGenBuffers(1, &colorbuffer);
	glBindBuffer(GL_ARRAY_BUFFER, colorbuffer);
	glBufferData(GL_ARRAY_BUFFER, sizeof(g_color_buffer_data), g_color_buffer_data, GL_STATIC_DRAW);

	// 1rst attribute buffer : vertices
	glEnableVertexAttribArray(0);
	glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
	glVertexAttribPointer(
		0,                  // attribute. No particular reason for 0, but must match the layout in the shader.
		3,                  // size
		GL_FLOAT,           // type
		GL_FALSE,           // normalized?
		0,                  // stride
		(void*)0            // array buffer offset
	);

	// 2nd attribute buffer : colors
	glEnableVertexAttribArray(1);
	glBindBuffer(GL_ARRAY_BUFFER, colorbuffer);
	glVertexAttribPointer(
		1,                                // attribute. No particular reason for 1, but must match the layout in the shader.
		3,                                // size
		GL_FLOAT,                         // type
		GL_FALSE,                         // normalized?
		0,                                // stride
		(void*)0                          // array buffer offset
	);
	glBindBuffer(GL_ARRAY_BUFFER, 0);
	glBindVertexArray(0);

	Axes ax(glm::vec3(0.0f, 0.0f, 0.0f), glm::vec3(4.0f, 4.0f, 4.0f));
	Axes localAX(glm::vec3(0.0f, 0.0f, 0.0f), glm::vec3(2.0f, 2.0f, 2.0f));

	Plane plane(5.0f);

	int i = 0;
	int j = 0;


	GLfloat theta = 3.14159f;
	GLfloat phi = 3.14159f * 0.5f;
	glm::vec3 position;
	if (currentStep >= 15) {
		glfwSetCursorPos(window, screenW/2, screenH/2);
	}


glm::vec3 eye = {5.0f, 2.0f, 5.0f};
// glm::vec3 eye = {-5.0f, 2.0f, -5.0f};
glm::vec3 up = {0.0f, 1.0f, 0.0f};
glm::vec3 center = {0.0f, 0.0f, 0.0f};
glm::mat4 V;


	do{
		// Clear the screen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


//////////////////////////////////////////////////////////////////////////////
if (currentStep == 0) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		// Projection = glm::mat4(1.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glm::mat4 V = glm::lookAt(eye, center, up); 
		cameraControlsGlobe(V);

		glm::mat4 M = glm::mat4(1.0f);
		M[3][0] = -0.5;
		M[3][1] = -0.5;
		M[3][2] = 0.5;

		glm::mat4 R = glm::mat4(1.0f);
		float rotX, rotY, rotZ;


		rotY = glm::radians(60.0f);
		rotZ = glm::radians(30.0f);

		float c, s;
		if (substep == 1) {
			rotZ = glm::radians( (float) (i % 360));
			s = sin(rotZ);
			c = cos(rotZ);

			R[0].x = c;
			R[1].x = -s;
			R[0].y = s;
			R[1].y = c;
		}
		if (substep == 2) {
			rotY = glm::radians( (float) (i % 360));
			c = cos(rotY);
			s = sin(rotY);
			R[0].x = c;
			R[2].x = s;
			R[0].z = -s;
			R[2].z = c;
			// R2[0].x = c;
			// R2[2].x = s;
			// R2[0].z = -s;
			// R2[2].z = c;
		}
		if (substep == 3) {
			rotX = glm::radians( (float) (i % 360));
			c = cos(rotX);
			s = sin(rotX);
			R[1].y = c;
			R[2].y = -s;
			R[1].z = s;
			R[2].z = c;
			// R2[0].x = c;
			// R2[2].x = s;
			// R2[0].z = -s;
			// R2[2].z = c;
		}
		if (substep == 4) {
			if (i < 90) {
				rotZ = glm::radians( (float) (i));
			} else {
				rotZ = glm::radians( 90.0f);
			}
			s = sin(rotZ);
			c = cos(rotZ);
			R[0].x = c;
			R[1].x = -s;
			R[0].y = s;
			R[1].y = c;

			rotY = glm::radians(0.0f);
			if (i > 90 && i < 180) {
				rotY = glm::radians( (float) (i - 90));
			} else if (i >= 180) {
				rotY = glm::radians(90.0f);
				i = 0;
			}
			glm::mat4 R2 = glm::mat4(1.0f);
			s = sin(rotY);
			c = cos(rotY);
			R2[0].x = c;
			R2[2].x = s;
			R2[0].z = -s;
			R2[2].z = c;
			R = R2 * R;
		}
		if (substep == 5) {
			if (i < 90) {
				rotZ = glm::radians( (float) (i/2));
			} else {
				rotZ = glm::radians( 90.0f/ 2);
			}
			s = sin(rotZ);
			c = cos(rotZ);
			R[0].x = c;
			R[1].x = -s;
			R[0].y = s;
			R[1].y = c;

			rotY = glm::radians(0.0f);
			if (i > 90 && i < 180) {
				rotY = glm::radians( (float) ((i - 90)));
			} else if (i >= 180) {
				rotY = glm::radians(90.0f);
				i = 0;
			}
			glm::mat4 R2 = glm::mat4(1.0f);
			s = sin(rotY);
			c = cos(rotY);
			R2[0].x = c;
			R2[2].x = s;
			R2[0].z = -s;
			R2[2].z = c;
			R = R2 * R;
		}
		if (substep == 6) {
			if (i < 90) {
				rotZ = glm::radians( (float) (i));
			} else {
				rotZ = glm::radians( 90.0f);
			}
			s = sin(rotZ);
			c = cos(rotZ);
			R[0].x = c;
			R[1].x = -s;
			R[0].y = s;
			R[1].y = c;

			rotY = glm::radians(0.0f);
			if (i > 90 && i < 180) {
				rotY = glm::radians( (float) ((i - 90)/2));
			} else if (i >= 180) {
				rotY = glm::radians(90.0f/2);
			}
			glm::mat4 R2 = glm::mat4(1.0f);
			s = sin(rotY);
			c = cos(rotY);
			R2[0].x = c;
			R2[2].x = s;
			R2[0].z = -s;
			R2[2].z = c;
			R = R2 * R;

			rotX = glm::radians(0.0f);
			if (i > 180 && i < 540) {
				rotX = glm::radians( (float) ((i - 180)/2));
			} else if (i >= 540) {
				rotX = glm::radians(180.0f);
			}
			if (i >= 880) {
				i = 0;
			}
			R2 = glm::mat4(1.0f);
			s = sin(rotX);
			c = cos(rotX);
			R2[1].y = c;
			R2[2].y = -s;
			R2[1].z = s;
			R2[2].z = c;
			R = R2 * R;
		}
		if (substep == 7) {
			if (i < 90) {
				rotZ = glm::radians( (float) (i)/2);
			} else {
				rotZ = glm::radians( 90.0f/2);
			}
			s = sin(rotZ);
			c = cos(rotZ);
			R[0].x = c;
			R[1].x = -s;
			R[0].y = s;
			R[1].y = c;

			rotY = glm::radians(0.0f);
			if (i > 90 && i < 180) {
				rotY = glm::radians( (float) ((i - 90)/2));
			} else if (i >= 180) {
				rotY = glm::radians(90.0f/2);
			}
			glm::mat4 R2 = glm::mat4(1.0f);
			s = sin(rotY);
			c = cos(rotY);
			R2[0].x = c;
			R2[2].x = s;
			R2[0].z = -s;
			R2[2].z = c;
			R = R2 * R;

			rotX = glm::radians(0.0f);
			if (i > 180 && i < 540) {
				rotX = glm::radians( (float) ((i - 180)/2));
			} else if (i >= 540) {
				rotX = glm::radians(180.0f);
			}
			if (i >= 880) {
				i = 0;
			}
			R2 = glm::mat4(1.0f);
			s = sin(rotX);
			c = cos(rotX);
			R2[1].y = c;
			R2[2].y = -s;
			R2[1].z = s;
			R2[2].z = c;
			R = R2 * R;
		}
		if (substep == 8) {
			if (i < 90) {
				rotZ = glm::radians( (float) (i)/2);
			} else {
				rotZ = glm::radians( 90.0f/2);
			}
			s = sin(rotZ);
			c = cos(rotZ);
			R[0].x = c;
			R[1].x = -s;
			R[0].y = s;
			R[1].y = c;

			rotX = glm::radians(0.0f);
			if (i > 180 && i < 540) {
				rotX = glm::radians( (float) ((i - 180)/2));
			} else if (i >= 540) {
				rotX = glm::radians(180.0f);
			}
			if (i >= 880) {
				i = 0;
			}
			glm::mat4 R2 = glm::mat4(1.0f);
			s = sin(rotX);
			c = cos(rotX);
			R2[1].y = c;
			R2[2].y = -s;
			R2[1].z = s;
			R2[2].z = c;
			R = R2 * R;
			rotY = glm::radians(0.0f);
			if (i > 90 && i < 180) {
				rotY = glm::radians( (float) ((i - 90)/2));
			} else if (i >= 180) {
				rotY = glm::radians(90.0f/2);
			}
			R2 = glm::mat4(1.0f);
			s = sin(rotY);
			c = cos(rotY);
			R2[0].x = c;
			R2[2].x = s;
			R2[0].z = -s;
			R2[2].z = c;
			R = R2 * R;

		}



		M = R*M;


		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		M = V*R;
		glLoadMatrixf(glm::value_ptr(M));
		localAX.draw();
		glMatrixMode(GL_MODELVIEW);
		glPopMatrix();



}



//////////////////////////////////////////////////////////////////////////////
if (currentStep == 1) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		// Projection = glm::mat4(1.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();
		glm::vec3 eye = {5.0f, 2.0f, 5.0f};
		// glm::vec3 eye = {-5.0f, 2.0f, -5.0f};
		glm::vec3 up = {0.0f, 1.0f, 0.0f};
		glm::vec3 center = {0.0f, 0.0f, 0.0f};
		//glm::mat4 V;

		glm::mat4 V = glm::lookAt(eye, center, up); 

		cameraControlsGlobe(V);


		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);
}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 2) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		// Projection = glm::mat4(1.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::vec3 eye = {5.0f, 2.0f, 5.0f};
		glm::vec3 up = {0.0f, 1.0f, 0.0f};
		glm::vec3 center = {0.0f, 0.0f, 0.0f};
		glm::mat4 V = glm::lookAt(eye, center, up);

		glm::mat4 M = glm::mat4(1.0f);
		M[3][0] = fmod(i * 0.005f, 2.0f);

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 3) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::mat4(1.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::mat4 V = glm::mat4(1.0f);
		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 4) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::mat4(1.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::mat4 V = glm::mat4(1.0f);
		V[3][0] = 0.5;
		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 5) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::mat4(1.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::mat4 V = glm::mat4(1.0f);
		V[3][2] = -0.5f;
		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 6) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::mat4(1.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::mat4 V = glm::mat4(1.0f);
		V[3][2] = 1.f;
		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);
}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 7) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::mat4(1.0f);//glm::perspective(glm::radians(45.0f), screenW / screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::mat4 V = glm::mat4(1.0f);
		V[3][2] = -1.f;
		glm::mat4 M = glm::mat4(1.0f);


		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 10*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 8) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::mat4 V = glm::mat4(1.0f);
		// V[3][0] = 0.5;
		// V[3][2] = -1.0f;
		glm::mat4 M = glm::mat4(1.0f);


		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 9) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::mat4 V = glm::mat4(1.0f);
		V[3][0] = 0.5;
		V[3][2] = -1.0f;
		glm::mat4 M = glm::mat4(1.0f);
		M[3][2] = -1.0*i*0.005f;

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 10) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();


		glm::vec3 eye = {5.0f, 2.0f, 5.0f};
		glm::vec3 up = {0.0f, 1.0f, 0.0f};
		glm::vec3 center = {0.0f, 0.0f, 0.0f};
		glm::mat4 V = glm::lookAt(eye, center, up);

		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 11) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();


		glm::vec3 eye = {5.0f, 2.0f, 5.0f};
		glm::vec3 up = {0.5f, 1.0f, -0.5f};
		glm::vec3 center = {0.0f, 0.0f, 0.0f};
		glm::mat4 V = glm::lookAt(eye, center, up);


		glm::mat4 M = glm::mat4(1.0f);
	//	M[3][2] = -1.0 * i * 0.005f;


		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 12) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::mat4 V = glm::mat4(1.0f);
		V[3][0] = -0.5f;
		V[3][1] = 0.25f;
		V[3][2] = -1.0f;


		glm::mat4 M = glm::mat4(1.0f);
	//	M[3][2] = -1.0 * i * 0.005f;


		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 13) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::vec3 eye = {0.5f, -0.25f, 1.0f};
		glm::vec3 targ = {0.0f, 0.0f, 0.0f};
		glm::vec3 up = {0.0f, 1.0f, 0.0f};
		glm::mat4 Vtrans = glm::mat4(1.0f);
		Vtrans[3][0] = -eye.x;
		Vtrans[3][1] = -eye.y;
		Vtrans[3][2] = -eye.z;

		glm::mat4 V = glm::mat4(1.0f);
		glm::vec3 D = glm::normalize(eye - targ);
		glm::vec3 R = glm::cross(up, D);

		V[0][0] = R.x;
		V[1][0] = R.y;
		V[2][0] = R.z;

		V[0][2] = D.x;
		V[1][2] = D.y;
		V[2][2] = D.z;

		V = V * Vtrans;

		glm::mat4 M = glm::mat4(1.0f);
	//	M[3][2] = -1.0 * i * 0.005f;

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 14) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::vec3 eye = {0.5f, -0.25f, 1.0f};
		glm::vec3 targ = {0.0f, 0.0f, 0.0f};
		glm::vec3 up = {0.0f, 1.0f, 0.0f};
		glm::mat4 Vtrans = glm::mat4(1.0f);
		Vtrans[3][0] = -eye.x;
		Vtrans[3][1] = -eye.y;
		Vtrans[3][2] = -eye.z;

		glm::mat4 V = glm::mat4(1.0f);
		glm::vec3 D = glm::normalize(eye - targ);
		glm::vec3 R = glm::cross(up, D);
		up = glm::cross(D, R);

		V[0][0] = R.x;
		V[1][0] = R.y;
		V[2][0] = R.z;

		V[0][1] = up.x;
		V[1][1] = up.y;
		V[2][1] = up.z;

		V[0][2] = D.x;
		V[1][2] = D.y;
		V[2][2] = D.z;

		V = V * Vtrans;

		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 15) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();

		glm::vec3 eye = {0.5f, -0.25f, 1.0f};
		glm::vec3 targ = {0.0f, 0.0f, 0.0f};
		glm::vec3 up = {0.0f, 1.0f, 0.0f};
		glm::mat4 V = glm::lookAt(eye, targ, up);
		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		// std::cerr << to_string(MV) << std::endl;
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
if (currentStep == 16) {
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glm::mat4 Projection = glm::perspective(glm::radians(45.0f), ((float) screenW)/screenH, 0.001f, 1000.0f);
		glLoadMatrixf(glm::value_ptr(Projection));

		glm::vec3 eye = {0.5f, -0.25f, 1.0f};
		glm::vec3 targ = {0.0f, 0.0f, 0.0f};
		glm::vec3 up = {0.0f, 1.0f, 0.0f};

		if (i <= 1) {
			position = eye;
		}
		if (i > 1) {
			double xpos, ypos;
			glfwGetCursorPos(window, &xpos, &ypos);
			glfwSetCursorPos(window, screenW/2, screenH/2);

			float dx = float(screenW/2 - xpos );
			float dy = float( screenH/2 - ypos );

			if (fabs(dx) < 10 && fabs(dy) < 10) {
				theta += 0.005f * dx;
				phi   += -0.005f * dy;
			}

			glm::vec3 direction(
				sin(phi) * sin(theta),
				cos(phi),
				sin(phi) * cos(theta)
			);
			glm::normalize(direction);
			// std::cerr << "phi  : " << phi << std::endl;
			// std::cerr << "theta: " << theta << std::endl;
			// std::cerr << "direction = " << to_string(direction) << std::endl << std::endl;

			glm::vec3 right = glm::normalize(glm::cross(direction, up));

			// Move forward
			float speed = 1.5f;
			float deltaTime = 1.0f / 60.f;
			if (glfwGetKey( window, GLFW_KEY_UP ) == GLFW_PRESS){
				position += direction * deltaTime * speed;
			}
			// Move backward
			if (glfwGetKey( window, GLFW_KEY_DOWN ) == GLFW_PRESS){
				position -= direction * deltaTime * speed;
			}
			// Strafe right
			if (glfwGetKey( window, GLFW_KEY_RIGHT ) == GLFW_PRESS){
				position += right * deltaTime * speed;
			}
			// Strafe left
			if (glfwGetKey( window, GLFW_KEY_LEFT ) == GLFW_PRESS){
				position -= right * deltaTime * speed;
			}

			eye = position;
			targ = eye + direction;
		}

		glm::mat4 Vtrans = glm::mat4(1.0f);
		Vtrans[3][0] = -eye.x;
		Vtrans[3][1] = -eye.y;
		Vtrans[3][2] = -eye.z;

		glm::mat4 V = glm::mat4(1.0f);
		glm::vec3 D = glm::normalize(eye - targ); 
		glm::vec3 R = glm::normalize(glm::cross(up, D));
		up = glm::normalize(glm::cross(D, R));

		V[0][0] = R.x;
		V[1][0] = R.y;
		V[2][0] = R.z;

		V[0][1] = up.x;
		V[1][1] = up.y;
		V[2][1] = up.z;

		V[0][2] = D.x;
		V[1][2] = D.y;
		V[2][2] = D.z;

		V = V * Vtrans;

		glm::mat4 M = glm::mat4(1.0f);
		M[3][2] = -1.0 * i * 0.005f;		

		glm::mat4 MV = V * M;

		glMatrixMode( GL_MODELVIEW );
		glPushMatrix();
		glLoadMatrixf(glm::value_ptr(V));

		glUseProgram(programID);
		MVP = Projection * V * M;
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);
		glBindVertexArray(VertexArrayID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);
		glUseProgram(0);

}

		//draw axes last for blending
		ax.draw();
		plane.draw();


		glMatrixMode( GL_MODELVIEW );
		glPopMatrix();

		glMatrixMode(GL_PROJECTION);
		glPopMatrix();

		// Swap buffers
		glfwSwapBuffers(window);
		glfwPollEvents();


		++i;


	} // Check if the ESC key was pressed or the window was closed
	while( glfwGetKey(window, GLFW_KEY_ESCAPE ) != GLFW_PRESS &&
		   glfwWindowShouldClose(window) == 0 );

	// Cleanup VBO and shader
	glDisableVertexAttribArray(0);
	glDisableVertexAttribArray(1);
	glDeleteBuffers(1, &vertexbuffer);
	glDeleteBuffers(1, &colorbuffer);
	// glDeleteProgram(programID);
	glDeleteVertexArrays(1, &VertexArrayID);

	// Close OpenGL window and terminate GLFW
	glfwTerminate();

	return 0;
}

