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

GLfloat g_vert_buffer[] = {
	0.0f, 0.8f, 0.0f,
	0.8f, -0.5f, 0.0f,
	-0.8f, -0.5f, 0.0f,
};

GLfloat g_uv_buffer[] = {
	0.5f, 0.5f,
	0.20f, 0.20f,
	0.8f, 0.20f,
};

GLuint indexArray[] = {
	0, 1, 2
};


unsigned char TEXTURE_SIZE = 32;

void createTextureImage(GLfloat** texPixels, float R, float G, float B) {
	int textureSize = TEXTURE_SIZE;

	*texPixels = (GLfloat*) malloc(3*sizeof(GLfloat)*TEXTURE_SIZE*TEXTURE_SIZE);
    float sigma2 = textureSize * 0.5f;
    for (int i = 0; i < textureSize; ++i) {
        float i1 = i-textureSize / 2.0f;
        for (int j = 0; j < textureSize; ++j) {
            float j1 = j - textureSize / 2.0f;

            int Ridx = i*(textureSize*3) + j*3;
            int Gidx = i*(textureSize*3) + j*3 + 1;
            int Bidx = i*(textureSize*3) + j*3 + 2;

            (*texPixels)[Ridx] = pow(R*exp(-1.0f*((i1*i1)/(2*sigma2) + (j1*j1)/(2*sigma2))), 2.2);
            (*texPixels)[Gidx] = pow(G*exp(-1.0f*((i1*i1)/(2*sigma2) + (j1*j1)/(2*sigma2))), 2.2);
            (*texPixels)[Bidx] = pow(B*exp(-1.0f*((i1*i1)/(2*sigma2) + (j1*j1)/(2*sigma2))), 2.2);
        }
    }
}

