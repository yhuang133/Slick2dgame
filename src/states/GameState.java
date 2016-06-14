package states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Entity;
import entities.Hero;
import entities.Mob;
import game.Resources;

public class GameState extends BasicGameState{
	
	private ArrayList<Entity> entities;
	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		entities = new ArrayList<Entity>();
		
		Hero h = new Hero();
		entities.add(h);
		entities.add(new Mob(h));

	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		g.drawString("Game state", 50, 50);
		
		//Resources.getSpriteImage("tiles", 0, 0).draw();
		
		int amount = entities.size();
		for (int i = 0; i < amount; i++){
			entities.get(i).render(gc,  g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) s.enterState(States.MENU);
		
		int amount = entities.size();
		
		for(int j = 0; j < amount; j++)
		{
			for(int k = j + 1; k < amount; k++)
			{
				if(j != k)
				{
					if(entities.get(j).hitTest(entities.get(k)))
					{
						entities.get(k).colliding = true;
						entities.get(j).colliding = true;
					}
				}
			}
		}
		
		for (int i = 0; i < amount; i++){
			if(entities.get(i).colliding) //if there is a collision move to oldxy, reset collision
			{
				entities.get(i).x = entities.get(i).oldX;
				entities.get(i).y = entities.get(i).oldY;
				entities.get(i).colliding = false;
			}
			else //no collision then get old y for in case there will be collision
			{
				entities.get(i).oldX = entities.get(i).x;
				entities.get(i).oldY = entities.get(i).y;
			}
			entities.get(i).update(gc, delta);
		}
	}

	@Override
	public int getID() {
		return States.GAME;
	}

}
