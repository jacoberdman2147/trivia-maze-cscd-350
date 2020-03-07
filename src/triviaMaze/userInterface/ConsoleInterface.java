package triviaMaze.userInterface;

import triviaMaze.maze.*;
import triviaMaze.game.*;
import triviaMaze.room.*;
import triviaMaze.question.*;
import triviaMaze.databaseService.*;
import java.io.*;
import java.util.*;

public class ConsoleInterface implements IUserInterface{
	boolean exit;
	boolean inProgress;
	IDatabaseService databaseSvc;
	ITriviaMazeGame game;
	String[] statusLines;
	Scanner in;
	
	
	
	public ConsoleInterface() {
		exit = false;
		inProgress = false;
		this.databaseSvc = new DatabaseService();
		statusLines = new String[2];
		in = new Scanner(System.in);
	}

	@Override
	public void begin() {
		showStatus("Welcome to the maze. Enter \"?\" for help.");
		while (!exit) {
			showMenu();
			while (inProgress) {
				updateDisplay();
				handleInput(in.nextLine());
			}
			updateDisplay();
			
		}
	}

	@Override
	public void updateDisplay() {
		for (String status : statusLines) {
			System.out.println(status);
		}
		IRoom cur = game.getCurrentRoom();
		displayRoom(cur);
	}
	
	private void displayRoom(IRoom cur) {
		//no
	}

	@Override
	public void onLose() {
		// :(
		inProgress = false;
		showStatus("Game over!");
	}

	@Override
	public void onWin() {
		// :)
		inProgress = false;
		showStatus("You win! Congratulations!");
	}

	@Override
	public boolean askQuestion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showStatus(String status) {
		for (int i = statusLines.length - 1; i > 0; i--) {
			statusLines[i] = statusLines[i - 1];
		}
		statusLines[0] = status;
	}
	
	@Override
	public String getHelpString() {
		return "";
	}
	
	private void handleInput(String input) {
		
	}

	@Override
	public void showMenu() {
		System.out.println("Welcome to Trivia Maze. Please select an option:\n1. Start a new game\n2. Load a saved game");
		boolean valid = false;
		char choice = ' ';
		while (!valid) {
			String input = in.nextLine();
			if (input.length() > 0) {
				choice = input.charAt(0);
				if (choice == '1' || choice == '2') {
					valid = true;
				}
			}
		}
		if (choice == '1') {
			newGame();
		} else {
			loadGame();
		}
	}

	@Override
	public void loadGame() {
		//no
	}

	@Override
	public void saveGame() {
		//no
	}

	@Override
	public void newGame() {
		System.out.print("We are making a rectangular trivia maze. The size may be between 2 and 10\nDesired height: ");
		int height = getInt(2, 10);
		System.out.print("Desired width: ");
		int width = getInt(2, 10);
		game = new RectangularTriviaMazeGame(height, width, this);
		inProgress = true;
	}
	
	private int getInt(int low, int high) {
		int ret = low - 1;
		while (ret < low || ret > high) {
			if (in.hasNextInt()) {
				ret = in.nextInt();
			} else in.nextLine();
		}
		return ret;
	}
	
	private void clearScreen() {
		System.out.println(new String(new char[50]).replace("\0", "\r\n"));
	}

}
