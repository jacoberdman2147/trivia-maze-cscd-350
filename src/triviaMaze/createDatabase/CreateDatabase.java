package triviaMaze.createDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import triviaMaze.question.IMultiple;
import triviaMaze.question.IQuestion;

public class CreateDatabase {
	public static void main(String[] args) throws ClassNotFoundException 
	{
	createTable();
	IQuestion question = new IMultiple();
	question.displayQuestion("1");
	question.displayAnswers("1");
	question.isCorrect("1", "Correct answer 1");
	}
	private static Connection connect() {
		String url = "jdbc:sqlite:triviaMazeData.db";
		Connection c = null;
		try {
			c = DriverManager.getConnection(url);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	public static void createTable() throws ClassNotFoundException {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = connect();
			Statement stmt = c.createStatement();
			System.out.println("Opened database successfully.");
			String sql = "CREATE TABLE IF NOT EXISTS MULTIPLE " +
					 "(ID	INT	PRIMARY	KEY	NOT	NULL, " +
					 "QUESTION	TEXT	NOT	NULL, " +
					 "ANSWER1	TEXT	NOT	NULL, " +
					 "ANSWER2	TEXT	NOT	NULL, " +
					 "ANSWER3	TEXT	NOT	NULL, " +
					 "CORRECT	TEXT	NOT	NULL) ";
			stmt.executeUpdate(sql);
			sql = "INSERT OR IGNORE INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
					 "VALUES (1, 'This is a sample question 1', 'Sample answer 1 1', 'Sample answer 2 1', 'Sample answer 3 1', 'Correct answer 1' );";
					stmt.executeUpdate(sql);
			sql = "INSERT OR IGNORE INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
					 "VALUES (2, 'This is a sample question 2', 'Sample answer 1 2', 'Sample answer 2 2', 'Sample answer 3 2', 'Correct answer 2' );";
					stmt.executeUpdate(sql);
			sql = "INSERT OR IGNORE INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
					 "VALUES (3, 'This is a sample question 3', 'Sample answer 1 3', 'Sample answer 2 3', 'Sample answer 3 3', 'Correct answer 3' );";
					stmt.executeUpdate(sql);
			sql = "INSERT OR IGNORE INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
					 "VALUES (4, 'This is a sample question 4', 'Sample answer 1 4', 'Sample answer 2 4', 'Sample answer 3 4', 'Correct answer 4' );";
					stmt.executeUpdate(sql);
			sql = "INSERT OR IGNORE INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
					 "VALUES (5, 'This is a sample question 5', 'Sample answer 1 5', 'Sample answer 2 5', 'Sample answer 3 5', 'Correct answer 5' );";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(SQLException e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}
		System.out.println("Table created successfully.");
	}
	public static void getQuestion(String id) {
		String sql = "SELECT QUESTION, ID FROM MULTIPLE";
		try {
		Connection c = connect();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			if(rs.getString("id").contentEquals(id))
			System.out.println(rs.getString("question"));
		}
		}catch(Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	public static void getAnswers(String id) {
		String sql = "SELECT ANSWER1, ANSWER2, ANSWER3, CORRECT, ID FROM MULTIPLE";
		try {
			Connection c = connect();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("id").contentEquals(id)) {
				System.out.println(rs.getString("ANSWER1"));
				System.out.println(rs.getString("ANSWER2"));
				System.out.println(rs.getString("ANSWER3"));
				System.out.println(rs.getString("CORRECT"));
				}
			}
		}catch(Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	public static void checkCorrect(String id, String guess) {
		String sql = "SELECT CORRECT, ID FROM MULTIPLE";
		try {
			Connection c = connect();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("id").contentEquals(id)) {
					if(rs.getString("correct").contentEquals(guess))
						System.out.println("This is the correct answer");
					else
						System.out.println("This is the wrong answer");
				}
			}
		}catch(Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
