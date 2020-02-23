package triviaMaze.game;

import triviaMaze.eventService.*;
import triviaMaze.maze.*;
import triviaMaze.player.*;
import triviaMaze.room.*;
import triviaMaze.question.*;
import triviaMaze.inputService.*;

public class RectangularTriviaMazeGame implements ITriviaMazeGame {
	// TODO THIS CLASS WILL NEED TO TAKE IN A REFERENCE TO THE INTERFACE ONCE WE GET THAT DEVELOPED
	// TODO THIS CLASS WILL NEED TO TAKE IN A REFERENCE TO THE DATABASE SERVICE OR UNDERSTAND THAT A SINGLETON FOR IT EXISTS
	// TODO MAYBE IMPLEMENT A CONSOLE INTERFACE CLASS WITH THOSE METHODS SUCH THAT WE CAN EXTEND THIS STUFF OUT
	
	protected IPlayer player;
	protected IMaze maze;
	protected ITmInputService inputService;
	private boolean playing;
	
	public RectangularTriviaMazeGame(int xSize, int ySize, ITmInputService inputService) {
		this.maze = new LinkedRectangularMaze(xSize, ySize);
		this.player = new BasicPlayer(maze.getStart());
		this.inputService = inputService;
		this.playing = false;
	}
	
	@Override
	public void start() {
		playing = true;
		addEventHandlers();
		while (playing) {
			TmEventService.fireEvent(inputService.getInput());
		}
		System.out.println("Made it out of the game inside the game class");
	}
	
	private void endGame() {
		playing = false;
		System.out.println("The game is now over.");
	}
	
	private void addEventHandlers() {
		TmEventService.addHandler(new TmHandler("onmove"){
			@Override
			public void fire() {
				if (player.getRoom().equals(maze.getEnd())) {
					 TmEventService.fireEvent("win");
				}
			}
		});
		TmEventService.addHandler(new TmHandler("questionmiss"){
			@Override
			public void fire() {
				if (!maze.isTraversable(player.getRoom())) {
					TmEventService.fireEvent("lose");
				}
			}
		});
		TmEventService.addHandler(new TmHandler("win"){
			@Override
			public void fire() {
				System.out.println("You win!");
				endGame();
			}
		});
		TmEventService.addHandler(new TmHandler("lose"){
			@Override
			public void fire() {
				System.out.println("You lost :(");
				endGame();
			}
		});
	}
}
