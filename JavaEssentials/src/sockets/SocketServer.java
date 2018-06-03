package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	
	
	public static void main (String Args[]) throws IOException {
		
		SocketServer sServer = new SocketServer();
		sServer.run();
		
	}
	
	public void run() throws IOException {
		ServerSocket servSocket = new ServerSocket(444);
		Socket socket = servSocket.accept();
		InputStreamReader ir = new InputStreamReader(socket.getInputStream());
		BufferedReader br = new BufferedReader(ir);
		
		String msg = br.readLine();
		
		if(msg!= null) {
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.println("Message received");
		}
		
	}
	

}
