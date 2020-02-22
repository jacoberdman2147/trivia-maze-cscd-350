package triviaMaze.question;

public interface IQuestion {
public void displayQuestion(String id);
public void displayAnswers(String id);
public void isCorrect(String id, String guess);
}
