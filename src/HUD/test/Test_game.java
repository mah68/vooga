package HUD.test;

import hudDisplay.BarItem;
import hudDisplay.HeadsUpDisplay;
import hudDisplay.TextItem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Map;

import sprites.BryanSprite;
import sprites.Chris_TestSprite;
import sprites.GeneralSprite;
import States.State;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.EventManager;

public class Test_game extends Game {
	BryanSprite s1;
	Map<String, State> stateMap;
	PlayField playfield;
	CollisionManager collisionTypeWall;
	
	HeadsUpDisplay HUD;
	GameFont scoreFont;
	
	


	
	public void initResources() {
		playfield = new PlayField();
		playfield.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));
		
		HUD = new HeadsUpDisplay(0,0);
		
		s1 = new BryanSprite();
		s1.setImage(getImage("images/mario1.png"));
		s1.setLocation(300, 200);
		
		s1.createScore("health", 300);
		s1.createScore("score", 0);
		
		SpriteGroup character = new SpriteGroup("character");
		character.add(s1);
		
		BarItem healthbar = new BarItem(getImage("images/healthBar.png", false), 500, 0, "health", s1);
		HUD.addGraphicItem(healthbar);

		scoreFont = fontManager.getFont(getImages("images/Score_Font.png", 8,12));
		TextItem Score = new TextItem(scoreFont, 300, 0, "score", s1);
		HUD.addTextItem(Score);

		
		


		Sprite wall1 = new Sprite(getImage("images/block.png"));
		wall1.setLocation(350,400);
		Sprite wall2 = new Sprite(getImage("images/block.png"));
		wall2.setLocation(300,400);
		Sprite wall3 = new Sprite(getImage("images/block.png"));
		wall3.setLocation(200,400);
		Sprite wall4 = new Sprite(getImage("images/block.png"));
		wall4.setLocation(250,400);


		SpriteGroup walls = new SpriteGroup("walls");
		walls.add(wall1);
		walls.add(wall2);
		walls.add(wall3);
		walls.add(wall4);
		
		
		collisionTypeWall = new WallCollision();
		collisionTypeWall.setCollisionGroup(character, walls);
		
		playfield.addGroup(character);
		playfield.addGroup(walls);
		


		
		
		
	}
	
	public void render(Graphics2D arg0) {
	playfield.render(arg0);
	collisionTypeWall.checkCollision();
	HUD.render(arg0);
	}
	
	public void update(long elapsedTime) {
		
		//Cutscene Code
//		if(cutTimer.action(elapsedTime)) {
//			trigger.triggerCutscene();
//			cutTimer.setActive(false);
//		}
//		cutscene.update(elapsedTime);
		
		EventManager.getEventManager().update(elapsedTime);
		playfield.update(elapsedTime);
		HUD.update(elapsedTime);
		

		
		if (keyDown(KeyEvent.VK_LEFT))
		{
			EventManager.getEventManager().sendEvent("left");
		}
		if (keyDown(KeyEvent.VK_RIGHT))
		{
			EventManager.getEventManager().sendEvent("right");
		}
		if (keyDown(KeyEvent.VK_UP))
		{
			EventManager.getEventManager().sendEvent("up");	
		}
		if (keyDown(KeyEvent.VK_DOWN))
		{
			EventManager.getEventManager().sendEvent("down");	
		}
	}
	
	class WallCollision extends BasicCollisionGroup {

	    public WallCollision() {
	    	pixelPerfectCollision = true;
	    }

	    public void collided(Sprite s1, Sprite s2) {
	    	EventManager.getEventManager().sendEvent("floor collide");
	    	EventManager.getEventManager().sendEvent("got hit");
//	    	EventManager.getEventManager().sendEvent("switchstates");
	    	
	    }

	}
}
