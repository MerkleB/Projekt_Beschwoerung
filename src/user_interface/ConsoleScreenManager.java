package user_interface;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import user_interface.screens.Screen;
import user_interface.screens.StartScreen;

public class ConsoleScreenManager implements ManagesScreens {
	
	private static ConsoleScreenManager instance;
	private Screen activeScreen;
	private Screen lastScreen;
	private ArrayList<Screen> screens;
	
	public static ManagesScreens getInstance(){
		if(instance == null) {
			instance = new ConsoleScreenManager();
			instance.lastScreen = null;
			instance.activeScreen = StartScreen.getInstance(instance);
			instance.screens = new ArrayList<Screen>();
			instance.screens.add(instance.activeScreen);
		}
		return instance;
	}
	
	public void routeInputToActiveScreen(String input) {
		activeScreen.processConsoleCommand(input);
	}
	
	@Override
	public void gameIsFinished() {
		GameConsole.getInstance().gameIsFinished();
	}
	
	@Override
	public Screen getActiveScreen() {
		return activeScreen;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
