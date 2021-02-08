package Assignment1_Group;

import java.awt.Color;
import java.awt.*; 
import java.awt.event.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.*;



public class GUI_GroupChat extends JFrame{
	JLabel userNameLbl;
	JLabel createGroupLbl;
	JLabel joinGroupLbl;
	JLabel messageLbl;
	
	JTextField userNameTxt;
	JTextField createGroupTxt;
	JTextField joinGroupTxt;
	JTextField messageTxt;
	
	JTextArea textArea;
	
	JButton userNameBtn;
	JButton createGroupBtn;
	JButton joinGroupBtn;
	JButton sendBtn;
	
	JPanel jp;
	JPanel userName;
	JPanel createGroup;
	JPanel joinGroup;
	JPanel messageHis;
	JPanel message;
	
	
	String name;
	HashMap<String, String> groupName;
	
	
	MulticastSocket multicastSocket = null;
	InetAddress multicastGroup = null;
	
	MulticastSocket centreSocket = null;
	InetAddress centreGroup = null;
	
	public GUI_GroupChat() {
		
		try {
			centreGroup = InetAddress.getByName("228.1.1.1");
			centreSocket = new MulticastSocket(6789);
			centreSocket.joinGroup(centreGroup);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		groupName = new HashMap<String, String>();
		setTitle("Group Chat App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,420);
		setVisible(true);
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		
		jp = new JPanel();
		userName = new JPanel();
		createGroup = new JPanel();
		joinGroup = new JPanel();
		messageHis = new JPanel();
		message = new JPanel();
		
		userNameLbl = new JLabel("User Name");
		userNameTxt = new JTextField(10);
		userNameBtn = new JButton("Update");
		userName.add(userNameLbl);
		userName.add(userNameTxt);
		userName.add(userNameBtn);
		jp.add(userName);
		
		createGroupLbl = new JLabel("Create Group");
		createGroupTxt = new JTextField(10);
		createGroupBtn = new JButton("Create");
		createGroup.add(createGroupLbl);
		createGroup.add(createGroupTxt);
		createGroup.add(createGroupBtn);
		jp.add(createGroup);

		joinGroupLbl = new JLabel("Join Group");
		joinGroupTxt = new JTextField(10);
		joinGroupBtn = new JButton("Join");
		joinGroup.add(joinGroupLbl);
		joinGroup.add(joinGroupTxt);
		joinGroup.add(joinGroupBtn);
		jp.add(joinGroup);
		
		textArea = new JTextArea(10, 20);
		messageHis.add(textArea);
		jp.add(messageHis);
		
		messageLbl = new JLabel("Message");
		messageTxt = new JTextField(10);
		sendBtn = new JButton("Send");
		message.add(messageLbl);
		message.add(messageTxt);
		message.add(sendBtn);
		jp.add(message);
		
		add(jp);
		
		try {
			centreGroup = InetAddress.getByName("228.1.1.1");
			centreSocket = new MulticastSocket(6789);
			centreSocket.joinGroup(centreGroup);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					while(true) {
						byte receivedData[] = new byte[1000];
						DatagramPacket dgpReceived = new DatagramPacket(receivedData, receivedData.length);
						centreSocket.receive(dgpReceived);
						receivedData =  dgpReceived.getData();
						int length = dgpReceived.getLength();
						String msg = new String(receivedData, 0, length);
						
						String[] commandMsg = msg.split(" ");
						//textArea.append(msg);
						if(commandMsg[0].matches("add")) {
							groupName.put(commandMsg[1], commandMsg[2]);
							textArea.append("Room name: " + commandMsg[1] + "\tIP: " + commandMsg[2] + "\n");
						}
						if(commandMsg[0].contains("search")) {
							
								msg = groupName.get(commandMsg[1]);
								byte[] buf = msg.getBytes();
								DatagramPacket dgpSend = new DatagramPacket(buf, buf.length, centreGroup, 6789);
								centreSocket.send(dgpSend);
								textArea.append("searching for: " + commandMsg[1] + "\n");
							
						}
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}).start();
		
		
		userNameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = userNameTxt.getText();
			}
			});
		createGroupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
				String msg = "add " + createGroupTxt.getText() + " 228.5.6." + groupName.size()+1;
				groupName.put(joinGroupTxt.getText(), "228.5.6." + groupName.size()+1);
				
				byte buf[] = msg.getBytes();
				
				DatagramPacket dgpSend = new DatagramPacket(buf, buf.length, centreGroup, 6789);
				centreSocket.send(dgpSend);
				
				 } catch (Exception ex) {
					 ex.printStackTrace();
				 }
			}
			});
		joinGroupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					if(groupName.get(joinGroupTxt.getText()) != null) {
						// request for IP address
						try {
							centreGroup = InetAddress.getByName("228.1.1.1");
							centreSocket = new MulticastSocket(6789);
							centreSocket.joinGroup(centreGroup);
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						String msg = "search " + joinGroupTxt.getText();
						byte searchBuf[] = msg.getBytes();
						DatagramPacket dgpSend = new DatagramPacket(searchBuf, searchBuf.length, centreGroup, 6789);
						centreSocket.send(dgpSend);
						
						byte receivedData[] = new byte[1000];
						DatagramPacket dgpReceived = new DatagramPacket(receivedData, receivedData.length);
						centreSocket.receive(dgpReceived);
						centreSocket.receive(dgpReceived);
						receivedData =  dgpReceived.getData();
						int length = dgpReceived.getLength();
						String receiveMsg = new String(receivedData, 0, length);

						
						multicastGroup = InetAddress.getByName(receiveMsg);
						multicastSocket = new MulticastSocket(6789);
						multicastSocket.joinGroup(multicastGroup);
						textArea.append("Joined room: " + joinGroupTxt.getText() + "\n Room's IP address:" + receiveMsg);						
						new Thread(new Runnable() {
							@Override
							public void run() {
								
								while (true) {
									try {
										byte receivedData[] = new byte[1000];
										DatagramPacket dgpReceived = new DatagramPacket(receivedData, receivedData.length);
										multicastSocket.receive(dgpReceived);
										receivedData =  dgpReceived.getData();
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
					}
				 }catch (Exception ex) {
						ex.printStackTrace();
					}
				 
			}
			});
		
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg = messageTxt.getText();
					msg = "\n" + name + ": " + msg;
					byte[] buf = msg.getBytes();
					DatagramPacket dgpSend = new DatagramPacket(buf, buf.length, multicastGroup, 6789);
					multicastSocket.send(dgpSend);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		);
		
	}
}
