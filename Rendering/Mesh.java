package Rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Mesh {
	int vertexArrayObject;
	int vertexBufferObject;
	int verticesCount;
	
	public Mesh(float vertices[]) {
		verticesCount = vertices.length/3;
		
		vertexArrayObject = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArrayObject);
		
		vertexBufferObject = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferObject);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		
		GL30.glBindVertexArray(0);
	}
	
	public void onEnd() {
		GL30.glDeleteVertexArrays(vertexArrayObject);
		GL15.glDeleteBuffers(vertexBufferObject);
	}
	
	public void render() {
		GL30.glBindVertexArray(vertexArrayObject);
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES,0,verticesCount);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}
