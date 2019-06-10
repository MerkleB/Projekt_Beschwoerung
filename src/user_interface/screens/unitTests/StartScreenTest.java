package user_interface.screens.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

import org.junit.jupiter.api.Test;

import user_interface.GameFrame;
import user_interface.GamePanel;
import user_interface.ScreenManager;
import user_interface.screens.Screen;
import user_interface.screens.StartScreen;

class StartScreenTest {

	@Test
	void testGetInstance() {
		Screen screen1 = null;
		GameFrame frame = new GameFrame();
		GamePanel panel = new GamePanel(frame);
		try {
			screen1 = StartScreen.getInstance(10, 10, (ScreenManager)ScreenManager.getInstance(10, 10, panel));
			
			if(screen1 == null) {
				fail("Screen 1 must not be null.");
			}
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		
		Screen screen2 = null;
		try {
			screen2 = StartScreen.getInstance(10, 10, (ScreenManager)ScreenManager.getInstance(10, 10, panel));
			
			if(screen2 == null) {
				fail("Screen 2 must not be null.");
			}
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		
		if(screen1 != screen2) {
			fail("Always the same instance should be retrieved.");
		}
	}

}
