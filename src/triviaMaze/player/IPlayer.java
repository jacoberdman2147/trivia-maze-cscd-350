package triviaMaze.player;

import triviaMaze.room.*;
import java.io.*;

/**
 * The interface to represent the player and their state
 * 
 * @author Jacob Erdman
 *
 */
public interface IPlayer extends Serializable, triviaMaze.Cleanable{
	/**
	 * Gets the IRoom that the player is in
	 * 
	 * @return Returns the IRoom that the player is currently in
	 */
	public IRoom getRoom();

	/**
	 * Moves the player
	 * 
	 * @param to
	 *            The IRoom in which the player should be moved into. Note that this
	 *            method does not necessarily check whether or not the player is
	 *            allowed to move to that room from their current room
	 */
	public void move(IRoom to);
}
