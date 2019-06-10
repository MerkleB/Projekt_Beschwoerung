package user_interface.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import user_interface.ScreenManager;
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
	private ScreenManager manager;
	
	public static Screen getInstance(int windowWidth, int windowHeight, ScreenManager manager) throws IOException {
		if(instance == null) {
			instance = new StartScreen();
			//instance.backgroundImage = ImageIO.read(new File(backgroundURI));
			instance.width = windowWidth;
			instance.height = windowHeight;
			instance.components = new ArrayList<Component>();
			Button button = new Button(instance.width/2-instance.width/8, 
					instance.height/2+instance.height/4, 
					2*instance.width/8, 
					instance.height/14, 
					"Beenden");
			button.addButtonListener(instance);
			instance.components.add(button);
			instance.activeComponent = null;
			instance.manager = manager;
		}
		return instance;
	}
	
	public boolean isMouseOverComponent(MouseEvent e, Component comp) {
		boolean mouseOverComp = false;
		if(comp != null) {
			if(e.getX() > comp.getX() && e.getX() < (comp.getX() + comp.getWidth()) && e.getY() > comp.getY() && e.getY() < (comp.getY() + comp.getHeight())) 
			{
				mouseOverComp = true;
			}
		}
		return mouseOverComp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		activeComponent.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		activeComponent.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		activeComponent.mouseReleased(e);
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

	@Override
	public Graphics2D prepareGraphic(Graphics2D graphic) {
		// graphic.drawImage(backgroundImage, 0, 0, width, height, null);
		graphic.setColor(new Color(127, 0, 0));
		graphic.fillRect(0, 0, width, height);
		graphic.setColor(new Color(64, 64, 64));
		graphic.drawRect(width/2-width/7, height/2-height/3, 2*width/7, 2*height/3);
		for(int i=0; i<components.size(); i++) {
			components.get(i).draw(graphic);		}
		return graphic;
	}
	
	@Override
	public void ButtonClicked(ButtonEvent e) {
		switch(e.source()) {
			case "Beenden": 
				manager.gameIsFinished();
				System.exit(0);
				break;
		}
	}

	@Override
	public void ButtonPressed(ButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ButtonReleased(ButtonEvent e) {
		// TODO Auto-generated method stub
		
	}

}
