package triviaMaze.databaseService;

import triviaMaze.question.IQuestion;

public interface IDatabaseService {
public void createTable(String type) throws ClassNotFoundException;
public void addQuestionMultiple(String question, String answer1, String answer2, String answer3, String correct);
public IQuestion constructQuestionMultiple();
}
