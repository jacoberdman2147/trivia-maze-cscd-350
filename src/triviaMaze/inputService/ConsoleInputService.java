package triviaMaze.inputService;

import java.io.*;

/**
 * This class is an InputService which takes input from the console and passes
 * it through as an event which is then fired and handled.
 * 
 * @author Jacob Erdman, Randy Heckard
 * @deprecated
 */
public class ConsoleInputService implements ITmInputService {

	private BufferedReader in;

	/**
	 * Creates a new ConsoleInputService in which input is read directly from
	 * console
	 */
	public ConsoleInputService() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * Creates a new ConsoleInputService in which input is read directly from a file
	 * 
	 * @param readFile
	 *            The File object corresponding to the file with input instructions
	 *            to be handled. Those instructions should be on separate lines
	 */
	public ConsoleInputService(File readFile) {
		try {
			in = new BufferedReader(new FileReader(readFile));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}

	@Override
	public String getInput() {
		try {
			return in.readLine();
		} catch (IOException e) {
			System.out.println("An error occurred while reading the information");
			return "";
		}
	}
}
