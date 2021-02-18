package com.iudi.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private GameContainer gameContainer;
	
	private final int NUMBER_OF_KEYS = 256;
	private boolean[] keys = new boolean[NUMBER_OF_KEYS];
	private boolean[] keysAtLast = new boolean[NUMBER_OF_KEYS];
	
	private final int NUMBER_OF_BUTTONS = 5;
	private boolean[] buttons = new boolean[NUMBER_OF_BUTTONS];
	private boolean[] buttonsAtLast = new boolean[NUMBER_OF_BUTTONS];
	
	private int mouseX, mouseY, scroll;
	
	public Input(GameContainer gameContainer)
	{
		this.gameContainer = gameContainer;
		mouseX = 0;
		mouseY = 0;
		scroll = 0;
		
		gameContainer.getWindow().getCanvas()	.addKeyListener(this);
		gameContainer.getWindow().getCanvas().addMouseListener(this);
		gameContainer.getWindow().getCanvas().addMouseMotionListener(this);
		gameContainer.getWindow().getCanvas().addMouseWheelListener(this);
	}
	
	public void update()
	{
		scroll = 0;
		
		for(int i = 0; i < NUMBER_OF_KEYS; i++)
		{
			keysAtLast[i] = keys[i];
		}
		for(int i = 0; i < NUMBER_OF_BUTTONS; i++)
		{
			buttonsAtLast[i] = buttons[i];
		}
	}
	
	public boolean isKey(int keyCode)
	{
		return keys[keyCode];
	}
	
	public boolean isKeyUP(int keyCode)
	{
		return !keys[keyCode] && keysAtLast[keyCode];
	}
	
	public boolean isKeyDown(int keyCode)
	{
		return keys[keyCode] && !keysAtLast[keyCode];
	}
	
	public boolean isButton(int button)
	{
		return buttons[button];
	}
	
	public boolean isButtonUp(int button)
	{
		return !buttons[button] && buttonsAtLast[button];
	}
	
	public boolean isButtonDown(int button)
	{
		return buttons[button] && !buttonsAtLast[button];
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		scroll = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		mouseX = (int) (e.getX() / gameContainer.getScale());
		mouseY = (int) (e.getY() / gameContainer.getScale());	
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = (int) (e.getX() / gameContainer.getScale());
		mouseY = (int) (e.getY() / gameContainer.getScale());
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public int getScroll() {
		return scroll;
	}
	
	
}