int main( int argc, char* argv[])
{

	// Initialise GLFW
	if( !glfwInit() )
	{
		fprintf( stderr, "Failed to initialize GLFW\n" );
		getchar();
		return -1;
	}

	// Open a window and create i 	ts OpenGL context
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

	GLuint VertexShaderID = glCreateShader(GL_VERTEX_SHADER);
    GLuint FragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);

    //TODO FILL IN SHADER CODE ///////////////////////////////////////////////////////////
    std::string VertexShaderCode = "\
    	#version 440\n\
		//SHADER VARIABLES\n\
		void main(){ \n\
			//SHADER CODE\n\
		}\n";

    // Read the Fragment Shader code from the file
    std::string FragmentShaderCode = "\
		#version 440\n\
		//SHADER VARIABLES\n\
		void main() {\n\
			//SHADER CODE\n\
		}\n";

    char const * VertexSourcePointer = VertexShaderCode.c_str();
    glShaderSource(VertexShaderID, 1, &VertexSourcePointer , NULL);
    glCompileShader(VertexShaderID);

    GLint Result = GL_FALSE;
    int InfoLogLength;
    // Check Vertex Shader
    glGetShaderiv(VertexShaderID, GL_COMPILE_STATUS, &Result);
    glGetShaderiv(VertexShaderID, GL_INFO_LOG_LENGTH, &InfoLogLength);
    if ( InfoLogLength > 0 ){
        std::vector<char> VertexShaderErrorMessage(InfoLogLength+1);
        glGetShaderInfoLog(VertexShaderID, InfoLogLength, NULL, &VertexShaderErrorMessage[0]);
        printf("%s\n", &VertexShaderErrorMessage[0]);
    }

    // Compile Fragment Shader
    char const * FragmentSourcePointer = FragmentShaderCode.c_str();
    glShaderSource(FragmentShaderID, 1, &FragmentSourcePointer , NULL);
    glCompileShader(FragmentShaderID);

    // Link the program
    GLuint ProgramID = glCreateProgram();
    glAttachShader(ProgramID, VertexShaderID);
    glAttachShader(ProgramID, FragmentShaderID);
    glLinkProgram(ProgramID);

    glDetachShader(ProgramID, VertexShaderID);
    glDetachShader(ProgramID, FragmentShaderID);

    glDeleteShader(VertexShaderID);
    glDeleteShader(FragmentShaderID);

	GLuint tex1ID = glGetUniformLocation(ProgramID, "tex1");
	GLuint tex2ID = glGetUniformLocation(ProgramID, "tex2");
	float blend1Val = 0.5f;
	float blend2Val = 0.5f;
	GLuint blend1ID = glGetUniformLocation(ProgramID, "blend1");
	GLuint blend2ID = glGetUniformLocation(ProgramID, "blend2");

	//Load texture data into texture units
 	GLuint textureID[2];
    glGenTextures(2, textureID);
    glBindTexture(GL_TEXTURE_2D, textureID[0]);

	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB32F, TEXTURE_SIZE, TEXTURE_SIZE, 0, GL_BGR, GL_FLOAT, 0);
	//fill server side texture data
	GLfloat* texPixels = NULL;
	createTextureImage(&texPixels, 0.0f, 1.0f, 0.0f);
    glTexSubImage2D(
        GL_TEXTURE_2D,      //target tex
        0,                  //level
        0,                  //xoffset
        0,                  //yoffset
        TEXTURE_SIZE,        //width
        TEXTURE_SIZE,        //height
        GL_RGB,             //texture format
        GL_FLOAT,           //pixel data type
        texPixels       //the data
    );
    free(texPixels);
    glGenerateMipmap(GL_TEXTURE_2D);

    glBindTexture(GL_TEXTURE_2D, textureID[1]);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB32F, TEXTURE_SIZE, TEXTURE_SIZE, 0, GL_BGR, GL_FLOAT, 0);
	//fill server side texture data
	texPixels = NULL;
	createTextureImage(&texPixels, 0.0f, 0.0f, 1.0f);
    glTexSubImage2D(
        GL_TEXTURE_2D,      //target tex
        0,                  //level
        0,                  //xoffset
        0,                  //yoffset
        TEXTURE_SIZE,        //width
        TEXTURE_SIZE,        //height
        GL_RGB,             //texture format
        GL_FLOAT,           //pixel data type
        texPixels       //the data
    );
    free(texPixels);
    glGenerateMipmap(GL_TEXTURE_2D);

    glBindTexture(GL_TEXTURE_2D, 0);


	// Ensure we can capture the escape key being pressed below
	glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);

	// Dark blue background
	glClearColor(0.2f, 0.2f, 0.3f, 0.0f);

	do{
		// Clear the screen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glUseProgram(ProgramID);

		glUniform1f(blend1ID, blend1Val);
		glUniform1f(blend2ID, blend2Val);
		glUniform1i(tex1ID, 0);
		glUniform1i(tex2ID, 1);

		glActiveTexture(GL_TEXTURE0);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, textureID[0]);
		glActiveTexture(GL_TEXTURE1);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, textureID[1]);

		glEnableVertexAttribArray(0);
		glVertexAttribPointer(
			0,
			3,
			GL_FLOAT,
			GL_FALSE,
			0,
			g_vert_buffer
		);
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(
			1,
			2,
			GL_FLOAT,
			GL_FALSE,
			0,
			g_uv_buffer
		);

		glDrawElements(GL_TRIANGLES, 3, GL_UNSIGNED_INT, indexArray);

		glActiveTexture(GL_TEXTURE1);
		glDisable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, 0);
		glActiveTexture(GL_TEXTURE0);
		glDisable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, 0);

		glUseProgram(0);

		// Swap buffers
		glfwSwapBuffers(window);
		glfwPollEvents();


	} // Check if the ESC key was pressed or the window was closed
	while( glfwGetKey(window, GLFW_KEY_ESCAPE ) != GLFW_PRESS &&
		   glfwWindowShouldClose(window) == 0 );

	// Close OpenGL window and terminate GLFW
	glfwTerminate();

	return 0;
}

