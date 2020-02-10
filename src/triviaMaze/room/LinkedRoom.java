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
	public boolean isEnabled(String direction)
	{
		IRoomLink link = getLink(direction);
		return link.isEnabled();
	}

	@Override
	public IRoom getRoom(String direction)
	{
		IRoomLink link = getLink(direction);
		return link.getRoom();
	}

	@Override
	public boolean isAnswered(String direction)
	{
		IRoomLink link = getLink(direction);
		return link.isAnswered();
	}

	@Override
	public void setRoom(String direction, IRoom room)
	{
		IRoomLink link = getLink(direction);
		link.setRoom(room);
	}
	
	private IRoomLink getLink(String direction) {
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
	
	private String invertDirection(String direction) {
		switch (direction.toLowerCase()) {
		case "right":
			return "left";
		case "left":
			return "right";
		case "up":
			return "down";
		case "down":
			return "up";
		default:
			throw new IllegalArgumentException("Invalid direction passed into a directional function of LinkedRoom");
		}
	}
	
	@Override
	public void answer(String direction)
	{
		IRoomLink link = getLink(direction);
		link.answer();
		String invertedDirection = invertDirection(direction);
		if (link.getRoom() != null) {
			if (link.getRoom().isAnswered(invertedDirection)) link.getRoom().answer(invertedDirection);
		}
	}

	@Override
	public void disable(String direction)
	{
		IRoomLink link = getLink(direction);
		link.disable();
		String invertedDirection = invertDirection(direction);
		if (link.getRoom() != null) {
			if (link.getRoom().isEnabled(invertedDirection)) link.getRoom().disable(invertedDirection);
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
