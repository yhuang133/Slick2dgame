package entities;

import org.newdawn.slick.GameContainer;

import game.Resources;

public class Mob extends Entity{
	
	private final float maxSpeed = 7;

	private Hero h;
	
	public Mob(Hero h){
		this.h = h;
	}

	@Override
	public void init() {
		image = Resources.getImage("mob");
		x = 400;
		y = 400;
		scale = 3;
		width = 30 * scale;
		height = 25 * scale;
		collideable = true;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		float xDist = h.x - (x + xSpeed);
		float yDist = h.y - (y + ySpeed);
		
		float accel = 0.05f;
		
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
		
		//xSpeed += ((moveDirX * maxSpeed) - xSpeed) * accel;
		//ySpeed += ((moveDirY * maxSpeed) - ySpeed) * accel;
		
		
		angleToTurn = (float) Math.toDegrees(Math.atan2(yDist, xDist)) + 90;
		
		image.setCenterOfRotation(image.getWidth()/2 * scale, image.getHeight()/2 * scale);
	}
	

}
