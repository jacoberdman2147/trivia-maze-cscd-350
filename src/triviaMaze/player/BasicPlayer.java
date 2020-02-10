package triviaMaze.player;

import triviaMaze.room.IRoom;

public class BasicPlayer implements IPlayer
{
	private IRoom cur;

	@Override
	public IRoom getRoom()
	{
		return cur;
	}

	@Override
	public void move(IRoom to)
	{
		cur = to;
	}

}
