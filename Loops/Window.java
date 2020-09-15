package Loops;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class Window {
	
	public Window(int WIDTH, int HEIGHT, String title) throws LWJGLException {
		
		ContextAttribs attribs = new ContextAttribs(3,2);
		attribs.withForwardCompatible(true);
		attribs.withProfileCore(true);
		
		Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
		Display.create(new PixelFormat(),attribs);
		Display.setTitle("OPENGL TEST");
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0, 0, 0, 0);
	}
	
	public void update() {
		Display.sync(120);
		Display.update();
	}
	
	public void onEnd() {
		Display.destroy();
	}
}
