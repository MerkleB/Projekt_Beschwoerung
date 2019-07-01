package user_interface.screens;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import server.ServerConnector;
import user_interface.GameConsole;
import user_interface.ManagesScreens;
import user_interface.actions.Treatable;

public class StartScreenController implements ControlsScreen{
	
	public static final String BEENDEN = "Beenden";
	
	private ManagesScreens manager;
	private StartScreen screen;
	private boolean consolMode;
	private GameConsole console;
	
	/**
	 * Get new instance of StartScreenController
	 * @param manager
	 * @param screen: If screen is null we are in console mode
	 */
	public StartScreenController(ManagesScreens manager, StartScreen screen, boolean consoleMode) {
		this.manager = manager;
		this.screen = screen;
		this.consolMode = consoleMode;
		if(consoleMode) {
			console = GameConsole.getInstance();
			printProgramHeadLine();
		}
	}
	
	@Override
	public void processAction(Treatable action) {
		switch(action.getCommand()) {
		case "Exit": 
			try {
				console.writeMessage("Disconnect from Server");
				if(ServerConnector.getInstance().disconnect() == false) {
					console.writeMessage("Client was already disconnected.");
				}
				
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			manager.gameIsFinished();
			break;
		case "Login":
			String user = console.promptUser("Username:");
			String password = console.promptUser("Password:");
			try {
				ServerConnector.getInstance().connect(user, password);
			} catch (InterruptedException|ExecutionException e) {
				console.writeMessage(e.getMessage());
			}
			break;
	}
	}
	
	private void printProgramHeadLine(){
		System.out.println("Starting in console mode...");
		System.out.println("*******************************************************************************************************************************************************************************************************");
		System.out.println("++++                                                                ++++                                  +");
		System.out.println("+   +                                                              +    +                                 +");
		System.out.println("+    +                                                   +         +     +                                +");
		System.out.println("+     +                        +                         +         +      +                               +                                     ++  ++");
		System.out.println("+    +                                                   +         +     +                                + ");
		System.out.println("+   +                                                    +         +    +                                 +");
		System.out.println("++++     + ++      ++++        +     ++++       ++++   +++++       ++++       ++++      +++++     ++++    +   +++     +                     +    ++++     + ++   +        +   + ++++       +++++");
		System.out.println("+        ++  +    +     +      +    +    +     +    +    +         +   +     +    +    +     +   +    +   + +    +     +                   +    +    +    ++  +  +        +   ++    +     +     +");
		System.out.println("+        +        +     +      +   +      +    +         +         +    +   +      +   +        +         ++      +     +                 +     +    +    +      +        +   +      +   +       +");
		System.out.println("+        +        +     +      +   +           +         +         +     +  +      +    +       +         +       +      +       +       +      +    +    +      +        +   +       +   +     ++");
		System.out.println("+        +        +     +      +   ++++++++    +         +         +     +  ++++++++     ++++   +         +       +       +     + +     +       +    +    +       +       +   +       +    +++++ +");
		System.out.println("+        +        +     +      +   +           +         +         +    +   +                +  +         +       +        +   +   +   +        +    +    +        +      +   +       +          +");
		System.out.println("+        +        +     +      +    +     +    +    +    +         +   +     +     +   +     +   +    +   +       +         + +     + +         +    +    +         +    ++   +       +          +");
		System.out.println("+        +         ++++        +     +++++      ++++     +         ++++       +++++     +++++     ++++    +       +          +       +           ++++     +          ++++ +   +       +   +++++++");
		System.out.println("                               +                                                                                                                                                         +     +   ");
		System.out.println("                           +   +                                                                                                                                                          +   +  ");
		System.out.println("                            +++                                                                                                                                                            +++");
		System.out.println("*******************************************************************************************************************************************************************************************************");
	}


}
