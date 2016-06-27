package entities;

import org.newdawn.slick.GameContainer;

import game.Resources;

public class Mob extends Entity
{
	
	private final float maxSpeed = 5;

	private Hero h;
	
	public Mob(Hero h)
	{
		this.h = h;
	}

	@Override
	public void init()
	{
		image = Resources.getImage("mob");
		x = 400;
		y = 400;
		scale = 2;
		width = 30 * scale;
		height = 25 * scale;
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		float xDist = (h.x + h.width) - (x + width + xSpeed);
		float yDist = (h.y + h.height) - (y + height + ySpeed);
		
		float accel = 0.01f;
		
		float moveDirX = 0;
		float moveDirY = 0;
		
		if (h.y < y){
			moveDirY = -1;
		}
		else if(h.y > y){
			moveDirY = 1;
		}
		
		if (h.x < x){
			moveDirX = -1;
		}
		else if(h.x > x){
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
		if(this.hitTest(other))
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
