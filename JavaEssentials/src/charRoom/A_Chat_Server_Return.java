package charRoom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class A_Chat_Server_Return implements Runnable {

	private Socket socket;
	private String message;
	private Scanner input;
	private PrintWriter out;

	@Override
	public void run() {
		try {
			 input = new Scanner(socket.getInputStream());
			 out = new PrintWriter(socket.getOutputStream());

			while (true) {

				checkConnection();
				if (!input.hasNext()) {
					return;
				}
				message = input.nextLine();
				System.out.println("Client said: " + message);

			/*	for (int i = 0; i < A_Chat_Server.ConnectionArray.size(); i++) {
					Socket tempSocket = (Socket) A_Chat_Server.ConnectionArray.get(i - 1);
					PrintWriter pw = new PrintWriter(tempSocket.getOutputStream());
					pw.println(tempSocket.getLocalAddress().getHostName() + "disconnected");
					pw.flush();
					System.out.println((socket.getLocalAddress().getHostName() + "disconnected"));
				}*/
			}
		} catch (Exception e) {
			System.out.println("Error in server return");
		}

	}

	public A_Chat_Server_Return(Socket socket) {
		this.socket = socket;
	}

	public void checkConnection() throws IOException {

		if (!socket.isConnected()) {
			
			for (int i = 0; i < A_Chat_Server.ConnectionArray.size(); i++) {
				if(A_Chat_Server.ConnectionArray.get(i)==socket) {
					A_Chat_Server.ConnectionArray.remove(i);
				}
				
			}
			

			for (int i = 0; i < A_Chat_Server.ConnectionArray.size(); i++) {
				Socket tempSocket = (Socket) A_Chat_Server.ConnectionArray.get(i - 1);
				PrintWriter pw = new PrintWriter(tempSocket.getOutputStream());
				pw.println(tempSocket.getLocalAddress().getHostName() + "disconnected");
				pw.flush();
				System.out.println((socket.getLocalAddress().getHostName() + "disconnected"));

			}
		}

	}

}
