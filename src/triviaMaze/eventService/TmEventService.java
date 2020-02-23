package triviaMaze.eventService;

import java.util.*;

public class TmEventService {
	// TODO: FIX THE FACT THAT EVENTS CAN BE CALLED AGAIN IN IMPROPER ORDER DUE TO EVENTS WITHIN EVENTS THAT ARE CAUGHT BY MORE THAN ONE HANDLER
	
	private static List<TmHandler> handlers;
	private static Queue<String> eventQueue;
	private static boolean currentlyHandling;
	
	static {
		handlers = new LinkedList<TmHandler>();
		eventQueue = new LinkedList<String>();
		currentlyHandling = false;
	}
	
	public static void addHandler(TmHandler handler) {
		handlers.add(handler);
	}
	
	public static boolean removeHandler(TmHandler handler) {
		if (handlers.contains(handler)) {
			handlers.remove(handler);
			return true;
		}
		return false;
	}
	
	public static void fireEvent(String message) {
		eventQueue.add(message);
		if (!currentlyHandling) handleEventQueue();
	}
	
	private static void handleEventQueue() {
		currentlyHandling = true;
		while (!eventQueue.isEmpty()) {
			String message = eventQueue.remove();
			for (int i = 0; i < handlers.size(); i++) {
				if (message.equals(handlers.get(i).trigger)){ //This will likely need to be changed when swing is implemented and we can use proper key handling
					handlers.get(i).fire();
				}
			}
		}
		currentlyHandling = false;
	}
}
