package sprites;

import game.Platformer;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import stateManagers.StateManager;

import collisions.Hitbox;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public abstract class GeneralSprite extends AdvanceSprite implements Boxable, LevelEditable {


	
	private StateManager myStateManager;
	private List<Hitbox> myHitboxes;
	private double myGravityValue; 
	private Platformer mygame;
	private String path;


	
	
	public GeneralSprite() {
		super();
	}
	
	public GeneralSprite(BufferedImage i) {
		super();
		BufferedImage[] image = new BufferedImage[1];
		image[0] = i;
		setImages(image);
	}
	
	public GeneralSprite(BufferedImage[] i) {
		super(i);
	}
	
	public GeneralSprite(BufferedImage i, double x, double y) {
		super(x,y);
		setImage(i);
	}
	
	public GeneralSprite(BufferedImage[] i, double x, double y) {
		super(i,x,y);
	}
	
	public GeneralSprite(double x, double y) {
		super(x,y);
	}

	public List<Hitbox> getHitboxes() {
		return myHitboxes;
	}

	public String getDefaultEvent() {
		return "";
	}
	public void setGravity(double d)
	{
		myGravityValue = d;
	}
	public double getGravity()
	{
		return myGravityValue;
	}
	
	public void setImage(BufferedImage i) {
		BufferedImage[] image = new BufferedImage[1];
		image[0] = i;
		setImages(image);
	}


	public void setInitPath(String path) {
		this.path=path;
		
	}
	public StateManager getStateManager()
	{
		return myStateManager;
	}
	public void setStateManager(StateManager sm)
	{
		myStateManager = sm;
	}
	public String getPath()
	{
		return path;
	}
	public Platformer getMygame() {
		return mygame;
	}

	public void setMygame(Platformer mygame) {
		this.mygame = mygame;
	}
	
	

}
