package user_interface.components;

public class ButtonEvent {
	
	private String kind;
	private String source;
	
	public ButtonEvent(String kind, String source) {
		this.kind = kind;
		this.source = source;
	}
	
	public String kind() {
		return kind;
	}
	
	public String source() {
		return source;
	}
}
