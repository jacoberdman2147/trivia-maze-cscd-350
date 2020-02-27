package triviaMaze.eventService;

/**
 * A class which handlers a specific event fired by TmEventService. TmHandlers
 * have their functionality defined by the fire method, and the event they are
 * listening for is defined by the String trigger. This is essentially a
 * callback with a trigger
 * 
 * @author Jacob Erdman
 *
 */
public abstract class TmHandler {
	public final String trigger;

	/**
	 * Creates an instance of TmHandler which will be executed when trigger is
	 * equivalent to the event which was fired
	 * 
	 * @param trigger
	 *            The string corresponding to the event this handler is listening
	 *            for
	 */
	public TmHandler(String trigger) {
		this.trigger = trigger.toLowerCase();
	}

	/**
	 * The method containing the code which this method should run upon being fired
	 */
	public abstract void fire();

}
