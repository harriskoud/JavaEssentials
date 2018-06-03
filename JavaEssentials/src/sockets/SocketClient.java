
package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketClient {
	
	
	public static void main (String Args[]) throws IOException {
		
		SocketClient sclient = new SocketClient();
		sclient.run();
		
	}
	
	public void run() throws IOException {
		
		
		Socket socket = new Socket("localhost", 444);
		PrintStream ps = new PrintStream(socket.getOutputStream());
		ps.println("Hello to server from client");
		
		InputStreamReader ir = new InputStreamReader(socket.getInputStream());
		BufferedReader br = new BufferedReader(ir);
		String msg = br.readLine();
		System.out.println(msg);
		

		
	}
	

}