package triviaMaze.DatabaseService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;

import triviaMaze.question.IQuestion;
import triviaMaze.question.Multiple;

public class DatabaseService implements IDatabaseService{
	private int multipleQuestions = 0;
	private int trueQuestions = 0;
	private int shortQuestions = 0;
	private HashSet<Integer> hset = new HashSet<Integer>();
	private Connection getConnection() {
			String url = "jdbc:sqlite:triviaMazeData.db";
			Connection c = null;
			try {
				c = DriverManager.getConnection(url);
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
			return c;
		}
	
	public void createTable(String type) {
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection c = getConnection();
			Statement stmt = c.createStatement();
			if(type.contentEquals("multiple")) {
			String sql = "CREATE TABLE IF NOT EXISTS MULTIPLE " +
					 "(ID	INT	PRIMARY	KEY	NOT	NULL, " +
					 "QUESTION	TEXT	NOT	NULL, " +
					 "ANSWER1	TEXT	NOT	NULL, " +
					 "ANSWER2	TEXT	NOT	NULL, " +
					 "ANSWER3	TEXT	NOT	NULL, " +
					 "CORRECT	TEXT	NOT	NULL) ";
			stmt.executeUpdate(sql);
			stmt.close();
			}
			else if(type.contentEquals("true")) {
				String sql = "CREATE TABLE IF NOT EXISTS TRUE " +
						 "(ID	INT	PRIMARY	KEY	NOT	NULL, " +
						 "QUESTION	TEXT	NOT	NULL, " +
						 "ANSWER1	TEXT	NOT	NULL, " +
						 "CORRECT	TEXT	NOT	NULL) ";
				stmt.executeUpdate(sql);
				stmt.close();
			}
			else{
				String sql = "CREATE TABLE IF NOT EXISTS SHORT " +
						 "(ID	INT	PRIMARY	KEY	NOT	NULL, " +
						 "QUESTION	TEXT	NOT	NULL, " +
						 "CORRECT	TEXT	NOT	NULL) ";
				stmt.executeUpdate(sql);
				stmt.close();
			}
		}catch(SQLException e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}
	}
	
	public void addQuestionMultiple(int id, String question, String answer1, String answer2, String answer3, String correct) {
		Connection c = getConnection();
		try {
		String sql = "INSERT OR IGNORE INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
				 "VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setString(2, question);
				pstmt.setString(3, answer1);
				pstmt.setString(4, answer2);
				pstmt.setString(5, answer3);
				pstmt.setString(6, correct);
				pstmt.executeUpdate();
		pstmt.close();
		multipleQuestions++;
		}catch(SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public IQuestion constructQuestionMultiple() {
		Connection c = getConnection();
		Random ran = new Random();
		int tempID = 0;
		do {
			if(multipleQuestions == 1)
				tempID = 1;
			else
		tempID = ran.nextInt(multipleQuestions - 1) + 1;
		}while(hset.contains(tempID));
		String question = "";
		String[] answers = new String[3];
		String correct = "";
		try {
			String sql = "SELECT QUESTION, ANSWER1, ANSWER2, ANSWER3, CORRECT FROM MULTIPLE WHERE ID = ?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, tempID);
			ResultSet rs = pstmt.executeQuery();
			question = rs.getString("question");
			answers[0] = rs.getString("answer1");
			answers[1] = rs.getString("answer2");
			answers[2] = rs.getString("answer3");
			correct = rs.getString("correct");
			hset.add(tempID);
		}catch(SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return new Multiple(question, answers, correct);
	}
}
