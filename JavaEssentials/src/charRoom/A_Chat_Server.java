package charRoom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class A_Chat_Server {

	public static ArrayList<Socket> ConnectionArray = new ArrayList<>();
	public static ArrayList<String> CurrentUsers = new ArrayList<>();

	static Logger log;

	public static void main(String Args[]) {

		try {
			final int PORT = 444;
			System.out.println("Hello");
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Waiting for clients");

			while (true) {
				Socket socket = server.accept();
				ConnectionArray.add(socket);
				System.out.println("Client Connected");
				addUserName(socket);
				A_Chat_Server_Return chat = new A_Chat_Server_Return(socket);
				Thread t = new Thread(chat);
				
				t.start();

				
			}
			
			
		} catch (Exception e) {
			System.out.println("Error in server initialize");
			e.printStackTrace();
		}

	}

	private static void addUserName(Socket socket) throws IOException {
		
		Scanner input = new Scanner(socket.getInputStream());
		String userName = input.nextLine();
		CurrentUsers.add(userName);
		/*for (int i=0;i<A_Chat_Server.ConnectionArray.size();i++) {
			Socket temp_socket = A_Chat_Server.ConnectionArray.get(i-1);
			PrintWriter out = new PrintWriter(temp_socket.getOutputStream());
			out.println("#?!"+ CurrentUsers);
			out.flush();
		}*/
		
		
	}

}
