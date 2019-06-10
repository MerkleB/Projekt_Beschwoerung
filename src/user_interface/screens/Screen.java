package user_interface.screens;

import java.awt.Graphics2D;

import javax.swing.event.MouseInputListener;

public interface Screen extends MouseInputListener{
	public Graphics2D prepareGraphic(Graphics2D graphic);
}
