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
	void basicStartToFinish() {
		IMaze maze = new LinkedRectangularMaze(2,2);
		IRoom cur = maze.getStart();
		cur = cur.getRoom("right").getRoom("down");
		assertEquals(cur, maze.getEnd());
	}
	
	@Test
	void rectangularStartToFinish() {
		IMaze maze = new LinkedRectangularMaze(3,2);
		IRoom cur = maze.getStart();
		cur = cur.getRoom("right").getRoom("down").getRoom("down");
		assertEquals(cur, maze.getEnd());
	}
	
	@Test
	void largeRectangularStartToFinish() {
		int height = 200;
		int width = 450;
		IMaze maze = new LinkedRectangularMaze(height, width);
		IRoom cur = maze.getStart();
		for (int i = 0; i < height - 1; i++) {
			cur = cur.getRoom("down");
		}
		for (int i = 0; i < width - 1; i++) {
			cur = cur.getRoom("right");
		}
		assertEquals(cur, maze.getEnd());
	}
	
	@Test
	void notTraversableFromStart() {
		IMaze maze = new LinkedRectangularMaze(5,5);
		IRoom cur = maze.getStart();
		cur.disable("right");
		cur.disable("down");
		assertEquals(maze.isTraversable(cur), false);
	}
	
	@Test
	void notTraversableFromEnd() {
		IMaze maze = new LinkedRectangularMaze(5,5);
		IRoom cur = maze.getEnd();
		cur.disable("left");
		cur.disable("up");
		assertEquals(maze.isTraversable(maze.getStart()), false);
	}
	
	@Test
	void twoNotTraversableFromStart() {
		IMaze maze = new LinkedRectangularMaze(2,2);
		IRoom cur = maze.getStart();
		cur.disable("right");
		maze.getEnd().disable("left");
		assertEquals(maze.isTraversable(maze.getStart()), false);
	}
	
	@Test
	void traversableAtEnd() {
		IMaze maze = new LinkedRectangularMaze(5,5);
		IRoom cur = maze.getEnd();
		assertEquals(maze.isTraversable(maze.getEnd()), true);
	}
	
	@Test
	void traversableFromStart() {
		IMaze maze = new LinkedRectangularMaze(3,3);
		IRoom cur = maze.getStart();
		cur.disable("right");
		maze.getEnd().disable("left");
		assertEquals(maze.isTraversable(maze.getStart()), true);
	}
	
	@Test
	void largeTraversableFromStart() {
		IMaze maze = new LinkedRectangularMaze(300,300);
		IRoom cur = maze.getStart();
		cur.disable("right");
		maze.getEnd().disable("left");
		assertEquals(maze.isTraversable(maze.getStart()), true);
	}
	
	@Test
	void linkedRoomAnswerDisableTest() {
		IMaze maze = new LinkedRectangularMaze(2,2);
		IRoom cur = maze.getStart();
		cur.disable("right");
		cur.answer("down");
		assertEquals(cur.isEnabled("right"), cur.getRoom("right").isEnabled("left"));
		assertEquals(cur.isAnswered("down"), cur.getRoom("down").isAnswered("up"));
		cur.answer("right");
		assertEquals(cur.isAnswered("right"), cur.getRoom("right").isAnswered("left"));
		assertEquals(cur.getRoom("right").isAnswered("left"), true);
		assertEquals(cur.getRoom("right").isEnabled("left"), false);
	}
	
	@Test
	void linkedRoomMoveTest() {
		IMaze maze = new LinkedRectangularMaze(2,2);
		IRoom cur = maze.getStart();
		assertEquals(cur.getRoom("right"), cur.getRoom("down").getRoom("right").getRoom("up"));
		assertEquals(cur, cur.getRoom("right").getRoom("left"));
	}
	
	@Test
	void linkedMazeInvalidArgs() {
		assertThrows(IllegalArgumentException.class, () -> new LinkedRectangularMaze(-1,-1));
		IMaze maze = new LinkedRectangularMaze(2,2);
		assertThrows(IllegalArgumentException.class, () -> maze.getStart().getRoom("no"));
	}
	
}
