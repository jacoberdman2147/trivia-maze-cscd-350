package triviaMaze;

import triviaMaze.room.*;
import triviaMaze.maze.*;

public class TriviaMazeMain
{

	public static void main(String[] args)
	{
		IMaze testMaze = new LinkedRectangularMaze(5,5);
		IRoom start = testMaze.getStart();
		testMaze.getEnd().disable("up");
		testMaze.getEnd().disable("left");
		System.out.println(testMaze.isTraversable(start));
	}

}
