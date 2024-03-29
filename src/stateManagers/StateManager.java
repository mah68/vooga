package stateManagers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import sprites.GeneralSprite;
import stateTransitions.StateTransition;

import States.State;

import com.golden.gamedev.object.Sprite;

import core.EventListener;




public class StateManager{
	private GeneralSprite mySprite;

	private List<State> currentStates;
	
	public StateManager(GeneralSprite s, State startingState)
	{
		currentStates = new ArrayList<State>();
		mySprite = s;
		currentStates.add(startingState);
		currentStates.get(0).activateAllListeners();
	}
	public StateManager(GeneralSprite s)
	{
		currentStates = new ArrayList<State>();
		mySprite = s;
	}
	

	public void addState(State s)
	{
		currentStates.add(s);
		s.activateAllListeners();
	}
	public void removeState(State s)
	{
		for(int i = 0; i< currentStates.size(); i++)
		{
			State cur = currentStates.get(i);
			if(cur.equals(s))
			{
				cur.deactivateAllListeners();
				currentStates.remove(i);
				return;
			}
		}
		
	}
	public void changeState(State s)
	{
		if(!isCurrentlyActive(s)){
			for(State cur : currentStates)
			{	
				
				cur.deactivateAllListeners();
			}
			currentStates.clear();
			s.activateAllListeners();
			currentStates.add(s);
			mySprite.setGravity(s.getMyGravityValue());
		}
	}
	public void replaceState(State stateToAdd, State stateToRemove)
	{	
			removeState(stateToRemove);
			addState(stateToAdd);
//			System.out.println(currentStates.get(0));
		
		
	}
	
	public boolean isCurrentlyActive(State s)
	{
		for(State cur : currentStates)
		{
			if(cur.equals(s))
			{
				return true;
			}
		}
		return false;
	}

	
	

}
