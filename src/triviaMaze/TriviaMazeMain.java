package triviaMaze;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.*;
import java.util.*;
import triviaMaze.game.ITriviaMazeGame;
import triviaMaze.game.*;
import triviaMaze.inputService.*;
import java.lang.reflect.*;
import triviaMaze.userInterface.*;
import triviaMaze.databaseService.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TriviaMazeMain {

	public static void main(String[] args) throws IOException {

		/*
		 * Class test = ConsoleInputService.class; try { ConsoleInputService test2 =
		 * (ConsoleInputService)test.newInstance(); test2.getInput(); } catch
		 * (InstantiationException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/*
		 * ITriviaMazeGame game = new RectangularTriviaMazeGame(5, 5, null);
		 * 
		 * FileInputStream f2 = new FileInputStream("test.tmp"); ObjectInput s2 = new
		 * ObjectInputStream(f2); try { game =
		 * (RectangularTriviaMazeGame)s2.readObject(); } catch (ClassNotFoundException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } f2.close();
		 * System.out.println("Looks like we made it out.");
		 */

		/*
		 * Scanner s = new Scanner(System.in); int test = 0; while (test == 0) { if
		 * (s.hasNextInt()) { test = s.nextInt(); } else s.nextLine(); } IUserInterface
		 * ui = new ConsoleInterface(new DatabaseService()); ui.begin();
		 */

		IDatabaseService databaseSvc = new DatabaseService();
		IUserInterface ui = new ConsoleInterface(databaseSvc);
		ui.begin();
	}

}
