package triviaMaze.createDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {
	public static void main(String[] args) 
	{
	Connection c = null;
	Statement stmt = null;
	try {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:triviaMazeData.db");
		System.out.println("Opened database successfully.");
		
		stmt = c.createStatement();
		String sql = "CREATE TABLE MULTIPLE " +
					 "(ID	INT	PRIMARY	KEY	NOT	NULL, " +
					 "QUESTION	TEXT	NOT	NULL, " +
					 "ANSWER1	TEXT	NOT	NULL, " +
					 "ANSWER2	TEXT	NOT	NULL, " +
					 "ANSWER3	TEXT	NOT	NULL, " +
					 "CORRECT	TEXT	NOT	NULL) ";
		stmt.executeUpdate(sql);
		sql = "INSERT INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
				 "VALUES (1, 'This is a sample question 1', 'Sample answer 1 1', 'Sample answer 2 1', 'Sample answer 3 1', 'Correct answer 1' );";
				stmt.executeUpdate(sql);
		sql = "INSERT INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
				 "VALUES (2, 'This is a sample question 2', 'Sample answer 1 2', 'Sample answer 2 2', 'Sample answer 3 2', 'Correct answer 2' );";
				stmt.executeUpdate(sql);
		sql = "INSERT INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
				 "VALUES (3, 'This is a sample question 3', 'Sample answer 1 3', 'Sample answer 2 3', 'Sample answer 3 3', 'Correct answer 3' );";
				stmt.executeUpdate(sql);
		sql = "INSERT INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
				 "VALUES (4, 'This is a sample question 4', 'Sample answer 1 4', 'Sample answer 2 4', 'Sample answer 3 4', 'Correct answer 4' );";
				stmt.executeUpdate(sql);
		sql = "INSERT INTO MULTIPLE (ID,QUESTION,ANSWER1,ANSWER2,ANSWER3,CORRECT) " +
				 "VALUES (5, 'This is a sample question 5', 'Sample answer 1 5', 'Sample answer 2 5', 'Sample answer 3 5', 'Correct answer 5' );";
		stmt.executeUpdate(sql);
		stmt.close();
	}catch(Exception e) 
	{
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
		System.exit(0);	
	}
	System.out.println("Table created successfully.");
	}
}
