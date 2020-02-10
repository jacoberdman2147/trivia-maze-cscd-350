package triviaMaze.player;

import triviaMaze.maze.*;
import triviaMaze.room.*;

public interface IPlayer
{
	public IRoom getRoom();
	public void move(IRoom to);
}
