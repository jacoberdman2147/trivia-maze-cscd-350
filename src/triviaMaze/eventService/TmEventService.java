package triviaMaze.eventService;

import java.util.*;

public class TmEventService {
	// TODO: FIX THE FACT THAT EVENTS CAN BE CALLED AGAIN IN IMPROPER ORDER DUE TO EVENTS WITHIN EVENTS THAT ARE CAUGHT BY MORE THAN ONE HANDLER
	
	private static List<TmHandler> handlers;
	static {
		handlers = new LinkedList<TmHandler>();
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
		for (TmHandler handler : handlers) {
			if (message.equals(handler.trigger)){ //This will likely need to be changed when swing is implemented and we can use proper key handling
				handler.fire();
			}
		}
	}
}
