import java.rmi.*;
import java.rmi.server.*;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{
	public String sayHello(){
		return "Srever says,'Hey'";
	}
	
	public MyRemoteImpl() throws RemoteException{}
	
	public  static void main (String[] args)
	{
		try{
		MyRemote service = new MyRemoteImpl();
		Naming.rebind("Remote HEllo",service);
		
		}catch(Exception ex){ex.printStackTrace();}
	}
}