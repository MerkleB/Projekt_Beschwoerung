package user_interface;

import javax.swing.event.MouseInputListener;

import user_interface.screens.Screen;

public interface ManagesScreens extends MouseInputListener {
	public Screen getActiveScreen();
	public void gameIsFinished();
}
