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
import entities.Projectile;
import entities.Shield;
import game.Resources;
import game.Window;

public class GameState extends BasicGameState{
	
	private ArrayList<Entity> entities;
	
	private static int offsetMaxX = Resources.getMap("bluegrass").getWidth() - Window.WIDTH;
	private static int offsetMaxY = Resources.getMap("bluegrass").getHeight() - Window.HEIGHT;
	private int offsetMinX = 0;
	private int offsetMinY = 0;
	public float camX;
	public float camY;
	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException
	{
		entities = new ArrayList<Entity>();
		
		Hero h = new Hero();
		entities.add(h);
		entities.add(new Mob(h));
		
		//entities.add(new Shield(h));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException
	{
		Resources.getMap("bluegrass").render(0,0);
		g.drawString("Game state", 50, 50);
		
		//Resources.getSpriteImage("tiles", 0, 0).draw();
		
		int amount = entities.size();
		for (int i = 0; i < amount; i++){
			entities.get(i).render(gc,  g);
		}
		
		
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException
	{
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_ENTER)) s.enterState(States.MENU);
		
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			entities.add(new Projectile((Hero)entities.get(0)));
		}
		
		
		collisionMover(entities.size());
		
		killer();
		
		
		// 
		camX = entities.get(0).x - gc.getWidth() / 2;
		camY = entities.get(0).y - gc.getHeight() / 2;

		
		if (camX > offsetMaxX)
		    camX = offsetMaxX;
		else if (camX < offsetMinX)
		    camX = offsetMinX;
		
		if (camY > offsetMaxY)
		    camY = offsetMaxY;
		else if (camY < offsetMinY)
		    camY = offsetMinY;
		//
		for (int i = 0; i < entities.size(); i++)
		{
			entities.get(i).update(gc, delta);
		}
		gc.getGraphics().translate(-camX,  -camY);
	
	}

	@Override
	public int getID()
	{
		return States.GAME;
	}
	
	public void collisionMover(int amount)
	{
		for(int j = 0; j < amount; j++)
		{
			entities.get(j).x += entities.get(j).xSpeed;
			entities.get(j).y += entities.get(j).ySpeed;
			
			for(int k = j + 1; k < amount; k++)
			{
				entities.get(k).x += entities.get(k).xSpeed;
				entities.get(k).y += entities.get(k).ySpeed;
				
				if(entities.get(j).hitTest(entities.get(k)))
				{
					entities.get(j).onCollision(entities.get(k));
					entities.get(k).onCollision(entities.get(j));
				}
				
			}
		}
	}
	
	public void killer()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			
			if(!e.isAlive)
			{
				int lastEntity = entities.size() - 1;
				entities.set(i, entities.get(lastEntity));
				entities.remove(lastEntity);
			}
		}
	}

	public int getOffsetMaxY() {
		return offsetMaxY;
	}

	public void setOffsetMaxY(int offsetMaxY) {
		this.offsetMaxY = offsetMaxY;
	}

	public int getOffsetMaxX() {
		return offsetMaxX;
	}

	public void setOffsetMaxX(int offsetMaxX) {
		this.offsetMaxX = offsetMaxX;
	}

}
