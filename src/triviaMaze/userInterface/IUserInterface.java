package triviaMaze.userInterface;

import triviaMaze.maze.*;

public interface IUserInterface {
	public void initialize();
	public void begin();
	public void updateDisplay(IMaze maze);
	public void onLose();
	public void onWin();
	public boolean askQuestion();
}
