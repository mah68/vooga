package demogame;

import input.InputManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import sprites.Flag;
import sprites.GeneralSprite;
import collisionType.AbstractHitboxNonhitboxCollision;
import collisions.Hitbox;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.EventManager;
import core.conditions.EventTriggeredCondition;
import cutscenes.Cutscene;
import cutscenes.CutsceneAutomation;
import cutscenes.EventAutomation;
import demogame.sprites.MainCharacter;

public class DemoGame extends Game {
	private String levelFileName;
	private PlayField myPlayField;
	private GeneralSprite mainChar;
	private Cutscene levelOver;
	private static final double gravity = .002;

	public DemoGame (String levelFileName) {
		this.levelFileName = levelFileName;
	}
	
    protected void initEngine() {
		super.initEngine();
		this.bsInput = new InputManager(this.bsGraphics.getComponent());
	}
	
	public void initResources() {
		setMaskColor(Color.WHITE);
		myPlayField = new PlayField();
//		myPlayField = new LevelBuilder(myPlayField, levelFileName).createLevel();
		Background b = new ColorBackground(Color.LIGHT_GRAY, 2000, 480);
		myPlayField.setBackground(b);
		
		SpriteGroup platforms = makePlatforms();
		myPlayField.addGroup(platforms);
		GeneralSprite flag = new Flag(getImage("images/finalflag.png"), 1750, 106);
		SpriteGroup fg = new SpriteGroup("flag");
		fg.add(flag);
		myPlayField.addGroup(fg);
		
		GeneralSprite castle = new GeneralSprite(getImage("images/castle.gif"), 1800, 300);
		myPlayField.add(castle);
		
		mainChar = new MainCharacter();
		mainChar.setImages(getImages("images/mariocharpng.png",3,2));
		mainChar.setLocation(200, 100);
		mainChar.setAnimate(false);
		SpriteGroup chargroup = new SpriteGroup("Character");
		chargroup.add(mainChar);
		myPlayField.addGroup(chargroup);
		
		
		
		
		
		myPlayField.addCollisionGroup(chargroup, platforms, new PlatformCollision());
		myPlayField.addCollisionGroup(chargroup,fg,new FlagCollision());
		
		//make the end-of-level cutscene
		EventAutomation aOne = new CutsceneAutomation();
		aOne.addTimedAutomation(5, 10000, "slide-down-pole");
		EventAutomation aTwo = new CutsceneAutomation("src/demogame/jump_off.script");
		aOne.addTransition(new EventTriggeredCondition("floor collide"), aTwo);
		levelOver = new Cutscene(aOne, "flag-hit", "end-level");
	}
	
	private SpriteGroup makePlatforms() {
		SpriteGroup s = new SpriteGroup("Platforms");
		BufferedImage image = getImage("images/bricks1.png");
		for(int i=0; i < 1980; i+=32) {
			s.add(new GeneralSprite(image,i,400));
		}
		return s;
	}

	public void render(Graphics2D g) {
		myPlayField.render(g);
	}

	public void update(long timeElapsed) {
		EventManager.getEventManager().update(timeElapsed);
		myPlayField.getBackground().setToCenter(mainChar);
		myPlayField.update(timeElapsed);
		levelOver.update(timeElapsed);
		
	}

	class PlatformCollision extends BasicCollisionGroup {

        public PlatformCollision() {
            pixelPerfectCollision = true;
        }

        public void collided(Sprite s1, Sprite s2) {
            EventManager.getEventManager().sendEvent("floor collide");
        }
	}
	
	class FlagCollision extends AbstractHitboxNonhitboxCollision {
		boolean hitYet;
		
		public FlagCollision () {
			hitYet = false;
			pixelPerfectCollision = true;
		}

		@Override
		protected void spriteCollided(Sprite s1, Sprite s2) {
			// Temporary!
//			if(!hitYet) {
//				EventManager.getEventManager().sendEvent("flag-hit");	
//				hitYet = true;
//			}			
		}

		@Override
		protected void hitboxSpriteCollided(Sprite s1, Hitbox h1, Sprite s2) {
			if(!hitYet) {
				EventManager.getEventManager().sendEvent("flag-hit");	
				hitYet = true;
			}			
		}
		
	}
}