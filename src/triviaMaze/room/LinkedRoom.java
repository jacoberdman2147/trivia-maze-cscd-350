package triviaMaze.room;

import java.util.UUID;

public class LinkedRoom extends IRoom
{
	private IRoomLink right;
	private IRoomLink left;
	private IRoomLink up;
	private IRoomLink down;
	private UUID uuid;
	
	public LinkedRoom() {
		right = new RoomLink();
		left = new RoomLink();
		up = new RoomLink();
		down = new RoomLink();
		uuid = UUID.randomUUID();
	}
	
	@Override
	public boolean canMoveInDirection(String direction)
	{
		IRoomLink link = getLinkInDirection(direction);
		return link.isEnabled();
	}

	@Override
	public IRoom roomAtDirection(String direction)
	{
		IRoomLink link = getLinkInDirection(direction);
		return link.getRoom();
	}

	@Override
	public boolean isAnsweredAtDirection(String direction)
	{
		IRoomLink link = getLinkInDirection(direction);
		return link.isAnswered();
	}

	@Override
	public void setRoomAtDirection(String direction, IRoom room)
	{
		IRoomLink link = getLinkInDirection(direction);
		link.setRoom(room);
	}
	
	private IRoomLink getLinkInDirection(String direction) {
		switch (direction.toLowerCase()) {
		case "right":
			return right;
		case "left":
			return left;
		case "up":
			return up;
		case "down":
			return down;
		default:
			throw new IllegalArgumentException("Invalid direction passed into a directional function of LinkedRoom");
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof LinkedRoom)) {
			return false;
		}
		LinkedRoom castedObj = (LinkedRoom)obj;
		return (this.uuid.equals(castedObj.uuid));
	}
	
	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

}
