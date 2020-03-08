package triviaMaze.game;

import java.io.*;
import triviaMaze.room.*;
import triviaMaze.userInterface.*;


/**
 * The interface for creating classes which represent the entire state of the
 * current game being played.
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public interface ITriviaMazeGame extends Serializable {
	
	public boolean tryMove(String direction);
	public IRoom getCurrentRoom();
	public void setUi(IUserInterface ui);
	
}
