// draw cube edges

void cube(float min, float max) {
	glBegin(GL_LINES);
	glColor3f(1.0f, 1.0f, 1.0f);
	//set the length of line
	glVertex3f(min, min, min);
	glVertex3f(max, min, min);

	glVertex3f(min, min, min);
	glVertex3f(min, max, min);

	glVertex3f(min, min, min);
	glVertex3f(min, min, max);

	// red vertex
	glVertex3f(max, min, min);
	glVertex3f(max, max, min);

	glVertex3f(max, min, min);
	glVertex3f(max, min, max);

	glVertex3f(max, min, min);
	glVertex3f(min, min, min);

	// green vertex
	glVertex3f(min, max, min);
	glVertex3f(max, max, min);

	glVertex3f(min, max, min);
	glVertex3f(min, max, max);

	glVertex3f(min, max, min);
	glVertex3f(min, min, min);

	// blue vertex
	glVertex3f(min, min, max);
	glVertex3f(max, min, max);

	glVertex3f(min, min, max);
	glVertex3f(min, max, max);

	glVertex3f(min, min, max);
	glVertex3f(min, min, min);

	// opposite corner vertex
	glVertex3f(max, max, max);
	glVertex3f(max, min, max);

	glVertex3f(max, max, max);
	glVertex3f(min, max, max);

	glVertex3f(max, max, max);
	glVertex3f(max, max, min);

	glEnd();
}
