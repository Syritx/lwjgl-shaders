package Rendering;

import java.io.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader {

	int vertexShaderProgram, fragmentShaderProgram, shaderProgram;
	
	String readShaderSource(String file) {
		BufferedReader reader = null;
		String source = null;
		
		try {
			reader = new BufferedReader(new FileReader("src/Shaders/"+file));
			String line;
			
			while ((line = reader.readLine()) != null) {
				source += line + "\n";
			}
			reader.close();
		}
		catch(IOException e) {}
		System.out.println(source);
		
		return source;
	}
	
	public boolean create(String shaderSource) {
		int success;
		
		vertexShaderProgram = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vertexShaderProgram, readShaderSource(shaderSource+"vx.glsl"));
		GL20.glCompileShader(vertexShaderProgram);
		
		success = GL20.glGetShaderi(vertexShaderProgram, GL20.GL_COMPILE_STATUS);
		if (success == GL11.GL_FALSE) return false;
		
		fragmentShaderProgram = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragmentShaderProgram,readShaderSource(shaderSource+"fr.glsl"));
		GL20.glCompileShader(fragmentShaderProgram);
		
		success = GL20.glGetShaderi(fragmentShaderProgram, GL20.GL_COMPILE_STATUS);
		if (success == GL11.GL_FALSE) return false;
		
		shaderProgram = GL20.glCreateProgram();
		GL20.glAttachShader(shaderProgram, vertexShaderProgram);
		GL20.glAttachShader(shaderProgram, fragmentShaderProgram);
		
		GL20.glLinkProgram(shaderProgram);
		
		success = GL20.glGetProgrami(shaderProgram, GL20.GL_LINK_STATUS);
		if (success == GL11.GL_FALSE) return false;
		
		GL20.glValidateProgram(shaderProgram);
		success = GL20.glGetProgrami(shaderProgram, GL20.GL_VALIDATE_STATUS);
		if (success == GL11.GL_FALSE) return false;
		
		return true;
	}
	
	public void onEnd() {
		GL20.glDetachShader(shaderProgram, vertexShaderProgram);
		GL20.glDetachShader(shaderProgram, fragmentShaderProgram);
		
		GL20.glDeleteShader(vertexShaderProgram);
		GL20.glDeleteShader(fragmentShaderProgram);
		GL20.glDeleteProgram(shaderProgram);
	}
	
	public void useShader() {
		GL20.glUseProgram(shaderProgram);
	}
}
