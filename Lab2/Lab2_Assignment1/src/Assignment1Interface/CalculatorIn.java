package Assignment1Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculatorIn extends Remote
{ //Declaring the methods to be implemented
	double add(double a,double b) throws RemoteException;
	double sub(double a,double b) throws RemoteException;
	double mul(double a,double b) throws RemoteException;
	double div(double a,double b) throws RemoteException;
}