package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import game.Resources;

public class Projectile extends Entity
{
	private final float maxSpeed = 7;
	
	private float targetX;
	private float targetY;
	
	private int timer;
	
	private boolean justClicked;
	
	private Hero h;
	
	public Projectile(Hero hero)
	{
		this.h = hero;
	}

	@Override
	public void init() 
	{
		scale = 2;
		width = 16 * scale;
		height = 16 * scale;
		targetX = 0;
		targetY = 0;
		x = 0;
		y = 0;
		justClicked = true;
		setTimer(0);
		image = Resources.getImage("fireball");

	}

	@Override
	public void update (GameContainer gc, int delta) 
	{
		Input input = gc.getInput();

		float mouseX = input.getMouseX();
		float mouseY  = input.getMouseY();

		float accel = 0.05f;
		
		float moveDirX = 0;
		float moveDirY = 0;
		
		if(justClicked)
		{
			this.x = h.x + h.width /2;
			this.y = h.y + h.height/ 2;

			targetX = mouseX;
			targetY = mouseY;
			justClicked = false;
		}

		
		if (targetY < y)
		{
			moveDirY = -1;
		}
		else if(targetY > y)
		{
			moveDirY = 1;
		}
		
		if (targetX < x)
		{
			moveDirX = -1;
		}
		else if(targetX > x)
		{
			moveDirX = 1;
		}
		
		xSpeed += ((moveDirX * maxSpeed) - xSpeed) * accel;
		ySpeed += ((moveDirY * maxSpeed) - ySpeed) * accel;
		
		
		angleToTurn = h.angleToTurn;
		
		image.setCenterOfRotation(image.getWidth()/2 * scale, image.getHeight()/2 * scale);
		setTimer(getTimer() + 1);
		
		if(getTimer() > 50)
		{
			isAlive = false;
		}
	}
	
	@Override
	public void onCollision(Entity other)
	{
	
	}

	public int getTimer() 
	{
		return timer;
	}

	public void setTimer(int timer) 
	{
		this.timer = timer;
	}

}
