package triviaMaze.userInterface;

import triviaMaze.game.*;

public class NullInterface implements IUserInterface {
	private ITriviaMazeGame game;
	private boolean miss = false;
	private boolean lost = false;
	private boolean won = false;

	@Override
	public void begin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLose(String direction) {
		lost = true;
	}

	@Override
	public void onWin(String direction) {
		won = true;
	}

	public boolean hasWon() {
		return won;
	}

	public boolean hasLost() {
		return lost;
	}

	@Override
	public boolean askQuestion() {
		return !miss;
	}

	public void setMiss(boolean b) {
		miss = b;
	}

	@Override
	public void showStatus(String status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showHelp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

}
