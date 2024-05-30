
class Plane {

public:
	enum PLANE_WHICH {
		x,
		y,
		z
	};

private:
	PLANE_WHICH plane = PLANE_WHICH::y;

	glm::vec4 color = glm::vec4(0.9f, 0.9f, 0.9f, 0.1f);

	GLfloat size;
	GLfloat offset = 0;
	GLuint texID = 0;

	GLuint programID;

    GLuint MVPID;
    GLuint MID;
    GLuint VID;
    GLuint LightPosID;
    GLuint colorID;
    GLuint alphaID;


public:

	Plane(GLfloat sz, std::string textureFile) : size(sz) {

		unsigned char* data;
		unsigned int width, height;
		loadBMP(textureFile.c_str(), &data, &width, &height);
		fprintf(stderr, "w: %d, h: %d\n", width, height);

		glGenTextures(1, &texID);
		glBindTexture(GL_TEXTURE_2D, texID);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_BGR, GL_UNSIGNED_BYTE, data);
    	glGenerateMipmap(GL_TEXTURE_2D);
	    glBindTexture(GL_TEXTURE_2D, 0);

        programID = LoadShaders( "PhongTexture.vertexshader", "PhongTexture.fragmentshader" );
        // programID = LoadShaders( "TransformVertexShader.vertexshader", "ColorFragmentShader.fragmentshader" );

        // Get a handle for our "MVP" uniform
        MVPID = glGetUniformLocation(programID, "MVP");
        MID = glGetUniformLocation(programID, "M");
        VID = glGetUniformLocation(programID, "V");
        LightPosID = glGetUniformLocation(programID, "LightPosition_worldspace");
        colorID = glGetUniformLocation(programID, "modelcolor");
        alphaID = glGetUniformLocation(programID, "alpha");

        glUseProgram(programID);
        glm::mat4 M(1.0f);
        glUniformMatrix4fv(MID, 1, GL_FALSE, &M[0][0]); //model matrix always identity.


	}



	Plane(GLfloat sz, PLANE_WHICH which) : size(sz), plane(which) {}
	Plane(GLfloat sz, PLANE_WHICH which, float set) : size(sz), plane(which), offset(set) {}


	void draw(glm::vec3 lightPos, glm::mat4 M, glm::mat4 V, glm::mat4 P, glm::vec4 color, float alpha) {
			static float verts[] = {
				-size, offset, -size,
				size, offset, -size,
				size, offset, size,
				-size, offset, size
			};

			static float uv[] = {
				0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f
			};

			static float normals[] = {
				0, 1, 0,
				0, 1, 0,
				0, 1, 0,
				0, 1, 0,
			};



		    glm::mat4 MVP = P*V*M;
		    glUseProgram(programID);

		    glUniformMatrix4fv(MVPID, 1, GL_FALSE, &MVP[0][0]);
		    glUniformMatrix4fv(VID, 1, GL_FALSE, &V[0][0]);
		    glUniform3f(LightPosID, lightPos.x, lightPos.y, lightPos.z);
		    glUniform1f(alphaID, alpha);
			glBindTexture(GL_TEXTURE_2D, texID);

		glEnableVertexAttribArray(0);
		glVertexAttribPointer(
			0,                                // attribute. No particular reason for 1, but must match the layout in the shader.
			3,                                // size
			GL_FLOAT,                         // type
			GL_FALSE,                         // normalized?
			0,                                // stride
			verts                        // array buffer offset
		);
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(
			1,                                // attribute. No particular reason for 1, but must match the layout in the shader.
			3,                                // size
			GL_FLOAT,                         // type
			GL_FALSE,                         // normalized?
			0,                                // stride
			normals                          // array buffer offset
		);

		// glTexCoordPointer(2, GL_FLOAT, 0, this->texcoordArray);
		glEnableVertexAttribArray(2);
		glVertexAttribPointer(
			2,                                // attribute. No particular reason for 1, but must match the layout in the shader.
			2,                                // size
			GL_FLOAT,                         // type
			GL_FALSE,                         // normalized?
			0,                                // stride
			uv                         // array buffer offset
		);



		glDrawArrays(GL_QUADS, 0, 4);


			glBindTexture(GL_TEXTURE_2D, 0);
			glUseProgram(0);
	}


	void draw() {


		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		float xmin, xmax, ymin, ymax, zmin, zmax;
		if (plane == PLANE_WHICH::x) {
			xmin = -size;
			xmax = size;
			ymin = ymax = 0;
			zmin = -size;
			zmax = size;
		}

			glBegin(GL_QUADS);
			glColor4f(color.x, color.y, color.z, color.w);
			if (plane == PLANE_WHICH::x) {
					glVertex3f(offset, -size, -size);
					glVertex3f(offset, size,  -size);
					glVertex3f(offset, size,  size);
					glVertex3f(offset, -size, size);
				}
				if (plane == PLANE_WHICH::y) {
					glVertex3f(-size, offset, -size);
					glVertex3f(size, offset, -size);
					glVertex3f(size, offset, size);
					glVertex3f(-size, offset, size);
				}
				if (plane == PLANE_WHICH::z) {
					glVertex3f(-size, -size, offset);
					glVertex3f(size, -size, offset);
					glVertex3f(size, size, offset);
					glVertex3f(-size, size, offset);
				}
			glEnd();

			// glBegin(GL_LINES);
			// glColor4f(color.x, color.y, color.z, color.w+0.2f);

			// 	for (int i = -size; i < size; ++i) {
			// 		glVertex3f(1.0f*i, 0.0f, -size);
			// 		glVertex3f(1.0f*i, 0.0f, size);
			// 		glVertex3f(size, 0.0f, 1.0f*i);
			// 		glVertex3f(-size, 0.0f, 1.0f*i);
			// 	}
			// glEnd();


		glPopMatrix();
		glDisable(GL_BLEND);
	}

};
