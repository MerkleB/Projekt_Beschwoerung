package server;

import java.util.UUID;

public class ServerConnector implements ServerConnection {
	
	private static ServerConnector instance = null;
	
	private String serverIP;
	private int port;
	private UUID sessionID;
	
	public static ServerConnection getInstance() {
		if(instance == null) {
			instance = new ServerConnector();
			instance.serverIP = "127.0.0.1";
			instance.port = 1227;
			instance.sessionID = null;
		}
		return instance;
	}
	
	@Override
	public boolean connect() {
		return false;
	}

	@Override
	public void sendRequest(String command) {
		
	}

	@Override
	public String getResponse() {
		return null;
	}

	@Override
	public boolean disconnect() {
		// TODO Auto-generated method stub
		return false;
	}

}
