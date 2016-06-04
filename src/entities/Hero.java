package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import game.Resources;

public class Hero extends Entity {

	private float speed = 0.3f;
	@Override
	public void init() {
		x = 100;
		y = 100;
		scale = 3;
		width = 30 * scale;
		height = 25 * scale;
		image = Resources.getImage("knight");
		
	}

	@Override
	public void update(GameContainer gc, int delta) {

		Input input = gc.getInput();
		float mouseX = input.getMouseX();
		float mouseY  = input.getMouseY();
		
		if (input.isKeyDown(Input.KEY_W)){
			y -= speed * delta;
		}else if(input.isKeyDown(Input.KEY_S)){
			y += speed * delta;
		}
		if(input.isKeyDown(Input.KEY_A)){
			x -= speed * delta;
		}else if(input.isKeyDown(Input.KEY_D)){
			x += speed * delta;
		}
		
		float xDist = mouseX - x;
		float yDist = mouseY - y;
		
		angleToTurn = (float) Math.toDegrees(Math.atan2(yDist, xDist)) + 90;
		
		image.setCenterOfRotation(image.getWidth()/2 * scale, image.getHeight()/2 * scale);
		
	}
	
}
