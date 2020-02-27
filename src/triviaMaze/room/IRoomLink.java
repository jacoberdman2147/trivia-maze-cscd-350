package triviaMaze.room;

/**
 * The interface which represents links between rooms which can contain the
 * state that they have.
 * 
 * @author Jacob Erdman
 *
 */
public interface IRoomLink {

	/**
	 * Whether or not the link is enabled
	 * 
	 * @return Returns whether or not the link is enabled
	 */
	public boolean isEnabled();

	/**
	 * Whether or not the link is answered
	 * 
	 * @return Returns a Boolean representing whether or not the link is enabled
	 */
	public boolean isAnswered();

	/**
	 * Sets the link to answered
	 */
	public void answer();

	/**
	 * Sets the link as disabled
	 */
	public void disable();

	/**
	 * Gets the room at the other end of the link
	 * 
	 * @return
	 */
	public IRoom getRoom();

	/**
	 * Sets the room at the other end of the link
	 * 
	 * @param room
	 */
	public void setRoom(IRoom room);

}
