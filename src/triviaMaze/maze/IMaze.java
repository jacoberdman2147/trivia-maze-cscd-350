package triviaMaze.maze;

import triviaMaze.room.*;
import java.io.*;

/**
 * Represents a set of rooms with a structure that the player can traverse in
 * order to win or lose the game
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public interface IMaze extends Serializable {
	/**
	 * Determines whether or not the end of the maze can be reached
	 * 
	 * @param cur
	 *            The room in which traversal should be tested from
	 * @return Returns a boolean representing whether or not the maze is traversable
	 *         from cur to end
	 */
	public boolean isTraversable(IRoom cur);

	/**
	 * Gets the start of the maze
	 * 
	 * @return Returns the IRoom at the start of the maze
	 */
	public IRoom getStart();

	/**
	 * Gets the end of the maze
	 * 
	 * @return Returns the IRoom at the end of the maze
	 */
	public IRoom getEnd();
}
