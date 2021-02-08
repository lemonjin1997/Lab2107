package Exercise1;


import java.awt.event.*;
import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.*;

public class GroupChatApp extends JFrame {
	JLabel lblProcessID;
	JLabel lblProcessIdValue;
	JLabel lblGroupIp;
	JTextField txtGroupIp;
	JButton btnJoin;
	JButton btnLeave;
	JTextArea textArea;
	JLabel message;
	JTextField textField;
	JButton btnSend;
	
	JPanel jp;
	JPanel jpTop;
	JPanel jpIP;
	JPanel jpTextArea;
	JPanel jpSend;
	MulticastSocket multicastSocket = null;
	InetAddress multicastGroup = null;
	
	public GroupChatApp() {
		
		setTitle("Group Chat App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,420);
		setVisible(true);
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		
		
		
		jpTop = new JPanel();
		lblProcessID = new JLabel("Process ID: ");
		lblProcessIdValue = new JLabel("Process ID Value");
		lblProcessIdValue.setText(ManagementFactory.getRuntimeMXBean().getName());
		jpTop.add(lblProcessID);
		jpTop.add(lblProcessIdValue);
		jp.add(jpTop);
		
		jpIP = new JPanel();
		lblGroupIp = new JLabel("Group IP");
		txtGroupIp = new JTextField("228.5.6.7");
		btnJoin = new JButton("Join");
		btnLeave = new JButton("Leave");
		btnLeave.setEnabled(false);
		jpIP.add(lblGroupIp);
		jpIP.add(txtGroupIp);
		jpIP.add(btnJoin);
		jpIP.add(btnLeave);
		jp.add(jpIP);
		
		jpTextArea = new JPanel();
		textArea = new JTextArea(10,20);
		jpTextArea.add(textArea);
		jp.add(jpTextArea);
		
		jpSend = new JPanel();
		message = new JLabel("Message");
		textField = new JTextField(10);
		btnSend = new JButton("Send");
		btnSend.setEnabled(false);
		jpSend.add(message);
		jpSend.add(textField);
		jpSend.add(btnSend);
		jp.add(jpSend);
		
		jp.setVisible(true);
		add(jp);
		
		
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					multicastGroup = InetAddress.getByName(txtGroupIp.getText());
					multicastSocket = new MulticastSocket(6789);
					multicastSocket.joinGroup(multicastGroup);
					String message = lblProcessIdValue.getText() + " joined";
					byte[] buf = message .getBytes();
					DatagramPacket dgpConnected = new DatagramPacket(buf, buf.length, multicastGroup, 6789);
					multicastSocket.send(dgpConnected);
					new Thread(new Runnable() {
						@Override
						public void run() {
							byte buf1[] = new byte[1000];
							DatagramPacket dgpReceived = new DatagramPacket(buf1, buf1.length);
							while (true) {
								try {
									multicastSocket.receive(dgpReceived);
									byte[] receivedData =  dgpReceived.getData();
									int length = dgpReceived.getLength();
									String msg = new String(receivedData, 0, length);
									textArea.append(msg + "\n");
								}
								catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
							}
						}
					}).start();
					btnJoin.setEnabled(false);
					btnSend.setEnabled(true);
					btnLeave.setEnabled(true);
					
				}
					catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				
			}
		});
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msg = lblProcessIdValue.getText() + ": is leaving";
					byte[] buf = msg.getBytes();
					DatagramPacket dgpSend =
							new DatagramPacket(buf, buf.length, multicastGroup, 6789);
					multicastSocket.send(dgpSend);
					multicastSocket.leaveGroup(multicastGroup);
					btnJoin.setEnabled(true);
					btnSend.setEnabled(false);
					btnLeave.setEnabled(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg = textField.getText();
					msg = lblProcessIdValue.getText() + ": " + msg;
					byte[] buf = msg.getBytes();
					DatagramPacket dgpSend =
							new DatagramPacket(buf, buf.length, multicastGroup, 6789);
					multicastSocket.send(dgpSend);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}
	
	
}
