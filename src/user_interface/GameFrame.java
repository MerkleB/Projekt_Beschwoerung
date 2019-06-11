package user_interface;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4318976117693221310L;
	
	public GameFrame() {
		super();
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setUndecorated(true);
		GamePanel panel = new GamePanel(this);
		add(panel);
		addWindowListener(panel);
		//addMouseListener(panel);
	}
	
	public static void main(String[] args) {
		if(args.length > 0) {
			if(args[0].equals("--ui")) {
				GameFrame frame = new GameFrame();
				frame.setVisible(true);
			}
		}else {
			GameConsole console = new GameConsole();
		}
			
	}

}
