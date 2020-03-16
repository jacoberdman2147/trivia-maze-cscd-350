package triviaMaze.unitTest;

import triviaMaze.game.*;
import triviaMaze.userInterface.*;
import triviaMaze.room.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

	private NullInterface ui;
	private ITriviaMazeGame game;
	private static final int height = 5;
	private static final int width = 5;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ui = new NullInterface();
		game = new RectangularTriviaMazeGame(height, width, ui);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void moveAnswerAndMissTest() {
		IRoom cur = game.getCurrentRoom();
		game.tryMove("right");
		assertEquals(game.getCurrentRoom(), cur.getRoom("right"));
		assertEquals(game.tryMove("left"), true);
		ui.setMiss(true);
		assertEquals(game.tryMove("right"), true);
		assertEquals(game.tryMove("down"), false);
		assertEquals(game.getCurrentRoom(), cur.getRoom("right"));
	}

	@Test
	void invalidMovementTest() {
		assertEquals(game.tryMove("left"), false);
		assertEquals(game.tryMove("up"), false);
	}

	@Test
	void winTest() {
		for (int i = 0; i < height - 1; i++) {
			game.tryMove("down");
		}
		for (int i = 0; i < width - 1; i++) {
			game.tryMove("right");
		}
		assertEquals(ui.hasWon(), true);
		assertEquals(ui.hasLost(), false);
		ui.setMiss(true);
		game.tryMove("up");
		game.tryMove("left");
		assertEquals(ui.hasLost(), false);
	}

	@Test
	void loseTest() {
		ui.setMiss(true);
		game.tryMove("right");
		game.tryMove("down");
		assertEquals(ui.hasLost(), true);
	}
}
