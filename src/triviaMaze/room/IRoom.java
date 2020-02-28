package triviaMaze.room;

import java.io.*;

/**
 * The interface corresponding to a room within the maze
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public abstract class IRoom implements Serializable {

	/**
	 * Gets whether or not the door in the specified direction is enabled
	 * 
	 * @param direction
	 *            The direction to check, left, right, up, or down
	 * @return Returns a boolean representing whether or not the room to that
	 *         direction is enabled
	 */
	public abstract boolean isEnabled(String direction);

	/**
	 * Gets whether or not the door in the specified direction is answered
	 * 
	 * @param direction
	 *            The direction to check, left, right, up, or down
	 * @return Returns a boolean representing whether or not the room to that
	 *         direction is answered
	 */
	public abstract boolean isAnswered(String direction);

	/**
	 * Gets the room in the specified direction
	 * 
	 * @param direction
	 *            The direction to get the room of, left, right, up, or down
	 * @return Returns a IRoom corresponding to the room in that direction
	 */
	public abstract IRoom getRoom(String direction);

	/**
	 * Sets the room in a particular direction
	 * 
	 * @param direction
	 *            The direction to set the room of, left, right, up, or down
	 * @param room
	 *            The room to set that direction to
	 */
	public abstract void setRoom(String direction, IRoom room);

	/**
	 * Sets a link as answered in the direction specified
	 * 
	 * @param direction
	 *            The direction to answer the room of, left, right, up, or down
	 */
	public abstract void answer(String direction);

	/**
	 * Sets a link as disabled in the direction specified
	 * 
	 * @param direction
	 *            The direction to disable the room of, left, right, up, or down
	 */
	public abstract void disable(String direction);
}
