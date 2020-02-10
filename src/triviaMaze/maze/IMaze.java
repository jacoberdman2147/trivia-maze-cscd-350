package triviaMaze.maze;

import triviaMaze.room.*;

public interface IMaze
{
	public boolean isTraversable(IRoom cur);
	public IRoom getStart();
	public IRoom getEnd();
}
