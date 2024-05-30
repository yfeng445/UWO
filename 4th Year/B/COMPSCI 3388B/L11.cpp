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

#include <vector>
#include <iostream>

//Nope. You'll have to do this yourself for assignment 5
//#include "CamControls.hpp"
void cameraControlsGlobe(glm::mat4& V) {

}

void loadBMP(const char* imagepath, unsigned char** data, unsigned int* width, unsigned int* height) {

    // printf("Reading image %s\n", imagepath);

    // Data read from the header of the BMP file
    unsigned char header[54];
    unsigned int dataPos;
    unsigned int imageSize;
    // Actual RGB data

    // Open the file
    FILE * file = fopen(imagepath,"rb");
    if (!file){
        printf("%s could not be opened. Are you in the right directory?\n", imagepath);
        getchar();
        return;
    }

    // Read the header, i.e. the 54 first bytes

    // If less than 54 bytes are read, problem
    if ( fread(header, 1, 54, file)!=54 ){
        printf("Not a correct BMP file\n");
        fclose(file);
        return;
    }
    // A BMP files always begins with "BM"
    if ( header[0]!='B' || header[1]!='M' ){
        printf("Not a correct BMP file\n");
        fclose(file);
        return;
    }
    // Make sure this is a 24bpp file
    if ( *(int*)&(header[0x1E])!=0  ) {
    	printf("Not a correct BMP file\n");
	    fclose(file);
	    return;
	}
    if ( *(int*)&(header[0x1C])!=24 ) {
    	printf("Not a correct BMP file\n");
	    fclose(file);
	    return;
	}

    // Read the information about the image
    dataPos    = *(int*)&(header[0x0A]);
    imageSize  = *(int*)&(header[0x22]);
    *width      = *(int*)&(header[0x12]);
    *height     = *(int*)&(header[0x16]);

    // Some BMP files are misformatted, guess missing information
    if (imageSize==0)    imageSize=(*width)* (*height)*3; // 3 : one byte for each Red, Green and Blue component
    if (dataPos==0)      dataPos=54; // The BMP header is done that way

    // Create a buffer
    *data = new unsigned char [imageSize];

    // Read the actual data from the file into the buffer
    fread(*data,1,imageSize,file);

    fprintf(stderr, "Done reading!\n");

    // Everything is in memory now, the file can be closed.
    fclose (file);

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

static const GLfloat g_uv_buffer_data[] = {
	
	//bottom face
	0.0f, 0.0f,
	1.0f, 0.0f,
	1.0f, 1.0f, //first tri
	0.0f, 0.0f,
	1.0f, 1.0f,
	0.0f, 1.0f, //second tri

	//back face
	0.0f, 0.0f,
	1.0f, 0.0f,
	1.0f, 1.0f, //first tri
	0.0f, 0.0f,
	1.0f, 1.0f,
	0.0f, 1.0f, //second tri

	//right face
	1.0f, 0.0f,
	1.0f, 1.0f, //first tri
	0.0f, 0.0f,
	1.0f, 1.0f,
	0.0f, 1.0f, //second tri
	0.0f, 0.0f,

	//left face
	0.0f, 0.0f,
	1.0f, 0.0f,
	1.0f, 1.0f, //first tri
	0.0f, 0.0f,
	1.0f, 1.0f,
	0.0f, 1.0f, //second tri

	//top face
	0.0f, 0.0f,
	1.0f, 0.0f,
	1.0f, 1.0f, //first tri
	0.0f, 0.0f,
	1.0f, 1.0f,
	0.0f, 1.0f, //second tri

	//front face
	0.0f, 0.0f,
	1.0f, 0.0f,
	1.0f, 1.0f, //first tri
	0.0f, 0.0f,
	1.0f, 1.0f,
	0.0f, 1.0f, //second tri
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

		glColor3f(ycol.x, ycol.y, ycol.z);
		glVertex3f(origin.x, origin.y, origin.z);
		glVertex3f(origin.x, origin.y + extents.y, origin.z);

		glVertex3f(origin.x, origin.y + extents.y, origin.z);
		glVertex3f(origin.x, origin.y + extents.y, origin.z+0.1);
		glVertex3f(origin.x, origin.y + extents.y, origin.z);
		glVertex3f(origin.x, origin.y + extents.y, origin.z-0.1);
		
		glColor3f(zcol.x, zcol.y, zcol.z);
		glVertex3f(origin.x, origin.y, origin.z);
		glVertex3f(origin.x, origin.y, origin.z + extents.z);
		
		glVertex3f(origin.x, origin.y, origin.z + extents.z);
		glVertex3f(origin.x+0.1, origin.y, origin.z + extents.z);

		glVertex3f(origin.x, origin.y, origin.z + extents.z);
		glVertex3f(origin.x-0.1, origin.y, origin.z + extents.z);
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

	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);


    // Create the shaders
    GLuint VertexShaderID = glCreateShader(GL_VERTEX_SHADER);
    GLuint FragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
    std::string VertexShaderCode = "\
    	#version 330 core\n\
		// Input vertex data, different for all executions of this shader.\n\
		layout(location = 0) in vec3 vertexPosition;\n\
		layout(location = 1) in vec2 uv;\n\
		// Output data ; will be interpolated for each fragment.\n\
		out vec2 uv_out;\n\
		// Values that stay constant for the whole mesh.\n\
		uniform mat4 MVP;\n\
		void main(){ \n\
			// Output position of the vertex, in clip space : MVP * position\n\
			gl_Position =  MVP * vec4(vertexPosition,1);\n\
			// The color will be interpolated to produce the color of each fragment\n\
			uv_out = uv;\n\
		}\n";

    // Read the Fragment Shader code from the file
    std::string FragmentShaderCode = "\
		#version 330 core\n\
		in vec2 uv_out; \n\
		uniform sampler2D tex;\n\
		void main() {\n\
			gl_FragColor = texture(tex, uv_out);\n\
		}\n";
    char const * VertexSourcePointer = VertexShaderCode.c_str();
    glShaderSource(VertexShaderID, 1, &VertexSourcePointer , NULL);
    glCompileShader(VertexShaderID);

    // Compile Fragment Shader
    char const * FragmentSourcePointer = FragmentShaderCode.c_str();
    glShaderSource(FragmentShaderID, 1, &FragmentSourcePointer , NULL);
    glCompileShader(FragmentShaderID);

    GLuint ProgramID = glCreateProgram();
    glAttachShader(ProgramID, VertexShaderID);
    glAttachShader(ProgramID, FragmentShaderID);
    glLinkProgram(ProgramID);

    glDetachShader(ProgramID, VertexShaderID);
    glDetachShader(ProgramID, FragmentShaderID);

    glDeleteShader(VertexShaderID);
    glDeleteShader(FragmentShaderID);


    unsigned char* data;
    GLuint width, height;
    loadBMP("texture.bmp", &data, &width, &height);
    GLuint textureID;
    glGenTextures(1, &textureID);
    glBindTexture(GL_TEXTURE_2D, textureID);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_BGR, GL_UNSIGNED_BYTE, data);
    glGenerateMipmap(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_2D, 0);

	// Get a handle for our "MVP" uniform
	GLuint MatrixID = glGetUniformLocation(ProgramID, "MVP");
	glm::mat4 MVP;

	GLuint vaoID;
	glGenVertexArrays(1, &vaoID);
	glBindVertexArray(vaoID);

	int nVerts = 36;
	GLuint vboID;
	glGenBuffers(1, &vboID);
	glBindBuffer(GL_ARRAY_BUFFER, vboID);
	glBufferData(GL_ARRAY_BUFFER, sizeof(GL_FLOAT)*3*nVerts, g_vertex_buffer_data, GL_STATIC_DRAW);
	// 1st attribute buffer : vertices
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(
		0,                  // attribute. No particular reason for 0, but must match the layout in the shader.
		3,                  // size
		GL_FLOAT,           // type
		GL_FALSE,           // normalized?
		0,                  // stride
		(void*) 0           // buffer offset
	);

	GLuint texCoordID;
	glGenBuffers(1, &texCoordID);
	glBindBuffer(GL_ARRAY_BUFFER, texCoordID);
	glBufferData(GL_ARRAY_BUFFER, sizeof(GL_FLOAT)*2*nVerts, g_uv_buffer_data, GL_STATIC_DRAW);
	// 2nd attribute buffer : UV
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(
		1,                                // attribute. No particular reason for 1, but must match the layout in the shader.
		2,                                // size
		GL_FLOAT,                         // type
		GL_FALSE,                         // normalized?
		0,                                // stride
		(void*) 0 			              // data
	);
	glBindVertexArray(0); //good practice to unbind VAO when done specifying vertices


	Axes ax(glm::vec3(0.0f, 0.0f, 0.0f), glm::vec3(4.0f, 4.0f, 4.0f));
	Plane plane(5.0f);

	do{
		// Clear the screen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


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

		glm::mat4 M = glm::mat4(1.0f);

		glm::mat4 MV = V * M;
		glLoadMatrixf(glm::value_ptr(V));

		MVP = Projection * V * M;

		glActiveTexture(GL_TEXTURE0);
		glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);


		glUseProgram(ProgramID);
		glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &MVP[0][0]);

		glBindVertexArray(vaoID);
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // 12*3 indices starting at 0 -> 12 triangles
		glBindVertexArray(0);

		glUseProgram(0);
		glBindTexture(GL_TEXTURE_2D, 0);

		ax.draw();
		plane.draw();

		// Swap buffers
		glfwSwapBuffers(window);
		glfwPollEvents();


	} // Check if the ESC key was pressed or the window was closed
	while( glfwGetKey(window, GLFW_KEY_ESCAPE ) != GLFW_PRESS &&
		   glfwWindowShouldClose(window) == 0 );

	// Cleanup shader
	glDeleteProgram(ProgramID);

	// Close OpenGL window and terminate GLFW
	glfwTerminate();

	return 0;
}

