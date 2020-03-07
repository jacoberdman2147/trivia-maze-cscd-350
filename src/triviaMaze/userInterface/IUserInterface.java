package triviaMaze.userInterface;

import triviaMaze.maze.*;

public interface IUserInterface {
	public void begin();
	public void updateDisplay();
	public void onLose();
	public void onWin();
	public boolean askQuestion();
	public void showStatus(String status);
	public String getHelpString();
	public void showMenu();
	public void loadGame();
	public void saveGame();
	public void newGame();
}
