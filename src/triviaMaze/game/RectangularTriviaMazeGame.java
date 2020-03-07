package triviaMaze.game;

import triviaMaze.eventService.*;
import triviaMaze.maze.*;
import triviaMaze.player.*;
import triviaMaze.userInterface.*;
import triviaMaze.room.*;
import java.io.*;
import java.util.*;

/**
 * The ITriviaMazeGame implementation in which the maze is rectangular, the
 * start is in the top left, and the end is in the bottom right.
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public class RectangularTriviaMazeGame implements ITriviaMazeGame {
	// TODO THIS CLASS WILL NEED TO TAKE IN A REFERENCE TO THE INTERFACE ONCE WE
	// GET THAT DEVELOPED
	// TODO MAYBE IMPLEMENT A CONSOLE INTERFACE CLASS WITH THOSE METHODS SUCH
	// THAT WE CAN EXTEND THIS STUFF OUT

	protected IPlayer player;
	protected IMaze maze;
	private IUserInterface ui;

	/**
	 * Creates a new RectangularTriviaMazeGame based on the following parameters
	 * 
	 * @param xSize        The horizontal size of the maze, i.e. number of rooms
	 * @param ySize        The vertical size of the maze, i.e. number of rooms
	 */
	public RectangularTriviaMazeGame(int xSize, int ySize, IUserInterface ui) {
		this.maze = new LinkedRectangularMaze(xSize, ySize);
		this.player = new BasicPlayer(maze.getStart());
		this.ui = ui;
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}

	@Override
	public boolean tryMove(String direction) {
		IRoom cur = player.getRoom();
		boolean success = false;
		if (!cur.isAnswered(direction)) {
			success = ui.askQuestion();
			cur.answer(direction);
			if (!success) {
				cur.disable(direction);
				System.out.println("Question missed!");
				boolean lose = maze.isTraversable(cur);
				if (lose) ui.onLose();
			}
		}
		if (cur.isEnabled(direction)) {
			player.move(cur.getRoom(direction));
			success = true;
			if (player.getRoom().equals(maze.getEnd())) {
				ui.onWin();
			}
		}
		return success;
	}

	@Override
	public IRoom getCurrentRoom() {
		return player.getRoom();
	}
}
