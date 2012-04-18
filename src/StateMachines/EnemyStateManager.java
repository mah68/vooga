package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Enemies.Enemy;
import States.RegularMotionState;
import States.State;
import States.StationaryState;
import States.WalkingLeftState;
import States.WalkingRightState;

import com.golden.gamedev.object.Sprite;

public class EnemyStateManager extends StateManager {

    public EnemyStateManager(Enemy enemy){
        possibleStates = new ArrayList<State>();
   //     possibleStates.add(new WalkingLeftState(enemy));
   //    possibleStates.add(new WalkingRightState(enemy));
   //     possibleStates.add(new StationaryState(enemy));
   //     possibleStates.add(new GoUpState(enemy));
    }
    
    public void add(State state){
        possibleStates.add(state);
    }





}
