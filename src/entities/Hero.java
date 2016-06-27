package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import game.Resources;

public class Hero extends Entity
{
	private final float maxSpeed = 7;
	
	@Override
	public void init() 
	{
		x = 100;
		y = 100;
		scale = 2;
		
		width = 30 * scale;
		height = 31 * scale;
		image = Resources.getImage("wizard");
	}

	@Override
	public void update(GameContainer gc, int delta)
	{

		Input input = gc.getInput();
		float mouseX = input.getMouseX();
		float mouseY  = input.getMouseY();
		
		float xDist = mouseX - (x + width/2 + xSpeed);
		float yDist = mouseY - (y + height/2  + ySpeed);
		
		float accel = 0.3f;
		
		float moveDirX = 0;
		float moveDirY = 0;
		
		if (input.isKeyDown(Input.KEY_W))
		{
			moveDirY = -1;
		}
		else if(input.isKeyDown(Input.KEY_S))
		{
			moveDirY = 1;
		}
		
		if(input.isKeyDown(Input.KEY_A))
		{
			moveDirX = -1;
		}
		else if(input.isKeyDown(Input.KEY_D))
		{
			moveDirX = 1;
		}
		
		xSpeed += ((moveDirX * maxSpeed) - xSpeed) * accel;
		ySpeed += ((moveDirY * maxSpeed) - ySpeed) * accel;
		
		angleToTurn = (float) Math.toDegrees(Math.atan2(yDist, xDist)) + 90;
		
		image.setCenterOfRotation(image.getWidth()/2 * scale, image.getHeight()/2 * scale);
	}
	
	@Override
	public void onCollision(Entity other)
	{
		if(this.hitTest(other) && (other instanceof Mob))
		{
			x -= xSpeed;
			y -= ySpeed;
			other.x -= other.xSpeed;
			other.y -= other.ySpeed;
			
			x += xSpeed;
			if(this.hitTest(other))
			{
				x -= xSpeed;
				xSpeed = 0;
			}

			y += ySpeed;
			if(this.hitTest(other))
			{
				y -= ySpeed;
				ySpeed = 0;
			}
		}
	}
	
}
