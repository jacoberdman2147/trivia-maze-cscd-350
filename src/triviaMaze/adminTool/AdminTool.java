package triviaMaze.adminTool;
import java.util.Scanner;

import triviaMaze.databaseService.DatabaseService;
public class AdminTool {
	public static void main(String[] args) {
		useTool();
		System.out.println("Thank you for using the Admin Tool! Have a great day!");
	}

	private static void useTool() {
		int choice;
		do {
			DatabaseService data = new DatabaseService();
			data.createTable("multiple");
			data.createTable("numberQuestions");
			Scanner s = new Scanner(System.in);
			// int type = getType(s); temporarily removed because we are only using
			// multiple.
			int type = 1;
			choice = getChoice(s);
			if (type == 1 && choice == 1)
				addMultiple(s, data);
			if (type == 1 && choice == 2)
				removeMultiple(s, data);
		} while (choice != 3);
	}

	private static void removeMultiple(Scanner s, DatabaseService data) {
		System.out.println("Which question would you like to remove out of the following questions.");
		data.displayQuestions();
		String dataToRemove = s.nextLine();
		data.removeMultiple(dataToRemove);
	}

	private static void addMultiple(Scanner s, DatabaseService data) {
		String question;
		String answer1;
		String answer2;
		String answer3;
		String correct;
		System.out.println("You have selected to add a question to Multiple choice! What would you like the question to be?");
		question = s.nextLine();
		System.out.println("What is the first answer choice?");
		answer1 = s.nextLine();
		System.out.println("And the next?");
		answer2 = s.nextLine();
		System.out.println("And the final distracting answer?");
		answer3 = s.nextLine();
		System.out.println("And what is the correct answer?");
		correct = s.nextLine();
		data.addQuestionMultiple(question, answer1, answer2, answer3, correct);
		System.out.println("Question successfully added!");
	}

	public static int getChoice(Scanner s) {
		int choice = 0;
		while (choice < 1 || choice > 3) {
			System.out.println("Would you like to\n1. Add a question\n2. Remove a question\n3. Quit");
			if (s.hasNextInt())
				choice = s.nextInt();
			else
				System.out.println("Please enter an interger.");
			s.nextLine();
		}
		return choice;
	}

	public static int getType(Scanner s) {
		int type = 0;
		while (type < 1 || type > 3) {
			System.out.println("Hello, and welcome to the admin tool! Which table would you like to interface with?\n1. Multiple choice\n2."
					+ " True/False\n3. Short answer");
			if (s.hasNextInt())
				type = s.nextInt();
			else
				System.out.println("Please enter an integer.");
			s.nextLine();
		}
		return type;
	}
}
