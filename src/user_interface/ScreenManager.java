package user_interface;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import user_interface.components.ButtonEvent;
import user_interface.components.ButtonListener;
import user_interface.screens.Screen;
import user_interface.screens.StartScreen;

public class ScreenManager implements ManagesScreens{

	private static ScreenManager instance;
	private Screen activeScreen;
	private Screen lastScreen;
	private int screenWidth;
	private int screenHeight;
	private ArrayList<Screen> screens;
	private GamePanel panel;
	
	public static ManagesScreens getInstance(int windowWidth, int windowHeight, GamePanel panel) throws IOException {
		if(instance == null) {
			instance = new ScreenManager();
			instance.screenWidth = windowWidth;
			instance.screenHeight = windowHeight;
			instance.screens = new ArrayList<Screen>();
			instance.screens.add(StartScreen.getInstance(instance.screenWidth, instance.screenHeight, instance));
			instance.activeScreen = instance.screens.get(0);
			instance.lastScreen = null;
			instance.panel = panel;
		}
		return instance;
	}
	
	public static ManagesScreens getInstance() {
		return instance;
	}
	
	public void gameIsFinished() {
		panel.gameIsFinished();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		activeScreen.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		activeScreen.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		activeScreen.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		activeScreen.mouseEntered(e);		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		activeScreen.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		activeScreen.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		activeScreen.mouseMoved(e);
	}

	@Override
	public Screen getActiveScreen() {
		return activeScreen;
	}

}
