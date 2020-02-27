package triviaMaze.room;

/**
 * An implementation of IRoomLink which holds the state between rooms
 * 
 * @author Jacob Erdman
 *
 */
public class RoomLink implements IRoomLink {
	private boolean enabled;
	private boolean answered;
	private IRoom room;

	/**
	 * Creates a new RoomLink
	 * 
	 * @param room
	 *            The IRoom at the opposite end of the link
	 */
	public RoomLink(IRoom room) {
		enabled = true;
		answered = false;
		this.room = room;
	}

	public RoomLink() {
		enabled = false;
		answered = false;
		this.room = null;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isAnswered() {
		return answered;
	}

	@Override
	public void answer() {
		answered = true;
	}

	@Override
	public void disable() {
		enabled = false;
	}

	@Override
	public IRoom getRoom() {
		return this.room;
	}

	@Override
	public void setRoom(IRoom room) {
		this.room = room;
		if (room != null)
			this.enabled = true;
	}

}
