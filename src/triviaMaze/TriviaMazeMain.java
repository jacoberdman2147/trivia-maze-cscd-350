package triviaMaze;

import java.io.*;
import triviaMaze.room.*;
import triviaMaze.eventService.*;
import triviaMaze.maze.*;
import triviaMaze.player.*;

public class TriviaMazeMain
{

	public static void main(String[] args) throws IOException
	{
		IMaze testMaze = new LinkedRectangularMaze(5,5);
		IRoom start = testMaze.getStart();
		
		IPlayer player = new BasicPlayer(testMaze.getStart(), testMaze.getEnd());
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		   while (line.equalsIgnoreCase("exit") == false) {
		       line = in.readLine();
		       if (line.length() == 1) TmEventService.fireEvent(line);
		   }

		   in.close();
	}

}
