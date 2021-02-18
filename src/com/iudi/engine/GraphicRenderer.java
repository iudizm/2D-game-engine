package com.iudi.engine;

import java.awt.image.DataBufferInt;

public class GraphicRenderer 
{
	private int pixelW, pixelH;
	private int[] pixel;
	
	public GraphicRenderer(GameContainer gameContainer)
	{
		pixelW = gameContainer.getWidth();
		pixelH = gameContainer.getHeigh();
		
		pixel = ((DataBufferInt)gameContainer.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	public void clear()
	{
		for(int i = 0; i < pixel.length; i++)
		{
			pixel[i] = 0 ;
		}
	}
	
}
