package triviaMaze.question;

import triviaMaze.createDatabase.CreateDatabase;
public class IMultiple implements IQuestion {

	@Override
	public void displayQuestion(String id) {
		CreateDatabase.getQuestion("1");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayAnswers(String id) {
		CreateDatabase.getAnswers("1");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isCorrect(String id, String guess) {
		CreateDatabase.checkCorrect(id, guess);
		// TODO Auto-generated method stub
		
	}

}
