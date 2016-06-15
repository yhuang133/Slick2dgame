package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import util.Box;

public abstract class Entity extends Box{
	public Image image;
	public Color color;
	public boolean colliding = false;
	public boolean collideable = false;
	public float xSpeed;
	public float ySpeed;

	public Entity() {
		init();
	}
	
	public abstract void init();

	public void render(GameContainer gc, Graphics g) {
		if(image != null) {
			image.setRotation((float)angleToTurn);
			x += xSpeed;
			y += ySpeed;
			image.draw(x, y, width, height, color);
		
		}
	}
	
	public abstract void update(GameContainer gc, int delta);
	
	public float getXSpeed()
	{
		return xSpeed;
	}
	
	public float getYSpeed()
	{
		return ySpeed;
	}
	
	public void setXSpeed(float newSpeed)
	{
		xSpeed = newSpeed;
	}
	
	public void setYSpeed(float newSpeed)
	{
		ySpeed = newSpeed;
	}
	

	
}
