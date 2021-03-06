package game;


import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class Resources {
	
	private static Map<String, Image> images;
	private static Map<String, SpriteSheet> sprites;
	private static Map<String, Sound> sounds;
	private static Map<String, TiledMap> tiledMap;
	
	public Resources(){
		tiledMap = new HashMap<String, TiledMap>();
		images = new HashMap<String, Image>();
		sprites = new HashMap<String, SpriteSheet>();
		sounds = new HashMap<String, Sound>();
		
		try {
			images.put("knight", loadImage("assets/knight.png"));
			images.put("wizard", loadImage("assets/wizardSprite.png"));
			images.put("fireball", loadImage("assets/fireball.png"));
			images.put("mob", loadImage("assets/mob1.png"));
			images.put("shield", loadImage("assets/shield.png"));
			
			tiledMap.put("bluegrass", loadMap("assets/bluegrass/bluegrass.tmx"));
			//sprites.put("tiles", loadSprite("assets/spriteSheetA.png", 32, 32));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Image loadImage(String path) throws SlickException {
		return new Image(path, false, Image.FILTER_NEAREST);
	}
	
	private SpriteSheet loadSprite(String path, int tilew, int tileh) throws SlickException{
		return new SpriteSheet(loadImage(path),tilew,tileh);
	}
	
	public static Image getSpriteImage(String getter, int x, int y){
		return sprites.get(getter).getSubImage(x, y);
	}
	
	public static Image getSprite(String getter, int x, int y){
		return sprites.get(getter);
	}
	
	public static Image getImage(String getter){
		return images.get(getter); 
	}
	
	private TiledMap loadMap (String path) throws SlickException {
		return new TiledMap(path);
	}
	
	public static TiledMap getMap (String getter){
		return tiledMap.get(getter);
	}
}
