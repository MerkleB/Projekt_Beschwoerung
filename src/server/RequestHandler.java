package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.concurrent.Callable;

public class RequestHandler implements Callable<Hashtable<String, String>>{
	
	private String ipAdress;
	private int port;
	private String request;
	
	public RequestHandler(String request, String ip, int port) {
		this.request = request;
		this.ipAdress = ip;
		this.port = port;
	}

	@Override
	public Hashtable<String, String> call() throws Exception {
		System.out.println("RequestHandler: "+ Thread.currentThread());
		return RequestServer();
	}
	
	private Hashtable<String, String> RequestServer() throws UnknownHostException, IOException {
		
		
		Socket socket = new Socket(ipAdress, port);
		
		sendRequest(request, socket);
		String responseText = readResponse(socket);
		Hashtable<String, String> response = convertResponseStringToHashTable(responseText);
		socket.close();
		return response;
	}
	
	private void sendRequest(String request, Socket socket) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printWriter.print(request);
		printWriter.flush();
	}
	
	private String readResponse(Socket socket) throws IOException {
		String response = "";
		BufferedReader bufferedReader =
			      new BufferedReader(
			        new InputStreamReader(
			          socket.getInputStream()));
		char[] buffer = new char[100];
		while(!response.contains("$END$")) {
			int numberOfChars = bufferedReader.read(buffer, 0, 100);
			response = response + new String(buffer, 0, numberOfChars);
		}
		return response;
	}
	
	private Hashtable<String, String> convertResponseStringToHashTable(String responseString) {
		Hashtable<String, String> response = new Hashtable<String, String>();
		
		String[] keyValueList = responseString.split(";");
		
		for(int i=0; i<keyValueList.length; i++) {
			String keyValue[] = keyValueList[i].split("=");
			response.put(keyValue[0], keyValue[1]);
		}
		
		return response;
	}
}
