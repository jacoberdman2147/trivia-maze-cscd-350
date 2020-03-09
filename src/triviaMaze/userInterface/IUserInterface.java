package triviaMaze.userInterface;

import triviaMaze.maze.*;

public interface IUserInterface {
	public void begin();
	public void updateDisplay();
	public void onLose(String direction);
	public void onWin(String direction);
	public boolean askQuestion();
	public void showStatus(String status);
	public void showHelp();
	public void showMenu();
	public void loadGame();
	public void saveGame();
	public void newGame();
}
