package triviaMaze.player;

import java.util.*;
import triviaMaze.eventService.*;
import triviaMaze.room.*;

/**
 * A simple implementation of IPlayer
 * 
 * @author Jacob Erdman
 *
 */
public class BasicPlayer implements IPlayer {
	private IRoom cur;

	/**
	 * Creates a BasicPlayer
	 * 
	 * @param start
	 *            The IRoom corresponding to the BasicPlayer's starting location
	 */
	public BasicPlayer(IRoom start) {
		cur = start;
		addEventHandlers();
	}

	private void addEventHandlers() {
		TmEventService.addHandler(new TmHandler("a") {
			@Override
			public void fire() {
				tryMove("left");
			}
		});
		TmEventService.addHandler(new TmHandler("d") {
			@Override
			public void fire() {
				tryMove("right");
			}
		});
		TmEventService.addHandler(new TmHandler("w") {
			@Override
			public void fire() {
				tryMove("up");
			}
		});
		TmEventService.addHandler(new TmHandler("s") {
			@Override
			public void fire() {
				tryMove("down");
			}
		});
	}

	@Override
	public IRoom getRoom() {
		return cur;
	}

	@Override
	public void move(IRoom to) {
		cur = to;
	}

	private void tryMove(String direction) {
		if (cur.isEnabled(direction) && cur.isAnswered(direction)) {
			cur = cur.getRoom(direction);
			System.out.println("Moved " + direction);
			TmEventService.fireEvent("onmove");
		} else if (!cur.isAnswered(direction) && cur.isEnabled(direction)) {
			boolean success = askQuestion();
			cur.answer(direction);
			if (!success) {
				cur.disable(direction);
				System.out.println("Question missed!");
				TmEventService.fireEvent("questionmiss");
			} else
				tryMove(direction);
		} else {
			System.out.println("Sorry you can't move in that direction");
		}
	}

	private boolean askQuestion() {
		System.out.println("This is a question, reply 'test'");
		Scanner s = new Scanner(System.in);
		String response = s.nextLine();
		s.close();
		return response.equals("test");
	}

}
