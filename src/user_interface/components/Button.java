package user_interface.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Button implements Component {
	
	private static final Color defaultColor = new Color(128, 128, 128);
	private static final Color activeColor = new Color(124, 85, 0);
	private static final Color textColor = new Color(0,0,0);
	private int x;
	private int y;
	private int width;
	private int height;
	private Color currentColor;
	private String name;
	private boolean isActive;
	private ArrayList<ButtonListener> buttonListeners;
	
	public Button(int x, int y, int width, int heigth, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = heigth;
		this.name = name;
		this.isActive = false;
		currentColor = defaultColor;
		buttonListeners = new ArrayList<ButtonListener>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addButtonListener(ButtonListener bl) {
		buttonListeners.add(bl);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0; i<buttonListeners.size(); i++) {
			buttonListeners.get(i).ButtonClicked(new ButtonEvent("Clicked", name));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(int i=0; i<buttonListeners.size(); i++) {
			buttonListeners.get(i).ButtonPressed(new ButtonEvent("Pressed", name));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(int i=0; i<buttonListeners.size(); i++) {
			buttonListeners.get(i).ButtonReleased(new ButtonEvent("Released", name));
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		isActive = true;
		currentColor = activeColor;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		isActive = false;
		currentColor = defaultColor;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Graphics2D draw(Graphics2D graphic) {
		graphic.setColor(currentColor);
		graphic.setFont(new JLabel().getFont());
		graphic.fillRoundRect(x, y, width, height, 10, 10);
		int textWidth = getNameWidth(graphic);
		int textHeight = getNameHeight(graphic);
		graphic.setColor(textColor);
		graphic.drawString(name, (x+x+width)/2 - textWidth/2, (y+y+height)/2 - textHeight/2);
		return graphic;
	}
	
	private int getNameWidth(Graphics g) {
		return g.getFontMetrics().stringWidth(name);
	}
	
	private int getNameHeight(Graphics2D g) {
		FontRenderContext frc = g.getFontRenderContext();
        GlyphVector gv = g.getFont().createGlyphVector(frc, name);
        return gv.getPixelBounds(null, x, y).height;
		//return g.getFontMetrics().getHeight();
	}

}
