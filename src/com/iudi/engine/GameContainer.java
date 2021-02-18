package com.iudi.engine;

public class GameContainer implements Runnable {
	
	private Thread thread;
	private GameWindow window;
	private GraphicRenderer graphicRenderer;
	private Input input;
	
	private final double UPDATE_LIMIT = 1.0/60.0;
	private boolean running = false;
	
	private int width = 900, heigh = 600;
	private float scale = 1f;
	private String containerTitle = "SemNome - the game";
	

	public GameContainer() 
	{
		
	}
	
	public void start() 
	{
	window = new GameWindow(this);
	graphicRenderer = new GraphicRenderer(this);
	thread = new Thread(this);
	input = new Input(this);

	thread.run();
	}
	
	public void stop() 
	{
		
	}
	
	// MAIN GAME LOOP
	public void run() 
	{
		running = true;
		
		boolean render = false;
		
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		double frameTime = 0;
		int frames = 0;
		int fps;
					
		while(running) 
		{
			render = false;
			
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= UPDATE_LIMIT) 
			{
				unprocessedTime -= UPDATE_LIMIT;
				render = true;
				
				// System.out.println("x: " + input.getMouseX() + "  y: " + input.getMouseY());
				input.update();
				
				
				if(frameTime >= 1.0)
				{
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("FPS: " + fps);
				}
			}
			
			if(render)
			{
				graphicRenderer.clear();
				window.update();
				frames++;
			}
			else 
			{
				try 
				{
					Thread.sleep(1); // ALIVÍA A CPU
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		dispose();
	}
	
	private void dispose() 
	{
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeigh() {
		return heigh;
	}
	
	public void setHeigh(int heigh) {
		this.heigh = heigh;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public static void main(String[] args) {
		GameContainer gameContainer = new GameContainer();
		gameContainer.start();
	}

	public String getContainerTitle() {
		return containerTitle;
	}

	public void setContainerTitle(String containerTitle) {
		this.containerTitle = containerTitle;
	}

	public GameWindow getWindow() {
		return window;
	}
}
