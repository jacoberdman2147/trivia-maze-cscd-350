package triviaMaze.unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import triviaMaze.maze.*;
import triviaMaze.room.*;
import triviaMaze.game.*;
import triviaMaze.userInterface.*;
import triviaMaze.question.*;
import triviaMaze.databaseService.*;

class MazeTest {

	@Test
	void test() {
		IMaze maze = new LinkedRectangularMaze(2,2);
		IRoom cur = maze.getStart();
		cur = cur.getRoom("right").getRoom("down");
		assertEquals(cur, maze.getEnd());
	}

}
