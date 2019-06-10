package user_interface.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import user_interface.GameFrame;
import user_interface.GamePanel;
import user_interface.ManagesScreens;
import user_interface.ScreenManager;
import user_interface.screens.Screen;
import user_interface.screens.StartScreen;

class ScreenManagerTest {

	@Test
	void testGetInstance() {
		ManagesScreens screen1;
		try {
			screen1 = ScreenManager.getInstance(10, 10, new GamePanel(new GameFrame()));
		
		if(screen1 == null) {
			fail("Screen1 must not be NULL!");
		}
		
		ManagesScreens screen2 = ScreenManager.getInstance();
		if(screen2 == null) {
			fail("Screen2 must not be NULL!");
		}
		
		if(screen1 != screen2) {
			fail("There should be only one instance of Screen Manager.");
		}
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testGetActiveScreen() {
		ManagesScreens screenM;
		try {
			screenM = ScreenManager.getInstance(10, 10, new GamePanel(new GameFrame()));
		Screen screen = screenM.getActiveScreen();
		if(screen == null) {
			fail("A Screen should have been retrieved!");
		}
		if(screen.getClass() != StartScreen.class) {
			fail("Active Screen should be the start screen");
		}
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
