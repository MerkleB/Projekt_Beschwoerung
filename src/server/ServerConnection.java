package server;

public interface ServerConnection {
	public boolean connect(String userName, String password);
	public boolean disconnect();
	public void sendRequest(String command);
	public String getResponse();
}
