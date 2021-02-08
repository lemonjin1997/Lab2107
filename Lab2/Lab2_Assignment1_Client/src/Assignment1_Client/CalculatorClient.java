package Assignment1_Client;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;

import Assignment1Interface.CalculatorIn;

public class CalculatorClient {
	public static void main(String[] args) {
		CalculatorIn cal;
		try {
			cal = (CalculatorIn) Naming.lookup("rmi://localhost:1099/CalculatorService");
			BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
			String result = reader.readLine();
			System.out.println(result);
			String[] num = result.split(" ");
			double value1 = Double.parseDouble(num[1]); 
			double value2 = Double.parseDouble(num[2]);
			double resultCal = 0.0;
			if (num[0].contentEquals("add")) {
				resultCal = cal.add(value1,value2);
			}
			if (num[0].contentEquals("sub")) {
				resultCal = cal.sub(value1,value2);
			}
			if (num[0].contentEquals("mul")) {
				resultCal = cal.mul(value1,value2);
			}
			if (num[0].contentEquals("div")) {
				resultCal = cal.div(value1,value2);
			}
			System.out.println("Result: " + resultCal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
