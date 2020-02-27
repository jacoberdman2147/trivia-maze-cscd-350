package triviaMaze.question;

/**
 * An implementation of IQuestion in which the question is a basic multiple
 * choice question. Preferably has at most 4 answers
 * 
 * @author Jacob Erdman, Randy Heckard
 *
 */
public class MultipleChoiceQuestion implements IQuestion {
	private String question;
	private String[] answers;
	private String correct;

	/**
	 * Creates a new MultipleChoiceQuestion
	 * 
	 * @param question
	 *            The text of the question
	 * @param answers
	 *            The array of possible answers to the question
	 * @param correct
	 *            The correct answer to the question
	 */
	public MultipleChoiceQuestion(String question, String[] answers, String correct) {
		this.question = question;
		this.answers = answers;
		this.correct = correct;
	}

	@Override
	public String getQuestion() {
		return this.question;
	}

	@Override
	public String[] getAnswers() {
		return this.answers;
	}

	@Override
	public String getCorrect() {
		return this.correct;
	}
}
