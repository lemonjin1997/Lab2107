package Assignement2_Client;

import java.awt.Color;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import Assignment1Interface.CalculatorIn;

public class GUI extends JFrame {
	JTextField field;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	JButton btn5;
	JButton btn6;
	JButton btn7;
	JButton btn8;
	JButton btn9;
	JButton btnAdd ;
	JButton btnSub;
	JButton btnMul;
	JButton btnDiv;
	JButton btnClear;
	JButton btn0;
	JButton btnQua;
	JPanel jp;
	CalculatorIn cal;
	
	public GUI(){
		try {
		cal = (CalculatorIn) Naming.lookup("rmi://localhost:1099/CalculatorService");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		jp = new JPanel();
		field = new JTextField("", 30);
		jp.add(field);
		btn1 = new JButton("1");
		btn2 = new JButton("2");
		btn3 = new JButton("3");
		btn4 = new JButton("4");
		btn5 = new JButton("5");
		btn6 = new JButton("6");
		btn7 = new JButton("7");
		btn8 = new JButton("8");
		btn9 = new JButton("9");
		btnAdd = new JButton("+");
		btnSub = new JButton("-");
		btnMul = new JButton("*");
		btnDiv = new JButton("/");
		btnClear = new JButton("C");
		btn0 = new JButton("0");
		btnQua = new JButton("=");
		
		btn1.setBounds(50, 50, 50, 50);
		btn2.setBounds(50, 50, 50, 50);
		btn3.setBounds(50, 50, 50, 50);
		btn4.setBounds(50, 50, 50, 50);
		btn5.setBounds(50, 50, 50, 50);
		btn6.setBounds(50, 50, 50, 50);
		btn7.setBounds(50, 50, 50, 50);
		btn8.setBounds(50, 50, 50, 50);
		btn9.setBounds(50, 50, 50, 50);
		btnAdd.setBounds(50, 50, 50, 50);
		btnSub.setBounds(50, 50, 50, 50);
		btnMul.setBounds(50, 50, 50, 50);
		btnDiv.setBounds(50, 50, 50, 50);
		btnClear.setBounds(50, 50, 50, 50);
		btn0.setBounds(50, 50, 50, 50);
		btnQua.setBounds(50, 50, 50, 50);
		
		setTitle("Calculator Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,420);
		setVisible(true);
		//jf.setLayout(null);
		getContentPane().setBackground(Color.black);
		btn1.setLocation(50, 50);
		btn2.setLocation(100, 50);
		btn3.setLocation(150, 50);
		btn4.setLocation(50, 100);
		btn5.setLocation(100, 100);
		btn6.setLocation(150, 100);
		btn7.setLocation(50, 150);
		btn8.setLocation(100, 150);
		btn9.setLocation(150, 150);
		
		btnAdd.setLocation(200, 50);
		btnSub.setLocation(200, 100);
		btnMul.setLocation(200, 150);
		btnDiv.setLocation(200, 200);
		btnClear.setLocation(50, 200);
		btn0.setLocation(100, 200);
		btnQua.setLocation(150, 200);
		
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
		add(btn6);
		add(btn7);
		add(btn8);
		add(btn9);
		add(btnAdd);
		add(btnSub);
		add(btnMul);
		add(btnDiv);
		add(btnClear);
		add(btn0);
		add(btnQua);
		add(jp);
		
		btn1.addActionListener(new ButtonListener());
		btn2.addActionListener(new ButtonListener());
		btn3.addActionListener(new ButtonListener());
		btn4.addActionListener(new ButtonListener());
		btn5.addActionListener(new ButtonListener());
		btn6.addActionListener(new ButtonListener());
		btn7.addActionListener(new ButtonListener());
		btn8.addActionListener(new ButtonListener());
		btn9.addActionListener(new ButtonListener());
		
		btnAdd.addActionListener(new ButtonListener());
		btnSub.addActionListener(new ButtonListener());
		btnMul.addActionListener(new ButtonListener());
		btnDiv.addActionListener(new ButtonListener());
		btnClear.addActionListener(new ButtonListener());
		btn0.addActionListener(new ButtonListener());
		btnQua.addActionListener(new ButtonListener());
		
	}
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btn1) {
				field.setText(field.getText() + "1");
			}
			if (e.getSource() == btn2) {
				field.setText(field.getText() + "2");
			}
			if (e.getSource() == btn3) {
				field.setText(field.getText() + "3");
			}
			if (e.getSource() == btn4) {
				field.setText(field.getText() + "4");
			}
			if (e.getSource() == btn5) {
				field.setText(field.getText() + "5");
			}
			if (e.getSource() == btn6) {
				field.setText(field.getText() + "6");
			}
			if (e.getSource() == btn7) {
				field.setText(field.getText() + "7");
			}
			if (e.getSource() == btn8) {
				field.setText(field.getText() + "8");
			}
			if (e.getSource() == btn9) {
				field.setText(field.getText() + "9");
			}
			if (e.getSource() == btn0) {
				field.setText(field.getText() + "0");
			}
			if (e.getSource() == btnAdd) {
				field.setText(field.getText() + "+");
			}
			if (e.getSource() == btnSub) {
				field.setText(field.getText() + "-");
			}
			if (e.getSource() == btnMul) {
				field.setText(field.getText() + "*");
			}
			if (e.getSource() == btnDiv) {
				field.setText(field.getText() + "/");
			}
			if (e.getSource() == btnClear) {
				field.setText("");
			}
			if (e.getSource() == btnQua) {
				String result = field.getText();
				double calResult = 0.0;
				
				try {
					if(result.contains("-")) {
						String[] num = result.split("\\-");
						double value1 = Double.parseDouble(num[0]); 
						double value2 = Double.parseDouble(num[1]);
						calResult = cal.sub(value1, value2);
					}
					if(result.contains("+")) {
						System.out.println(result);
						String[] num = result.split("\\+");
						double value1 = Double.parseDouble(num[0]); 
						double value2 = Double.parseDouble(num[1]);
						calResult = cal.add(value1, value2);
					}
					if(result.contains("*")) {
						String[] num = result.split("\\*");
						double value1 = Double.parseDouble(num[0]); 
						double value2 = Double.parseDouble(num[1]);
						calResult = cal.mul(value1, value2);
					}
					if(result.contains("/")) {
						String[] num = result.split("\\/");
						double value1 = Double.parseDouble(num[0]); 
						double value2 = Double.parseDouble(num[1]);
						calResult = cal.div(value1, value2);
					}
					field.setText(Double.toString(calResult));
				}catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
			}
			
		}
		
	}

}
