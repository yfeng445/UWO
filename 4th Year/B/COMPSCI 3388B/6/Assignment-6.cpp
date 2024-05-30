#include <stdio.h>
#include <stdlib.h>
#include <cmath>
#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <glm/glm.hpp>
#include <glm/gtc/type_ptr.hpp>
#include <glm/gtx/string_cast.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <iostream>
#include <vector>
#include "LoadBMP.hpp"
#include "shader.hpp"
#include "Plane.hpp"

GLFWwindow* window;
using namespace glm;
static const double _PI = 2.0 * asin(1);

void cameraControlsGlobe(glm::mat4& V, float start) {

	glm::vec3 eye = { start, start, start };
	glm::vec3 targ = { 0.0f, 0.0f, 0.0f };
	glm::vec3 up = { 0.0f, 1.0f, 0.0f };
	static float radiusFromOrigin = glm::length(eye);

	static glm::vec3 position = eye;
	//exactly the angles to look at origin from (x,y,z) = (+v, +v/2, +v);
	static GLfloat theta = 0.0f;//0.25f*3.14159f;
	static GLfloat phi = 0.392f * 3.14159f;

	static double mouseDownX;
	static double mouseDownY;
	static bool firstPress = true;
	static double lastTime = glfwGetTime();

	double dx = 0.0, dy = 0.0;
	int state = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);
	if (state == GLFW_PRESS)
	{
		if (firstPress) {
			glfwGetCursorPos(window, &mouseDownX, &mouseDownY);
		}
		firstPress = false;

		double xpos, ypos;
		glfwGetCursorPos(window, &xpos, &ypos);

		dx = xpos - mouseDownX;
		dy = ypos - mouseDownY;

		mouseDownX = xpos;
		mouseDownY = ypos;
	}
	if (state == GLFW_RELEASE) {
		firstPress = true;
	}

	theta += 0.002f * dx;
	phi += -0.002f * dy;
	if (theta > 2 * _PI) {
		theta -= 2 * _PI;
	}
	if (phi >= _PI) {
		phi = 0.9999999 * _PI;
	}
	if (phi <= 0) {
		phi = 0.0000001;//0.9999999 * _PI;
	}

	glm::vec3 direction(
		sin(phi) * cos(theta),
		cos(phi),
		sin(phi) * sin(theta)
	);
	glm::normalize(direction);

	float speed = 0.25f * radiusFromOrigin; //move faster further away
	double currentTime = glfwGetTime();
	float deltaTime = (currentTime - lastTime);
	lastTime = currentTime;

	// Move forward
	if (glfwGetKey(window, GLFW_KEY_UP) == GLFW_PRESS) {
		radiusFromOrigin -= deltaTime * speed;
	}
	// Move backward
	if (glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_PRESS) {
		radiusFromOrigin += deltaTime * speed;
	}

	position = direction * radiusFromOrigin;
	V = glm::lookAt(position, targ, up);

}


int main(int argc, char* argv[]){

	float screenW = 1500;
	float screenH = 1500;
	float stepsize = 1.0f;

	float xmin = -10;
	float xmax = 10;

	if (argc > 1) {
		screenW = atoi(argv[1]);
	}
	if (argc > 2) {
		screenH = atoi(argv[2]);
	}
	if (argc > 3) {
		stepsize = atof(argv[3]);
	}
	if (argc > 4) {
		xmin = atof(argv[4]);
	}
	if (argc > 5) {
		xmax = atof(argv[5]);
	}

	// Initialise GLFW
	if (!glfwInit()){
		fprintf(stderr, "Failed to initialize GLFW\n");
		getchar();
		return -1;
	}

	glfwWindowHint(GLFW_SAMPLES, 4);

	// Open a window and create its OpenGL context
	window = glfwCreateWindow(screenW, screenH, "Phong", NULL, NULL);
	if (window == NULL) {
		fprintf(stderr, "Failed to open GLFW window. If you have an Intel GPU, they are not 3.3 compatible. Try the 2.1 version of the tutorials.\n");
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
	glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

	glMatrixMode(GL_PROJECTION);
	glPushMatrix();

	glm::mat4 Projection = glm::perspective(glm::radians(45.0f), screenW / screenH, 0.001f, 1000.0f);
	glLoadMatrixf(glm::value_ptr(Projection));

	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();
	glm::vec3 eye = { 5.0f, 5.0f, 5.0f };
	glm::vec3 up = { 0.0f, 1.0f, 0.0f };
	glm::vec3 center = { 0.0f, 0.0f, 0.0f };
	//glm::mat4 V;

	// view matrix
	glm::mat4 V = glm::lookAt(eye, center, up);
	glLoadMatrixf(glm::value_ptr(V));

	glm::mat4 M2(1.0f);
	glm::mat4 MFloor(1.0f);

	MFloor = glm::translate(MFloor, glm::vec3(0.f, -1.1f, 0.f));

	Plane floor(10.0f, "Assets\\water.bmp");

	//Axes worldaxes({ min,min,min }, { max - min,max - min, max - min });
	glm::vec3 lightpos(5.0f, 5.0f, 5.0f);
	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);

	//glm::vec4 color1(0.f, 0.8f, 0.8f, 1.0f);
	glm::vec4 color2(0.f, 0.8f, 0.8f, 1.0f);
	glm::vec4 color3(0.f, 0.8f, 0.8f, 1.0f);

	//shininess
	float alpha1 = 2;
	float alpha2 = 16;
	float alpha3 = 64;

	do {
		// Clear the screen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		floor.draw(lightpos, MFloor, V, Projection, color3, alpha1);

		cameraControlsGlobe(V, 10);
		glMatrixMode(GL_MODELVIEW);
		glLoadMatrixf(glm::value_ptr(V));

		// Swap buffers
		glfwSwapBuffers(window);
		glfwPollEvents();


	} // Check if the ESC key was pressed or the window was closed
	while (glfwGetKey(window, GLFW_KEY_ESCAPE) != GLFW_PRESS && glfwWindowShouldClose(window) == 0);

	// Close OpenGL window and terminate GLFW
	glfwTerminate();
	return 0;
}

