package triviaMaze.DatabaseService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService implements IDatabaseService{
	private int multipleQuestions = 0;
	private int trueQuestions = 0;
	private int shortQuestions = 0;
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
	
	public void createTable(String tableName, String type) {
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
		Statement stmt = c.createStatement();
		
		String sql = "INSERT OR IGNORE INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
				 "VALUES (id, question, answer1, answer2, answer3, correct);";
				stmt.executeUpdate(sql);
		stmt.close();
		multipleQuestions++;
		}catch(SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public String getQuestion() {
		String result = "";
		Connection c = getConnection();
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT QUESTION FROM MULTIPLE";
			ResultSet rs = stmt.executeQuery(sql);
			result = rs.getString("question");
		}catch(SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return result;
	}
	
	public String[] getAnswers() {
		String[] result = new String[4];
		Connection c = getConnection();
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT ANSWER1, ANSWER2, ANSWER3, CORRECT FROM MULTIPLE";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
				result[0] = rs.getString("ANSWER1");
				result[1] = rs.getString("ANSWER2");
				result[2] = rs.getString("ANSWER3");
				result[3] = rs.getString("CORRECT");
		}catch(SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return result;
		
	}
}
