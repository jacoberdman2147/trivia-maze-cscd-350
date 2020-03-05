package triviaMaze.player;

import java.io.IOException;
import java.util.*;
import triviaMaze.eventService.*;
import triviaMaze.room.*;

/**
 * A simple implementation of IPlayer
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public class BasicPlayer implements IPlayer {
	private IRoom cur;

	/**
	 * Creates a BasicPlayer
	 * 
	 * @param start
	 *            The IRoom corresponding to the BasicPlayer's starting location
	 */
	public BasicPlayer(IRoom start) {
		cur = start;
	}

	@Override
	public IRoom getRoom() {
		return cur;
	}

	@Override
	public void move(IRoom to) {
		cur = to;
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
	}
}
