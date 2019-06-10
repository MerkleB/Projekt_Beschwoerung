package user_interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JPanel;

import user_interface.screens.Screen;

public class GamePanel extends JPanel implements WindowListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6438396292474169234L;
	private int clickCounter;
	private boolean gameIsFinished;
	private boolean paused;
	private int fps = 60;
	private int frameCount = 0;
	private GameFrame parent;
	private ManagesScreens screenManager;
	int lastDrawX, lastDrawY;
	private final double GAME_HERTZ = 30.0;
	
	public GamePanel(GameFrame frame) {
		parent = frame;
		try {
			screenManager = ScreenManager.getInstance(Toolkit.getDefaultToolkit().getScreenSize().width, 
														Toolkit.getDefaultToolkit().getScreenSize().height,
														this);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		frame.addMouseListener(screenManager);
		frame.addMouseMotionListener(screenManager);
		clickCounter = 0;
		gameIsFinished = false;
		setBackground(Color.BLACK);
		lastDrawX = 0;
		lastDrawY = 0;
		runGameLoop();
	}
	
	public void gameIsFinished() {
		gameIsFinished = true;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		gameIsFinished = true;
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {		
	}
	
	private void runGameLoop() {
		Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};
		loop.start();
	}
	
	private void gameLoop() {
	      //Calculate how many ns each frame should take for our target game hertz.
	      final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
	      //At the very most we will update the game this many times before a new render.
	      //If you're worried about visual hitches more than perfect timing, set this to 1.
	      final int MAX_UPDATES_BEFORE_RENDER = 5;
	      //We will need the last update time.
	      double lastUpdateTime = System.nanoTime();
	      //Store the last time we rendered.
	      double lastRenderTime = System.nanoTime();
	      
	      //If we are able to get as high as this FPS, don't render again.
	      final double TARGET_FPS = 60;
	      final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	      
	      //Simple way of finding FPS.
	      int lastSecondTime = (int) (lastUpdateTime / 1000000000);
	      
	      while(!gameIsFinished) {
	    	  double now = System.nanoTime();
	          int updateCount = 0;
	          
	          if (!paused)
	          {
	              //Do as many game updates as we need to, potentially playing catchup.
	             while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
	             {
	                updateGame();
	                lastUpdateTime += TIME_BETWEEN_UPDATES;
	                updateCount++;
	             }
	    
	             //If for some reason an update takes forever, we don't want to do an insane number of catchups.
	             //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
	             if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
	             {
	                lastUpdateTime = now - TIME_BETWEEN_UPDATES;
	             }
	          
	             drawGame();
	             lastRenderTime = now;
	          
	             //Update the frames we got.
	             int thisSecond = (int) (lastUpdateTime / 1000000000);
	             if (thisSecond > lastSecondTime)
	             {
	                System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
	                fps = frameCount;
	                frameCount = 0;
	                lastSecondTime = thisSecond;
	             }
	          
	             //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
	             while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
	             {
	                Thread.yield();
	             
	                //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
	                //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
	                //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
	                try {Thread.sleep(1);} catch(Exception e) {} 
	             
	                now = System.nanoTime();
	             }
	          }
	      }
	}
	
	private void updateGame() {
		
	}
	
	private void drawGame() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(getBackground());
		
		g.fillRect(0, 0, width, height);
		g.fillRect(5, 0, 75, 30);
		
		/*g.setColor(Color.RED);
		int drawX = 40;
		int drawY = 40;
		g.drawString("Die Maus wurde "+clickCounter+" mal geklickt", drawX, drawY);*/
		
		Screen screen = screenManager.getActiveScreen();
		screen.prepareGraphic((Graphics2D) g);
		
		g.setColor(Color.GREEN);
        g.drawString("FPS: " + fps, 5, 10);
        
        /*lastDrawX = drawX;
        lastDrawY = drawY;*/
        
        frameCount++;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clickCounter = clickCounter + 1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
