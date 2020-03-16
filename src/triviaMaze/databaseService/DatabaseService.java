package triviaMaze.databaseService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;

import triviaMaze.question.IQuestion;
import triviaMaze.question.MultipleChoiceQuestion;

public class DatabaseService implements IDatabaseService {

	private HashSet<Integer> hset = new HashSet<Integer>();

	public void removeMultiple(String question) {
		Connection c = getConnection();
		String sql = "DELETE FROM MULTIPLE WHERE QUESTION == ?";
		try {
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, question);
			int rowsAltered = pstmt.executeUpdate();
			pstmt.close();
			if (rowsAltered > 0)
				decreaseQuestions();
			System.out.println("There are now " + numberOfQuestions() + " questions in the table");
			System.out.println("This is the new table of questions");
			displayQuestions();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int numberOfQuestions() {
		Connection c = getConnection();
		String sql = "SELECT COUNT FROM QUESTIONS";
		Statement stmt;
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int result = rs.getInt("count");
			stmt.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void increaseQuestions() {
		Connection c = getConnection();
		String sql = "UPDATE QUESTIONS SET COUNT = COUNT + 1;";
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void decreaseQuestions() {
		Connection c = getConnection();
		String sql = "UPDATE QUESTIONS SET COUNT = COUNT - 1;";
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void displayQuestions() {
		Connection c = getConnection();
		String sql = "SELECT * FROM MULTIPLE";
		Statement stmt;
		try {
			stmt = c.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				System.out.println(res.getString("question"));
			}
			System.out.println("There are " + numberOfQuestions() + " questions in the database.");
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		String url = "jdbc:sqlite:triviaMazeData.db";
		Connection c = null;
		try {
			c = DriverManager.getConnection(url);
		} catch (SQLException e) {
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
			if (type.contentEquals("multiple")) {
				String sql = "CREATE TABLE IF NOT EXISTS MULTIPLE " + "(ID	INT	PRIMARY	KEY	NOT	NULL, " + "QUESTION	TEXT	NOT	NULL, "
						+ "ANSWER1	TEXT	NOT	NULL, " + "ANSWER2	TEXT	NOT	NULL, " + "ANSWER3	TEXT	NOT	NULL, " + "CORRECT	TEXT	NOT	NULL) ";
				stmt.executeUpdate(sql);
				stmt.close();
			} else if (type.contentEquals("numberQuestions")) {
				String sql = "CREATE TABLE IF NOT EXISTS QUESTIONS " + "(COUNT	INT	NOT	NULL) ";
				stmt.executeUpdate(sql);
				sql = "INSERT OR IGNORE INTO QUESTIONS (COUNT) " + "VALUES(0);";
				stmt.executeUpdate(sql);
				stmt.close();
			} else {
				String sql = "CREATE TABLE IF NOT EXISTS SHORT " + "(ID	INT	PRIMARY	KEY	NOT	NULL, " + "QUESTION	TEXT	NOT	NULL, "
						+ "CORRECT	TEXT	NOT	NULL) ";
				stmt.executeUpdate(sql);
				stmt.close();
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void addQuestionMultiple(String question, String answer1, String answer2, String answer3, String correct) {
		Connection c = getConnection();
		try {
			int id = numberOfQuestions();
			String sql = "INSERT OR IGNORE INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " + "VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, question);
			pstmt.setString(3, answer1);
			pstmt.setString(4, answer2);
			pstmt.setString(5, answer3);
			pstmt.setString(6, correct);
			pstmt.executeUpdate();
			pstmt.close();
			increaseQuestions();
			System.out.println("There are now " + numberOfQuestions() + " questions in the table");
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public IQuestion constructQuestionMultiple() {
		Connection c = getConnection();
		Random ran = new Random();
		int tempID = 0;
		do {
			if (numberOfQuestions() == 1)
				tempID = 1;
			else
				tempID = ran.nextInt(numberOfQuestions() - 1) + 1;
		} while (hset.contains(tempID));
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
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return new MultipleChoiceQuestion(question, answers, correct);
	}
}
