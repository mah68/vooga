package demogame.sprites;

import hudDisplay.Stat;

import java.util.HashMap;

import States.InAirState;
import States.JetPackPowerup;
import States.OnLandState;
import States.State;
import States.TeleJumpPowerup;
import sprites.StateSprite;
import stateTransitions.AddStateTransition;
import stateTransitions.ReplaceStateTransition;
import stateTransitions.StateTransition;

public class MainCharacter extends StateSprite {
	public MainCharacter()
	{
		super();
		State s1 = new InAirState(this);
		setGravity(0.002);
		getStateManager().addState(s1);
		StateTransition land = new ReplaceStateTransition(getStateManager(), "landed",  new OnLandState(this), s1);
		StateTransition jump = new ReplaceStateTransition(getStateManager(), "jumped", s1, new OnLandState(this));
		StateTransition powerup = new AddStateTransition(getStateManager(), "pwrup", new JetPackPowerup(this));
		setMyStats(new HashMap<String, Stat>());
		land.activate();
		jump.activate();
		powerup.activate();
	}
	
	

	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		this.addVerticalSpeed(elapsedTime, getGravity(), 0.5);
	}
}