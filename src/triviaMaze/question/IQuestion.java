package triviaMaze.question;

public interface IQuestion {
	public String getQuestion();
	public String[] getAnswers();
	public boolean isCorrect(String guess);
}
