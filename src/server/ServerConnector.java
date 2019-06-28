package server;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import user_interface.GameConsole;

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
	public boolean connect(String userName, String password) {
		if(sessionID == null) {
			RequestHandler request = new RequestHandler("Login;"+userName+";"+password, serverIP, port);
			FutureTask<String> ft = new FutureTask<>(request);
			Thread thread = new Thread(ft);
			thread.start();
			while(!ft.isDone()) {
			}
			try {
				String[] response = ft.get().split(";");
				if(response[0].equals("SUCCESS")) {
					sessionID = UUID.fromString(response[1]);
					return true;
				}else return false;
			} catch (InterruptedException | ExecutionException e) {
				System.out.println("ServerConnector");
				System.out.println(e.getMessage());
			}
		}
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
