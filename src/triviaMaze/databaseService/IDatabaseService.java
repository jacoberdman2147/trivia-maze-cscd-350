package triviaMaze.DatabaseService;

public interface IDatabaseService {
public void createTable(String tableName, String type) throws ClassNotFoundException;
public void addQuestionMultiple(int id, String question, String answer1, String answer2, String answer3, String correct);
public String getQuestion();
}
