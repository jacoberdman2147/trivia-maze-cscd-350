package triviaMaze;

/**
 * An interface designed to allow a class to remove its event handlers and clean itself up so that old event handlers will not cause problems
 * @author Jacob Erdman
 *
 */
public interface Cleanable {
	
	/**
	 * The method that performs the cleanup in a class
	 */
	public void cleanUp();
}
