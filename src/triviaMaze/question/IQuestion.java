package triviaMaze.question;

/**
 * The interface representing question objects
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public interface IQuestion {

	/**
	 * Gets the question string
	 * 
	 * @return Returns a String corresponding to the text of the question
	 */
	public String getQuestion();

	/**
	 * Gets all possible answers to the question
	 * 
	 * @return Returns an array of Strings with all possible answers
	 */
	public String[] getAnswers();

	/**
	 * Gets the correct answer
	 * 
	 * @return Returns a string corresponding to the correct answer
	 */
	public String getCorrect();
}
