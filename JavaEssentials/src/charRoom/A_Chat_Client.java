package charRoom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class A_Chat_Client implements Runnable {

	public Socket socket;
	public Scanner input;
	public Scanner send = new Scanner(System.in);
	PrintWriter out;

	public A_Chat_Client(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			try {
				input = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream());
				out.flush();
				checkStream();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void checkStream() {
		while(true) {
			receive();
		}

	}

	public void receive() {
		
		if(input.hasNext()) {
			String message = input.nextLine();
			if(message.contains("#?!")) {
				String temp1 = message.substring(3);
				temp1 = temp1.replace("[", "");
				temp1 = temp1.replace("]", "");
				
				String [] CurrentUsers = temp1.split(", ");
				A_Chat_Client_Gui.jl_online.setListData(CurrentUsers);
				
			}else {
				A_Chat_Client_Gui.ta_conversation.append(message +"\n");
			}
		}
		
	}

	public void send(String text) {
		out.println(A_Chat_Client_Gui.userName +": "+ text);
		out.flush();
		//A_Chat_Client_Gui.ta_conversation.setText(text);
		A_Chat_Client_Gui.tf_message.setText("");

	}

	public void disconnect() throws IOException {
		out.println(A_Chat_Client_Gui.userName+" has disconnected.");
		out.flush();
		socket.close();
		JOptionPane.showMessageDialog(null,"You disconnected");
		System.exit(0);
		

	}

}
