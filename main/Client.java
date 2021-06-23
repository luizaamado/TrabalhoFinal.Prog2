package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField msgText;
	private static JTextArea chatArea;
	private JButton btnSend;

	static Socket socket;
	static DataInputStream input;
	static DataOutputStream output;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String msgIn = "";		
		try {
			socket = new Socket("127.0.0.1", 10000);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			
			while (!msgIn.equals("exit")) {
				msgIn = input.readUTF();
				chatArea.setText(chatArea.getText().trim() + "\n" + msgIn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		chatArea = new JTextArea();
		chatArea.setBounds(10, 0, 414, 186);
		contentPane.add(chatArea);
		
		msgText = new JTextField();
		msgText.setBounds(10, 197, 302, 38);
		contentPane.add(msgText);
		msgText.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msgOut = "";
				
				try {
					msgOut = msgText.getText().trim();
					output.writeUTF("Client: " + msgOut);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		btnSend.setBounds(320, 197, 104, 38);
		contentPane.add(btnSend);
	}
}
