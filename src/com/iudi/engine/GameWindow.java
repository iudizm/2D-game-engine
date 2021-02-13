package com.iudi.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class GameWindow 
{
	private JFrame frame;
	private BufferedImage image; // imagem armazenada na memoria
	private Canvas canvas;
	private BufferStrategy bufferStrategy;
	private Graphics graphic;
	
	public GameWindow(GameContainer gameContainer) 
	{
		image = new BufferedImage(gameContainer.getWidth(), gameContainer.getHeigh(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension dimension = new Dimension((int)(gameContainer.getWidth() * gameContainer.getScale()), (int)(gameContainer.getHeigh() * gameContainer.getScale()));
		canvas.setPreferredSize(dimension);
		canvas.setMaximumSize(dimension);
		canvas.setMinimumSize(dimension);
		
		frame = new JFrame(gameContainer.getContainerTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		graphic = bufferStrategy.getDrawGraphics();
	}
	
	public void update() 
	{
		graphic.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bufferStrategy.show();
	}

	public BufferedImage getImage() {
		return image;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
