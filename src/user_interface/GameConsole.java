package user_interface;

import java.util.Scanner;

import user_interface.actions.UserAction;
import user_interface.screens.ControlsScreen;
import user_interface.screens.Screen;

public class GameConsole implements Runnable{
	
	private static GameConsole instance;
	
	private boolean closed;
	private boolean paused;
	private Scanner inputScanner;
	private ManagesScreens screenManager;
	
	public static GameConsole getInstance() {
		if(instance == null) {
			instance = new GameConsole();
			instance.closed = false;
			instance.paused = false;
			instance.screenManager = ConsoleScreenManager.getInstance();
			instance.inputScanner = new Scanner(System.in);
		}
		return instance;
	}
	
	private void waitForInput() {
		String input = "";
		//Scanner inputScanner = new Scanner(System.in);
		input = inputScanner.nextLine();
		//inputScanner.close();
		executeInput(input);
	}
	
	private void executeInput(String input) {
		((ConsoleScreenManager) screenManager).routeInputToActiveScreen(input);
		waitForInput();
	}

	@Override
	public void run() {
		waitForInput();
	}

	public void gameIsFinished() {
		inputScanner.close();
		closed = true;
		System.out.println("Close application");
		System.exit(0);
	}
	
	public String promptUser(String message) {
		String input = "";
		System.out.println(message);
		//Scanner inputScanner = new Scanner(System.in);
		input = inputScanner.nextLine();
		//inputScanner.close();
		return input;		
	}
	
	public void writeMessage(String message) {
		System.out.println(message);
	}
	
}
