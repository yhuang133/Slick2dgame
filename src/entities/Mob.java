package entities;

import org.newdawn.slick.GameContainer;

import game.Resources;

public class Mob extends Entity{

	private float speed = 0.1f;
	private Hero h;
	
	public Mob(Hero h){
		this.h = h;
	}

	@Override
	public void init() {
		image = Resources.getImage("mob");
		x = 400;
		y = 400;
		scale = 2;
		width = 30 * scale;
		height = 25 * scale;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		float xDist = h.x - x;
		float yDist = h.y - y;

		if(h.x > x)
			x += speed * delta;
		else
			x -= speed * delta;
		
		if(h.y > y)
			y += speed * delta;
		else
			y -= speed * delta;
		
		angleToTurn = (float) Math.toDegrees(Math.atan2(yDist, xDist)) - 90;
		
		image.setCenterOfRotation(image.getWidth()/2 * scale, image.getHeight()/2 * scale);
	}
	

}
