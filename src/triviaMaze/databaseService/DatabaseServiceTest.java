package triviaMaze.databaseService;

import triviaMaze.question.IQuestion;

public class DatabaseServiceTest {
	public static void main(String[] args) {
		IDatabaseService data = new DatabaseService();
		try {
			data.createTable("multiple");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.addQuestionMultiple(1, "This is a sample question.", "Possible answer 1", "Possible answer 2", "Possible answer 3", "This is the correct answer");
		IQuestion quest = data.constructQuestionMultiple();
		System.out.println(quest.getQuestion());
		String[] temp = quest.getAnswers();
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
		}
		System.out.println(quest.getCorrect());
	}
}
