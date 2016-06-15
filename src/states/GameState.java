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

public class GameState extends BasicGameState{
	
	private ArrayList<Entity> entities;
	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		entities = new ArrayList<Entity>();
		
		Hero h = new Hero();
		entities.add(h);
		entities.add(new Mob(h));
		//entities.add(new Shield(h));

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
				entities.get(j).x += entities.get(j).xSpeed;
				entities.get(j).y += entities.get(j).ySpeed;

				entities.get(k).x += entities.get(k).xSpeed;
				entities.get(k).y += entities.get(k).ySpeed;

				if(entities.get(j).hitTest(entities.get(k)))
				{
					if(entities.get(j).hitTestX(entities.get(k)))
					{
						entities.get(j).x -= entities.get(j).xSpeed;
						entities.get(k).x -= entities.get(k).xSpeed;
						
						entities.get(j).xSpeed = 0;
						entities.get(k).xSpeed = 0;
					}
					else if(entities.get(j).hitTestY(entities.get(k)))
					{
						entities.get(j).y -= entities.get(j).ySpeed;
						entities.get(k).y -= entities.get(k).ySpeed;
						
						entities.get(j).ySpeed = 0;
						entities.get(k).ySpeed = 0;
					}
										
				}
			}
		}
		
		for (int i = 0; i < amount; i++){

			entities.get(i).update(gc, delta);
		}
	}

	@Override
	public int getID() {
		return States.GAME;
	}

}
