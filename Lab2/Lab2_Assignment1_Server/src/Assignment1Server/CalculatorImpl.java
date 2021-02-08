package Assignment1Server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Assignment1Interface.CalculatorIn;

public class CalculatorImpl extends UnicastRemoteObject implements CalculatorIn
{ //Defining the methods declared in the interface
	CalculatorImpl() throws RemoteException
	{
		super();
	}
	public double add(double a, double b)throws RemoteException
	{
		return(a+b);
	}
	public double sub(double a,double b)throws RemoteException
	{
		return(a-b);
	}
	public double mul(double a,double b)throws RemoteException
	{
		return(a*b);
	}
	public double div(double a,double b)throws RemoteException
	{
		return(a/b);
	}
}