package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChatClientServer extends JFrame {

	private JPanel contentPane;
	private JLabel lblWelcome;
	private JButton btnConnect;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClientServer chat = new ChatClientServer();
					chat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatClientServer() {
		setTitle("Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWelcome = new JLabel("Welcome to the Chat!");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWelcome.setBounds(73, 33, 184, 54);
		contentPane.add(lblWelcome);
		
		btnConnect = new JButton("Connect");
		btnConnect.setBounds(116, 113, 89, 23);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();

					Server server = new Server();
					Thread tServer = new Thread(server);
					tServer.start();
					
					Client client = new Client();
					Thread tClient = new Thread (client);
					tClient.start();
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		contentPane.add(btnConnect);
	}
}
