package triviaMaze.room;

public abstract class IRoom
{
	public abstract boolean isEnabled(String direction);
	public abstract IRoom getRoom(String direction);
	public abstract boolean isAnswered(String direction);
	public abstract void setRoom(String direction, IRoom room);
	public abstract void answer(String direction);
	public abstract void disable(String direction);
}
