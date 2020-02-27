package triviaMaze.question;

public class MultipleChoiceQuestion implements IQuestion {
	private String question;
	private String[] answers;
	private String correct;
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
		// TODO Auto-generated method stub
		return this.correct;
	}
}
