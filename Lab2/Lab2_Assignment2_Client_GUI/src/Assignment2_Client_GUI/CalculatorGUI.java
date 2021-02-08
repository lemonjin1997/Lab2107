package Assignment2_Client_GUI;
import java.awt.Color;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;


public class CalculatorGUI {
	
	public static void main(String[] args) {
		JFormattedTextField field; 
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();
		field = new JFormattedTextField("asfasfas");
		jp.add(field);
		
		
		
		JButton btn1 = new JButton("1");
		JButton btn2 = new JButton("2");
		JButton btn3 = new JButton("3");
		JButton btn4 = new JButton("4");
		JButton btn5 = new JButton("5");
		JButton btn6 = new JButton("6");
		JButton btn7 = new JButton("7");
		JButton btn8 = new JButton("8");
		JButton btn9 = new JButton("9");
		JButton btnAdd = new JButton("+");
		JButton btnSub = new JButton("-");
		JButton btnMul = new JButton("*");
		JButton btnDiv = new JButton("/");
		JButton btnClear = new JButton("C");
		JButton btn0 = new JButton("0");
		JButton btnQua = new JButton("=");
		
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
		
		jf.setTitle("Calculator Client");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(420,420);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.getContentPane().setBackground(Color.black);
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
		
		jf.add(btn1);
		jf.add(btn2);
		jf.add(btn3);
		jf.add(btn4);
		jf.add(btn5);
		jf.add(btn6);
		jf.add(btn7);
		jf.add(btn8);
		jf.add(btn9);
		jf.add(btnAdd);
		jf.add(btnSub);
		jf.add(btnMul);
		jf.add(btnDiv);
		jf.add(btnClear);
		jf.add(btn0);
		jf.add(btnQua);
		jf.add(field);
		
	}

}
