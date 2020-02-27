package triviaMaze;

import java.io.*;
import triviaMaze.game.ITriviaMazeGame;
import triviaMaze.game.*;
import triviaMaze.inputService.*;

public class TriviaMazeMain {

	public static void main(String[] args) throws IOException {
		ITriviaMazeGame game = new RectangularTriviaMazeGame(5, 5, new ConsoleInputService());
		game.start();
		System.out.println("Looks like we made it out.");
	}

}
