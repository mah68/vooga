package SpriteAction;

import sprites.GeneralSprite;
import sprites.InteractiveSprite;

public class ThrowAction extends SpriteAction{

    public ThrowAction(GeneralSprite s) {
        super(s);

    }
    
    public void actionPerformed(String event){
        ((InteractiveSprite) mySprite).throwAction();
    }

	

}
