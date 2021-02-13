package com.iudi.engine;

public class GameContainer implements Runnable {
	
	private Thread thread;
	private final double UPDATE_LIMIT = 1.0/60.0;
	private boolean running = false;
	
	private int width = 320, heigh = 240;
	private float scale = 1f;
	private String containerTitle = "SEMNOME - O JOGO";

	public GameContainer() 
	{
		
	}
	
	public void start() 
	{
	thread = new Thread(this);
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
		int fps = 0;
					
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
				//TODO: atualizar o frame
				
				if(frameTime >= 1.0)
				{
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("fps: " + fps);
				}
				
			}
			
			if(render)
			{
				//TODO: renderizar os 'frames'
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
}
