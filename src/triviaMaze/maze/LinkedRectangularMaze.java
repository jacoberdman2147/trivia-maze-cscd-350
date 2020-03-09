package triviaMaze.maze;

import triviaMaze.room.*;
import java.util.*;

/**
 * An implementation of IMaze in which the maze is rectangular in structure, the
 * start is in the top left, and the end is in the bottom right
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public class LinkedRectangularMaze implements IMaze {
	private IRoom start;
	private IRoom end;

	/**
	 * Creates a new LinkedRectangularMaze and builds the entire structure.
	 * Initially all doors are open and unanswered
	 * 
	 * @param height
	 *            The height in rooms of the maze
	 * @param width
	 *            The width in rooms of the maze
	 */
	public LinkedRectangularMaze(int height, int width) {
		createMazeStructure(height, width); // Sets start and end
	}

	private void createMazeStructure(int height, int width) {
		IRoom[][] rooms = new IRoom[height + 2][width + 2]; // Creates a buffer
															// on the outside to
															// avoid dealing
															// with boundary
															// conditions
		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width + 1; j++) {
				rooms[i][j] = new LinkedRoom();
			}
		}
		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width + 1; j++) {
				assignRoomLinks(rooms, i, j);
			}
		}
		start = rooms[1][1];
		end = rooms[height][width];
	}

	private void assignRoomLinks(IRoom[][] rooms, int y, int x) {
		IRoom cur = rooms[y][x];
		cur.setRoom("left", rooms[y][x - 1]);
		cur.setRoom("right", rooms[y][x + 1]);
		cur.setRoom("up", rooms[y - 1][x]);
		cur.setRoom("down", rooms[y + 1][x]);

	}

	@Override
	public boolean isTraversable(IRoom cur) {
		return isTraversable(cur, new HashMap<IRoom, Integer>());
	}

	private boolean isTraversable(IRoom cur, Map<IRoom, Integer> traversed) {
		if (cur == null)
			return false;
		if (cur.equals(this.end))
			return true;
		if (traversed.containsKey(cur))
			return false;
		traversed.put(cur, Integer.valueOf(1));
		if (cur.isEnabled("right"))
			if (isTraversable(cur.getRoom("right"), traversed))
				return true;
		if (cur.isEnabled("down"))
			if (isTraversable(cur.getRoom("down"), traversed))
				return true;
		if (cur.isEnabled("left"))
			if (isTraversable(cur.getRoom("left"), traversed))
				return true;
		if (cur.isEnabled("up"))
			if (isTraversable(cur.getRoom("up"), traversed))
				return true;
		return false;
	}

	@Override
	public IRoom getStart() {
		return start;
	}

	@Override
	public IRoom getEnd() {
		return end;
	}

}
