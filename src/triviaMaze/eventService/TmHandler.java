package triviaMaze.eventService;

public abstract class TmHandler {
	public final String trigger;
	
	public TmHandler(String trigger) {
		this.trigger = trigger;
	}
	
	public abstract void fire();
	
}
