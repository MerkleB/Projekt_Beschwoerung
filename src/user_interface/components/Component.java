package user_interface.components;

import java.awt.Graphics2D;

import javax.swing.event.MouseInputListener;

public interface Component extends MouseInputListener{
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public Graphics2D draw(Graphics2D graphic); 
}
