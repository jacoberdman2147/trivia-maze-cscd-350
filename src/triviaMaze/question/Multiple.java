package triviaMaze.question;

public class Multiple implements IQuestion {
	private String question;
	private String[] answers;
	private String correct;
	public Multiple(String question, String[] answers, String correct) {
		this.question = question;
		this.answers = answers;
		this.correct = correct;
	}
	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCorrect(String guess) {
		// TODO Auto-generated method stub
		return false;
	}

}
