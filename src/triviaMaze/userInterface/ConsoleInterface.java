package triviaMaze.userInterface;

import triviaMaze.maze.*;
import triviaMaze.game.*;
import triviaMaze.room.*;
import triviaMaze.question.*;
import triviaMaze.databaseService.*;
import java.io.*;
import java.util.*;

public class ConsoleInterface implements IUserInterface{
	private boolean exit;
	private boolean inProgress;
	private IDatabaseService databaseSvc;
	private ITriviaMazeGame game;
	private String[] statusLines;
	private Scanner in;
	private LinkedHashMap<String, IInterfaceFunction> inputFunctions;
	private String clearString;
	
	
	
	public ConsoleInterface(IDatabaseService databaseSvc) {
		exit = false;
		inProgress = false;
		this.databaseSvc = databaseSvc;
		statusLines = new String[4];
		for(int i = 0; i < statusLines.length; i++) {
			statusLines[i] = "";
		}
		in = new Scanner(System.in);
		inputFunctions = new LinkedHashMap<String, IInterfaceFunction>();
		setupInputs();
		
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 50; i++) {
			s.append("\n");
		}
		clearString = s.toString();
	}
	
	private void setupInputs() {
		String[] directions = {"left", "right", "up", "down"};
		for(String dir : directions){
			inputFunctions.put(dir, new IInterfaceFunction() {
				@Override
				public void execute() {
					boolean success = game.tryMove(dir);
					if (success) {
						showStatus("Moved " + dir + ".");
					} else {
						showStatus("You cannot move " + dir + ".");
					}
				}

				@Override
				public String getHelpString() {
					return "Moves the player " + dir + ".";
				}
			});
		}
		
		inputFunctions.put("save", new IInterfaceFunction() {
			@Override
			public void execute() {
				saveGame();
			}
			
			@Override
			public String getHelpString() {
				return "Allows you to save your game.";
			}
		});
		
		inputFunctions.put("exit", new IInterfaceFunction() {

			@Override
			public void execute() {
				inProgress = false;
				showStatus("Game exited, hit enter to continue...");
				in.nextLine();
			}

			@Override
			public String getHelpString() {
				return "Allows you to quit the game.";
			}
			
		});
		
		inputFunctions.put("?", new IInterfaceFunction() {

			@Override
			public void execute() {
				showHelp();
			}

			@Override
			public String getHelpString() {
				return "Shows this dialogue.";
			}
			
		});
	}

	@Override
	public void begin() {
		showStatus("Welcome to the maze. Enter \"?\" for help.");
		while (!exit) {
			clearStatus();
			showMenu();
			while (inProgress) {
				updateDisplay();
				handleInput(in.nextLine());
			}
		}
	}

	@Override
	public void updateDisplay() {
		clearScreen();
		for (String status : statusLines) {
			System.out.println(status);
		}
		IRoom cur = game.getCurrentRoom();
		displayRoom(cur);
		System.out.println("\r\n");
	}
	
	private void displayRoom(IRoom cur) {
		//Here we pray that they are on CP437
		System.out.println("╔═" + roomChar(cur, "up") + "═╗\r\n║   ║\r\n" + roomChar(cur, "left") + " @ " + roomChar(cur, "right") + "\r\n║   ║\r\n╚═" + roomChar(cur, "down") + "═╝");
	}
	
	private char roomChar(IRoom cur, String dir) {
		if (!cur.isAnswered(dir) && cur.isEnabled(dir)) return '+';
		else if (cur.isEnabled(dir)) return ' ';
		else {
			switch (dir) {
			case "left":
			case "right":
				return '║';
			case "up":
			case "down":
				return '═';
			}
			throw new IllegalArgumentException("Invalid direction passed to roomChar method...");
		}
		
	}

	@Override
	public void onLose(String direction) {
		// :(
		inProgress = false;
		showStatus("You cannot move " + direction + ".");
		showStatus("Game over!");
		updateDisplay();
		System.out.println("Press enter to continue...");
		in.nextLine();
	}

	@Override
	public void onWin(String direction) {
		// :)
		inProgress = false;
		showStatus("Moved " + direction + ".");
		showStatus("You win! Congratulations!");
		updateDisplay();
		System.out.println("Press enter to continue...");
		in.nextLine();
	}

	@Override
	public boolean askQuestion() {
		IQuestion question = databaseSvc.constructQuestionMultiple();
		Random ran = new Random();
		int choice;
		HashSet<Integer> hset = new HashSet<Integer>();
		System.out.println(question.getQuestion());
		String[] answers = question.getAnswers();
		String correct = question.getCorrect();
		for(int count = 0; count < 4;)
		{
			choice = ran.nextInt(4);
			if(!hset.contains(choice)) 
			{
				if(choice == 3)
				{
					System.out.println(correct);
					count++;
				}
				else
				{
					System.out.println(answers[choice]);
					count++;
				}
			hset.add(choice);
			}
		}
		if(in.nextLine().equals(question.getCorrect()))
			return true;
		else
			return false;
	}

	@Override
	public void showStatus(String status) {
		for (int i = 0; i < statusLines.length - 1; i++) {
			statusLines[i] = statusLines[i + 1];
		}
		statusLines[statusLines.length - 1] = status;
	}
	
	@Override
	public void showHelp() {
		clearScreen();
		System.out.println("Symbols:\r\n\"+\": A closed, unanswered door\r\n\" \": An open door\r\nWall: A closed, answered door, never openable\r\n\"@\": You");
		System.out.println("Press enter to continue...");
		in.nextLine();
		System.out.println("Commands:");
		inputFunctions.forEach((k, v) -> {
			System.out.printf("%s: %s\r\n", k, v.getHelpString());
		});
		System.out.println("Press enter to continue...");
		in.nextLine();
	}
	
	private void handleInput(String input) {
		if (inputFunctions.containsKey(input)) {
			inputFunctions.get(input).execute();
		} else {
			showStatus("Invalid command!");
		}
	}

	@Override
	public void showMenu() {
		clearScreen();
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
		clearScreen();
		System.out.print("Input the name of the save file: ");
		String fileName = in.nextLine();
		File loadFile = new File(fileName);
		if (new File(fileName).exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(loadFile);
				ObjectInputStream objInput = new ObjectInputStream(fileInputStream);
				ITriviaMazeGame loadedGame = (ITriviaMazeGame)objInput.readObject();
				game = loadedGame;
				game.setUi(this);
				inProgress = true;
			} catch (FileNotFoundException e) {
				System.out.println("Non-existent file, load cancelled.\nHit enter to continue...");
				in.nextLine();
				return;
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("An unexpected exception occurred when trying to open the input file. Perhaps it isn't actually a save game?\nPress enter to continue...");
				in.nextLine();
				return;
			}
		} else {
			System.out.println("Non-existent file, load cancelled.\nHit enter to continue...");
			in.nextLine();
		}
	}

	@Override
	public void saveGame() {
		clearScreen();
		System.out.print("What would you like to name the save file: ");
		String fileName = in.nextLine();
		File outFile = new File(fileName);
		if (outFile.exists()) {
			System.out.println("The specified file already exists. Save cancelled...\r\nPress enter to continue...");
			in.nextLine();
			return;
		}
		try {
			outFile.createNewFile();
			FileOutputStream fOut = new FileOutputStream(outFile);
			ObjectOutputStream objOut = new ObjectOutputStream(fOut);
			objOut.writeObject(game);
			showStatus("Save successful!");
		} catch (IOException e) {
			// TODO Make sure that this doesn't break when the game is installed to program files or whatever
			System.out.println("Creation of the new file failed, perhaps a permissions error? Save cancelled...\r\nPress enter to continue...");
			in.nextLine();
			return;
		}
		
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
				in.nextLine();
			} else in.nextLine();
		}
		return ret;
	}
	
	private void clearScreen() {
		System.out.println(clearString);
	}
	
	private void clearStatus() {
		for(int i = 0; i < statusLines.length; i++) {
			statusLines[i] = "";
		}
	}

}
