package interactiveSprites;

interface InteractiveSprite {
	
	/**
	 * performs primary function of the interactive sprite type
	 */
	public void primaryAction();
	
	
	
	/**
	 * sets position of the interactive sprite to follow user sprite. i.e. lets user move sprite
	 */
	public void userMove();
	
	
	
	/**
	 * returns type of interactive sprite
	 */
	public String getType();
	
	

}
