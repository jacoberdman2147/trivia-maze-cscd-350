package triviaMaze;

import java.io.*;
import java.util.*;
import triviaMaze.game.ITriviaMazeGame;
import triviaMaze.game.*;
import triviaMaze.inputService.*;
import java.lang.reflect.*;

public class TriviaMazeMain {

	public static void main(String[] args) throws IOException {
		
		/*Class test = ConsoleInputService.class;
		try {
			ConsoleInputService test2 = (ConsoleInputService)test.newInstance();
			test2.getInput();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		ITriviaMazeGame game = new RectangularTriviaMazeGame(5, 5, new ConsoleInputService());
		FileOutputStream f = new FileOutputStream("test.tmp");
		ObjectOutput s = new ObjectOutputStream(f);
		s.writeObject(game);
		s.flush();
		s.close();
		f.close();
		game.cleanUp();
		
		FileInputStream f2 = new FileInputStream("test.tmp");
		ObjectInput s2 = new ObjectInputStream(f2);
		try {
			game = (RectangularTriviaMazeGame)s2.readObject();
			game.start();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Looks like we made it out.");
	}

}
