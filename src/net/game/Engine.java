package net.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import states.GameState;
import states.MenuState;

public class Engine extends StateBasedGame{
	private TiledMap waterMap;
	private int fpsUpdateRate;
	
	public Engine() {
		super("Game");
		// TODO Auto-generated constructor stub
	}

	public static void main (String[] args){
		AppGameContainer app;
		try {
			app = new AppGameContainer (new Engine());
			app.setDisplayMode(Window.HEIGHT, Window.WIDTH, false);
			app.start();
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(fpsUpdateRate);
		gc.setTargetFrameRate(fpsUpdateRate);
		gc.setAlwaysRender(true);
		gc.setShowFPS(true);
		gc.setVSync(true);
		
		new Resources();
		
		this.addState(new GameState());
		this.addState(new MenuState());
		     
		waterMap = new TiledMap("assets/watermap.tmx");
		
	}
}