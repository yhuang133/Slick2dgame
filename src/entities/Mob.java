package entities;

import org.newdawn.slick.GameContainer;

import game.Resources;

public class Mob extends Entity{

	private float speed = 0.3f;
	private Hero h;
	
	public Mob(Hero h){
		this.h = h;
	}

	@Override
	public void init() {
		image = Resources.getImage("mob");
		x = 200;
		y = 200;
		scale = 3;
		width = 30 * scale;
		height = 25 * scale;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		x = h.getCenterX();
		
	}

}
