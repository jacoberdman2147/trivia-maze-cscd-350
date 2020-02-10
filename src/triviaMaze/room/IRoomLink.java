package triviaMaze.room;

public interface IRoomLink
{
	public boolean isEnabled();
	public boolean isAnswered();
	public void answer();
	public void disable();
	public IRoom getRoom();
	public void setRoom(IRoom room);
}
