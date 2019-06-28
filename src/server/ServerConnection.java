package server;

import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

public interface ServerConnection {
	public boolean connect(String userName, String password) throws InterruptedException, ExecutionException;
	public boolean disconnect() throws InterruptedException, ExecutionException;
	public boolean sendRequest(String command) throws InterruptedException, ExecutionException;
	public Hashtable<String, String> getResponse() throws InterruptedException, ExecutionException;
}
