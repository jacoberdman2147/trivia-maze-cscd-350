package triviaMaze.keyService;

import java.util.*;

public class TmKeyService {
	private static List<KeyHandler> handlers;
	static {
		handlers = new LinkedList<KeyHandler>();
	}
	
	public static void addHandler(KeyHandler handler) {
		handlers.add(handler);
	}
	
	public static boolean removeHandler(KeyHandler handler) {
		if (handlers.contains(handler)) {
			handlers.remove(handler);
			return true;
		}
		return false;
	}
	
	public static void keyPressed(String message) {
		for (KeyHandler handler : handlers) {
			if (message.equals(String.valueOf(handler.key))){ //This will likely need to be changed when swing is implemented and we can use proper key handling
				handler.fire();
			}
		}
	}
}
