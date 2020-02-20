package triviaMaze.keyService;

public abstract class KeyHandler {
	public final char key;
	
	public KeyHandler(char key) {
		this.key = key;
	}
	
	public abstract void fire();
	
}
