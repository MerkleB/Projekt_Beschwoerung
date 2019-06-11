package user_interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GameConsole {
	private boolean closed;
	
	public GameConsole() {
		closed = false;
		printProgramHeadLine();
		waitForInput();
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
	
	private void waitForInput() {
		String input = "";
		Scanner inputScanner = new Scanner(System.in);
		while(closed == false) {
			input = inputScanner.nextLine();
			executeInput(input);
		}
		inputScanner.close();
	}
	
	private void executeInput(String input) {
		if(input.equalsIgnoreCase("exit")) {
			System.out.println("Exit program.");
			closed = true;
		}else {
			System.out.println("Typed: "+input);
		}
	}
	
}
