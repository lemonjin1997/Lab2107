package Assignment1Server;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorService {
	public static void main(String[] args) {
		try {
			//Create a Calculator object
			CalculatorImpl calImpl =  new CalculatorImpl();
			//Locate the registry
			LocateRegistry.createRegistry(1099);
			//Bind the Calculator object
			Naming.rebind("rmi://localhost:1099/CalculatorService", calImpl);
			System.out.println("Server is ready");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
