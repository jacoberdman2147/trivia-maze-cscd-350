package triviaMaze.eventService;

import java.util.*;

/**
 * A static class designed to contain TmHandlers and handle events corresponding
 * to particular strings that are fired by classes within TriviaMaze that are
 * handled by those TmHandlers
 * 
 * @author Jacob Erdman, Randy Heckard
 */
public class TmEventService {

	private static List<TmHandler> handlers;
	private static Queue<String> eventQueue;
	private static boolean currentlyHandling;

	static {
		handlers = new LinkedList<TmHandler>();
		eventQueue = new LinkedList<String>();
		currentlyHandling = false;
	}
	
	//This class should have no constructor as it is designed to be a static class which java doesn't support...
	private TmEventService() {}

	/**
	 * Adds a handler to the list of handlers
	 * 
	 * @param handler
	 *            The handler to be added to the list of handlers
	 */
	public static void addHandler(TmHandler handler) {
		handlers.add(handler);
	}

	/**
	 * Attempts to remove a handler from the list of TmHandlers
	 * 
	 * @param handler
	 *            Handler to remove, should be a reference to one added earlier in
	 *            order for it to be found and removed
	 * @return Returns a boolean representing whether or not the handler was found
	 *         and successfully removed
	 */
	public static boolean removeHandler(TmHandler handler) {
		if (handlers.contains(handler)) {
			handlers.remove(handler);
			return true;
		}
		return false;
	}

	/**
	 * Fires an event to this service to be caught by all corresponding TmHandlers
	 * listening for that event. These events can be anything, but should be
	 * standardized throughout the project so they can be caught and handled with
	 * the same set of listeners.
	 * 
	 * @param message
	 *            The name of the event to fire. Note that this parameter is case
	 *            insensitive!
	 */
	public static void fireEvent(String message) {
		eventQueue.add(message.toLowerCase());
		if (!currentlyHandling)
			handleEventQueue();
	}

	private static void handleEventQueue() {
		currentlyHandling = true;
		while (!eventQueue.isEmpty()) {
			String message = eventQueue.remove();
			for (int i = 0; i < handlers.size(); i++) {
				//System.out.println(handlers.get(i).trigger); Debug
				if (message.equals(handlers.get(i).trigger)) {
					handlers.get(i).fire();
				}
			}
		}
		currentlyHandling = false;
	}
}
