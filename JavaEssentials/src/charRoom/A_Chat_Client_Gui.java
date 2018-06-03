package charRoom;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class A_Chat_Client_Gui {
	
	private static A_Chat_Client chatClient;
	public static String userName = "Anonymous";
	public Socket socket;
	public Scanner input;
	public Scanner send = new Scanner(System.in);
	PrintWriter out;
	

	//GUI
	public static JFrame mainWindow = new JFrame();
	private static JButton b_about = new JButton();
	private static JButton b_connect = new JButton();
	private static JButton b_disconnect = new JButton();
	private static JButton b_help = new JButton();
	private static JButton b_send = new JButton();
	
	private static JLabel l_message = new JLabel("Message");
	public static JTextField tf_message = new JTextField(20);
	private static JLabel l_conversation = new JLabel();
	public static JTextArea ta_conversation = new JTextArea();
	private static JScrollPane sp_conversation = new JScrollPane();
	private static JLabel l_online = new JLabel();
	public static JList jl_online = new JList();
	private static JScrollPane sp_online = new JScrollPane();
	private static JLabel l_loggedInAs = new JLabel();
	private static JLabel l_loggedInAsBox = new JLabel();
	
	//Login 
	public static JFrame LoginWindow = new JFrame();
	public static JTextField tf_userNameBox = new JTextField(20);
	private static JButton b_enter = new JButton("ENTER");
	private static JLabel l_enterUserName = new JLabel("Enter UserName:");
	private static JPanel p_logIn = new JPanel();
	
	
	public static void main(String Args[]) {
		
		BuildMainWindow();
		Initialize();	
		
	}

	public static void Initialize() {
		b_send.setEnabled(false);
		b_connect.setEnabled(true);
		b_disconnect.setEnabled(false);
		
	}
	
	public static void buildLogInWindow() {
		LoginWindow.setTitle("What is your name?");
		LoginWindow.setSize(400, 100);
		LoginWindow.setLocation(250, 200);
		LoginWindow.setResizable(false);
		p_logIn = new JPanel();
		p_logIn.add(l_enterUserName);
		p_logIn.add(tf_userNameBox);
		p_logIn.add(b_enter);
		LoginWindow.add(p_logIn);
		login_action();
		LoginWindow.setVisible(true);
		
	}

	private static void BuildMainWindow() {
		mainWindow.setTitle(userName +"'s Chat Box" );
		mainWindow.setSize(450, 500);
		mainWindow.setLocation(220, 180);
		mainWindow.setResizable(false);
		configureMainWindow();
		MainWindow_Action();
		mainWindow.setVisible(true);
		
	}

	private static void configureMainWindow() {
		
		mainWindow.setBackground(new Color(255, 255, 255));
		mainWindow.setSize(500, 320);
		mainWindow.getContentPane().setLayout(null);
		
		b_send.setBackground(new Color(0, 0, 255));
		b_send.setForeground(new Color(0, 0, 255));
		b_send.setText("SEND");
		mainWindow.getContentPane().add(b_send);
		b_send.setBounds(250, 40, 81, 25);
		
		b_disconnect.setBackground(new Color(0, 0, 255));
		b_disconnect.setForeground(new Color(0, 0, 255));
		b_disconnect.setText("DISCONNECT");
		mainWindow.getContentPane().add(b_disconnect);
		b_disconnect.setBounds(10, 40, 110, 25);
		
		b_connect.setBackground(new Color(0, 0, 255));
		b_connect.setForeground(new Color(255, 0, 0));
		b_connect.setText("CONNECT");
		mainWindow.getContentPane().add(b_connect);
		b_connect.setBounds(130, 40, 110, 25);
		
		b_help.setBackground(new Color(0, 0, 255));
		b_help.setForeground(new Color(0, 0, 255));
		b_help.setText("HELP");
		mainWindow.getContentPane().add(b_help);
		b_help.setBounds(420, 40, 70, 25);
		
		b_about.setBackground(new Color(0, 0, 255));
		b_about.setForeground(new Color(0, 0, 255));
		b_about.setText("ABOUT");
		mainWindow.getContentPane().add(b_about);
		b_about.setBounds(340, 40, 75, 25);
		
		l_message.setText("Message: ");
		mainWindow.getContentPane().add(l_message);
		l_message.setBounds(10, 10, 60, 20);
		
		tf_message.setForeground(new Color(0,0,255));
		tf_message.requestFocus();
		mainWindow.getContentPane().add(tf_message);
		tf_message.setBounds(70,4,260,30);
		
		l_conversation.setHorizontalAlignment(SwingConstants.CENTER);
		l_conversation.setText("Conversation");
		mainWindow.getContentPane().add(l_conversation);
		l_conversation.setBounds(100, 70, 140, 16);
		
		ta_conversation.setColumns(20);
		ta_conversation.setFont(new Font("Arial", 0, 12));
		ta_conversation.setForeground(new Color(0, 0, 255));
		ta_conversation.setLineWrap(true);
		ta_conversation.setRows(5);
		ta_conversation.setEditable(false);
		
		sp_conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_conversation.setViewportView(ta_conversation);
		mainWindow.getContentPane().add(sp_conversation);
		sp_conversation.setBounds(10, 90, 330, 180);
		
		l_online.setHorizontalAlignment(SwingConstants.CENTER);
		l_online.setText("Currenty Online");
		l_online.setToolTipText("");
		mainWindow.getContentPane().add(l_online);
		l_online.setBounds(350, 70, 130, 16);
		
		//String [] Names = {"Harris","Niki"};
		
		jl_online.setForeground(new Color(0, 0, 255));
		//jl_online.setListData(Names);
		
		sp_online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_online.setViewportView(jl_online);
		mainWindow.getContentPane().add(sp_online);
		sp_online.setBounds(350, 90, 130, 180);
		
		l_loggedInAs.setFont(new Font("Arial", 0, 12));
		l_loggedInAs.setText("Currently logged in as");
		mainWindow.getContentPane().add(l_loggedInAs);
		l_loggedInAs.setBounds(348,0,140,15);
		
		l_loggedInAsBox.setFont(new Font("Arial", 0, 12));
		l_loggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
		l_loggedInAsBox.setForeground(new Color(255, 0, 0));
		l_loggedInAsBox.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		mainWindow.getContentPane().add(l_loggedInAsBox);
		l_loggedInAsBox.setBounds(340, 17, 150, 20);
		
		
	}
	
	public static void login_action() {
		b_enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				action_b_enter();
				
			}

			private void action_b_enter() {
				if(!tf_userNameBox.getText().equals("")) {
					userName = tf_userNameBox.getText().trim();
					l_loggedInAsBox.setText(userName);
					A_Chat_Server.CurrentUsers.add(userName);
					mainWindow.setTitle(userName + "'s chat box");
					LoginWindow.setVisible(false);
					b_send.setEnabled(true);
					b_disconnect.setEnabled(true);
					b_connect.setEnabled(false);
					connect();
				}else {
					JOptionPane.showMessageDialog(null, "Please Enter a Name");
				}
			}
		});
		
	}
	
	public static void connect() {
		
		try {
			final int port = 444;
			final String host = "localhost";
			Socket socket = new Socket(host, port);
			chatClient = new A_Chat_Client(socket);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println(userName);
			out.flush();
			Thread x = new Thread(chatClient);
			x.start();
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Server nor responding"	);
			System.exit(0);
		}
		
	}
	
	public static void MainWindow_Action() {
		
		b_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				action_b_send();
			}
		});
		
		b_disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				action_b_disconnect();
			}
		});
		
		b_connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				action_b_connect();
			}
		});
		
		
	}
	
	public static void action_b_send() {
		if(!tf_message.getText().equals("")) {
			chatClient.send(tf_message.getText());
			tf_message.requestFocus();
		}
	}
	public static void action_b_connect() {
		buildLogInWindow();
	}
	

	public static void action_b_disconnect() {
		try {
			chatClient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
