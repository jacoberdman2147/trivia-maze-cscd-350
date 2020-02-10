package triviaMaze.room;

public abstract class IRoom
{
	public abstract boolean canMoveInDirection(String direction);
	public abstract IRoom roomAtDirection(String direction);
	public abstract boolean isAnsweredAtDirection(String direction);
	public abstract void setRoomAtDirection(String direction, IRoom room);
	
}
