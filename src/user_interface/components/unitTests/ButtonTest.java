package user_interface.components.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user_interface.components.Button;

class ButtonTest {
	
	Button button;
	
	@BeforeEach
	void setUp() throws Exception {
		button = new Button(200, 200, 30, 40, "Beenden");
	}

	@Test
	void testGetX() {
		if(button.getX() != 200) {
			fail("X-Value is wrong");
		}
	}

	@Test
	void testGetY() {
		if(button.getY() != 200) {
			fail("Y-Value is wrong");
		}
	}

	@Test
	void testGetWidth() {
		if(button.getWidth() != 30) {
			fail("Width-Value is wrong");
		}
	}

	@Test
	void testGetHeight() {
		if(button.getHeight() != 40) {
			fail("Height-Value is wrong");
		}
	}
	
	@Test
	void testGetName() {
		if(button.getName().equals("Beenden") == false) {
			fail("X-Value is wrong");
		}
	}

}
