package entities;

import org.newdawn.slick.GameContainer;

import game.Resources;

public class Shield extends Entity{

	private float speed = 0.1f;
	private Hero h;
	
	public Shield(Hero h){
		this.h = h;
	}
	
	@Override
	public void init() {
		image = Resources.getImage("shield");
		x = 600;
		y = 600;
		scale = 2;
		width = 27 * scale;
		height = 9 * scale;
		collideable = true;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		x = h.x + 15;
		y = h.y + 15;
	}
}
