package triviaMaze.room;

public class LinkedRoom extends IRoom
{
	IRoomLink right;
	IRoomLink left;
	IRoomLink up;
	IRoomLink down;
	
	public LinkedRoom() {
		right = new RoomLink();
		left = new RoomLink();
		up = new RoomLink();
		down = new RoomLink();
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
		switch (direction) {
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

}
