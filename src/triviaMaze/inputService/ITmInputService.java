package triviaMaze.inputService;

/**
 * An interface defining an InputService which allows the current game to get
 * input from the user in a modular manner
 * 
 * @author Jacob Erdman, Randy Heckard
 * @deprecated
 */
public interface ITmInputService {

	/**
	 * Gets input from the user playing the game
	 * 
	 * @return Returns a string representing the input the user gave. If the string
	 *         is a single character long, it represents a key press, otherwise it
	 *         represents a specific event.
	 */
	public String getInput();
}
