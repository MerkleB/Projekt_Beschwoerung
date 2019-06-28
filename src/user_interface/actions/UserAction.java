package user_interface.actions;

public class UserAction implements Treatable {

	private String command;
	
	public UserAction(String command) {
		this.command = command;
	}
	
	@Override
	public String getCommand() {
		return this.command;
	}

}
