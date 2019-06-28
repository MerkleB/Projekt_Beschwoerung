package server;

public interface ServerConnection {
	public boolean connect();
	public boolean disconnect();
	public void sendRequest(String command);
	public String getResponse();
}
