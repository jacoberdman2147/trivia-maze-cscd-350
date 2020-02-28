package triviaMaze.player;

import java.io.IOException;
import java.util.*;
import triviaMaze.eventService.*;
import triviaMaze.room.*;

/**
 * A simple implementation of IPlayer
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public class BasicPlayer implements IPlayer {
	private IRoom cur;
	private transient List<TmHandler> hookedHandlers;

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
		hookedHandlers = new LinkedList<TmHandler>();
		hookedHandlers.add(new TmHandler("a") {
			@Override
			public void fire() {
				tryMove("left");
			}
		});
		hookedHandlers.add(new TmHandler("d") {
			@Override
			public void fire() {
				tryMove("right");
			}
		});
		hookedHandlers.add(new TmHandler("w") {
			@Override
			public void fire() {
				tryMove("up");
			}
		});
		hookedHandlers.add(new TmHandler("s") {
			@Override
			public void fire() {
				tryMove("down");
			}
		});
		
		for (TmHandler handler : hookedHandlers) {
			TmEventService.addHandler(handler);
		}
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
		System.out.println("This is a question, reply ''");
		Scanner s = new Scanner(System.in);
		String response = s.nextLine();
		return response.equals("");
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		addEventHandlers();
	}
	
	public void cleanUp() {
		if (hookedHandlers != null) {
			for (TmHandler handler : hookedHandlers) {
				TmEventService.removeHandler(handler);
			}
		}
	}

}
