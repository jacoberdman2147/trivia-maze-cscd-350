package triviaMaze;

import triviaMaze.room.*;
import triviaMaze.maze.*;

public class TriviaMazeMain
{

	public static void main(String[] args)
	{
		IMaze testMaze = new LinkedRectangularMaze(10,10);
		IRoom start = testMaze.getStart();
		System.out.println(testMaze.isTraversable(start));
	}

}
