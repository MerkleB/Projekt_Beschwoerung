package user_interface.screens;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import user_interface.GameConsole;
import user_interface.ManagesScreens;
import user_interface.actions.Treatable;

public class StartScreenController implements ControlsScreen{
	
	public static final String BEENDEN = "Beenden";
	
	private ManagesScreens manager;
	private StartScreen screen;
	private boolean consolMode;
	
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
			printProgramHeadLine();
		}
	}
	
	@Override
	public void processAction(Treatable action) {
		switch(action.getCommand()) {
		case "Exit": 
			manager.gameIsFinished();
			break;
		case "Login":
			String user = GameConsole.getInstance().promptUser("Username:");
			GameConsole.getInstance().writeMessage("Typed:"+user);
			String password = GameConsole.getInstance().promptUser("Password:");
			GameConsole.getInstance().writeMessage("Typed:"+password);
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
