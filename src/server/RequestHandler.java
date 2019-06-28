package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class RequestHandler implements Callable<String>{
	
	private String ipAdress;
	private int port;
	private String request;
	
	public RequestHandler(String request, String ip, int port) {
		this.request = request;
		this.ipAdress = ip;
		this.port = port;
	}

	@Override
	public String call() throws Exception {
		System.out.println("RequestHandler: "+ Thread.currentThread());
		return RequestServer();
	}
	
	private String RequestServer() throws UnknownHostException, IOException {
		String response = "";
		
		Socket socket = new Socket(ipAdress, port);
		
		sendRequest(request, socket);
		response = readResponse(socket);
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
		return response;
	}
}
