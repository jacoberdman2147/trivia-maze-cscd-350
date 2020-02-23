package triviaMaze.inputService;

import java.io.*;

import triviaMaze.eventService.TmEventService;

public class ConsoleInputService implements ITmInputService {

	BufferedReader in;
	
	public ConsoleInputService() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
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
