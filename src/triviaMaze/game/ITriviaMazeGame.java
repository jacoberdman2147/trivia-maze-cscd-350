package triviaMaze.game;

import java.io.*;

/**
 * The interface for creating classes which represent the entire state of the
 * current game being played.
 * 
 * @author Jacob Erdman
 *
 */
public interface ITriviaMazeGame extends Serializable, triviaMaze.Cleanable {

	/**
	 * Begins the game and passes control over to this object
	 */
	public void start();
}
