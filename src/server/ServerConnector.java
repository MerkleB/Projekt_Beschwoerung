package server;

import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import user_interface.GameConsole;

public class ServerConnector implements ServerConnection {
	
	private static ServerConnector instance = null;
	
	private String serverIP;
	private int port;
	private UUID sessionID;
	private FutureTask<Hashtable<String, String>> futureTask;
	
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
	public boolean connect(String userName, String password) throws InterruptedException, ExecutionException {
		if(sessionID == null) {
			RequestHandler request = new RequestHandler("Login;"+userName+";"+password, serverIP, port);
			futureTask = new FutureTask<>(request);
			Thread thread = new Thread(futureTask);
			thread.start();
			while(!futureTask.isDone()) {
			}
			if(futureTask.isCancelled()) {
				return false;
			}else {
				Hashtable<String, String> response = futureTask.get();
				if(response.get("Code").equals("100")) {
					sessionID = UUID.fromString(response.get("Session"));
					GameConsole.getInstance().writeMessage("Login was successful");
					GameConsole.getInstance().writeMessage("Session "+sessionID+" started!");
					futureTask = null;
					return true;
				}else{
					GameConsole.getInstance().writeMessage(response.get("Code")+ ":" + response.get("Message"));
				}
				
			}
			
		}else GameConsole.getInstance().writeMessage("ServerConnector: Login refused. You're already logged in.");
		
		return false;
	}

	@Override
	public boolean sendRequest(String command) {
		if(sessionID == null) {
			return false;
		}
		return true;
	}

	@Override
	public Hashtable<String, String> getResponse() throws InterruptedException, ExecutionException {
		if(sessionID == null || futureTask == null) {
			return null;
		}else {
			return futureTask.get();
		}
	}

	@Override
	public boolean disconnect() throws InterruptedException, ExecutionException {
		if(sessionID != null) {
			RequestHandler request = new RequestHandler("Logout;"+sessionID, serverIP, port);
			futureTask = new FutureTask<>(request);
			Thread thread = new Thread(futureTask);
			thread.start();
			while(!futureTask.isDone()) {
			}
			if(futureTask.isCancelled()) {
				return false;
			}else {
				Hashtable<String, String> response = futureTask.get();
				if(response.get("Code").equals("100")) {
					sessionID = UUID.fromString(response.get("Session"));
					GameConsole.getInstance().writeMessage("Logout was successful");
					GameConsole.getInstance().writeMessage("Session "+sessionID+" ended!");
					futureTask = null;
					return true;
				}else {
					GameConsole.getInstance().writeMessage(response.get("Code")+ ":" + response.get("Message"));
				}
				
			}
			
		}
		return false;
	}

}
