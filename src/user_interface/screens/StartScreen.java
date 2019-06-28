package user_interface.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import user_interface.ConsoleScreenManager;
import user_interface.GameConsole;
import user_interface.ManagesScreens;
import user_interface.ScreenManager;
import user_interface.actions.UserAction;
import user_interface.components.Button;
import user_interface.components.ButtonEvent;
import user_interface.components.ButtonListener;
import user_interface.components.Component;

public class StartScreen implements Screen, ButtonListener {
	
	private static StartScreen instance;
	private int width;
	private int height;
	private ArrayList<Component> components;
	private Component activeComponent;
	private ManagesScreens manager;
	private StartScreenController controller;
	private boolean consoleMode;
	private static GameConsole CONSOLE;
	
	public static Screen getInstance(int windowWidth, int windowHeight,  ManagesScreens manager) throws IOException {
		if(instance == null) {
			instance = new StartScreen();
			//instance.backgroundImage = ImageIO.read(new File(backgroundURI));
			instance.width = windowWidth;
			instance.height = windowHeight;
			instance.components = new ArrayList<Component>();
			instance.initializeComponents();
			instance.activeComponent = null;
			instance.manager = manager;
			instance.controller = new StartScreenController(manager, instance, false);
			instance.consoleMode = false;
		}
		return instance;
	}
	
	public static Screen getInstance(ConsoleScreenManager manager) {
		if(instance == null) {
			instance = new StartScreen();
			instance.manager = manager;
			instance.components = new ArrayList<Component>();
			instance.initializeComponents();
			instance.controller = new StartScreenController(manager, instance, true);
			instance.consoleMode = true;
			CONSOLE = GameConsole.getInstance();
		}
		return instance;
	}
	
	private void initializeComponents() {
		Button button = new Button(instance.width/2-instance.width/8, 
				instance.height/2+instance.height/4, 
				2*instance.width/8, 
				instance.height/14, 
				"Exit");
		button.addButtonListener(instance);
		instance.components.add(button);
		
		button = new Button(instance.width/2-instance.width/8, 
				instance.height/2+instance.height/4-1*instance.height/14-instance.height/40, 
				2*instance.width/8, 
				instance.height/14, 
				"Login");
		button.addButtonListener(instance);
		instance.components.add(button);
	}
	
	public boolean isMouseOverComponent(MouseEvent e, Component comp) {
		if(!consoleMode) {
			boolean mouseOverComp = false;
			if(comp != null) {
				if(e.getX() > comp.getX() && e.getX() < (comp.getX() + comp.getWidth()) && e.getY() > comp.getY() && e.getY() < (comp.getY() + comp.getHeight())) 
				{
					mouseOverComp = true;
				}
			}
			return mouseOverComp;
		}
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!consoleMode) {
			activeComponent.mouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!consoleMode) {
			activeComponent.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!consoleMode) {
			activeComponent.mouseReleased(e);
		}
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
		if(!consoleMode) {
			if(isMouseOverComponent(e, activeComponent) == false) {
				if(activeComponent != null) {
					activeComponent.mouseExited(e);
					activeComponent = null;
				}
				for(int i=0; i<components.size(); i++) {
					if(isMouseOverComponent(e, components.get(i))) {
						activeComponent = components.get(i);
						activeComponent.mouseEntered(e);
					}
				}
			}
		}
	}

	@Override
	public Graphics2D prepareGraphic(Graphics2D graphic) {
		if(!consoleMode) {
			// graphic.drawImage(backgroundImage, 0, 0, width, height, null);
			graphic.setColor(new Color(127, 0, 0));
			graphic.fillRect(0, 0, width, height);
			graphic.setColor(new Color(64, 64, 64));
			graphic.drawRect(width/2-width/7, height/2-height/3, 2*width/7, 2*height/3);
			for(int i=0; i<components.size(); i++) {
				components.get(i).draw(graphic);		}
			return graphic;
		}
		return null;
	}
	
	@Override
	public void ButtonClicked(ButtonEvent e) {
		controller.processAction(new UserAction(e.source()));
	}

	@Override
	public void ButtonPressed(ButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ButtonReleased(ButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processConsoleCommand(String input) {
		if(consoleMode) {
			boolean validCommand = false;
			if(input.equalsIgnoreCase("help")) {
				CONSOLE.writeMessage(">Start screen==-");
				CONSOLE.writeMessage(">Available commands:");
				printCommands();
				validCommand = true;
			}else {
				for(int i=0; i<components.size(); i++) {
					if(components.get(i) instanceof Button) {
						Button button = (Button)components.get(i);
						if(button.getName().equals(input)) {
							validCommand = true;
							ButtonClicked(new ButtonEvent("Clicked", button.getName()));
						}
					}
				}
			}
			if(!validCommand) {
				CONSOLE.writeMessage("Start screen: Invalid command!");
				CONSOLE.writeMessage("Start screen: Invalid command!");
				printCommands();
			}
		}
	}
	
	private void printCommands() {
		for(int i=0; i<components.size(); i++) {
			if(components.get(i) instanceof Button) {
				Button button = (Button)components.get(i);
				CONSOLE.writeMessage("   "+button.getName());
			}
		}
	}

}
