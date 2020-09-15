package Loops;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import Rendering.*;

public class Launcher {
	
	static Window window;
	public static void main(String[] args) throws LWJGLException {
		window = new Window(1000,720,"Hello");
		
		Mesh testMesh = new Mesh(
		new float[] {
			-1,-1, 0,
			 0, 1, 0,
			 1,-1, 0,
			});

		Shader shader = new Shader();
		shader.create("shader");
				
		while (!Display.isCloseRequested()) {
			window.update();
			testMesh.render();
			shader.useShader();
		}
		shader.onEnd();
		testMesh.onEnd();
		window.onEnd();
	}
}
