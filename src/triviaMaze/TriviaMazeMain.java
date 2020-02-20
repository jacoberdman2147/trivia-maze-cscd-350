package triviaMaze;

import java.io.*;
import triviaMaze.room.*;
import triviaMaze.maze.*;
import triviaMaze.keyService.*;
import triviaMaze.player.*;

public class TriviaMazeMain
{

	public static void main(String[] args) throws IOException
	{
		IMaze testMaze = new LinkedRectangularMaze(5,5);
		IRoom start = testMaze.getStart();
		testMaze.getEnd().disable("up");
		testMaze.getEnd().disable("left");
		System.out.println(testMaze.isTraversable(start));
		
		IPlayer player = new BasicPlayer(testMaze.getStart(), testMaze.getEnd());
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		   while (line.equalsIgnoreCase("exit") == false) {
		       line = in.readLine();
		       if (line.length() == 1) TmKeyService.keyPressed(line);
		   }

		   in.close();
	}

}
