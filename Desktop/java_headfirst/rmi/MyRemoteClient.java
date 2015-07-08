import java.rmi.*;

public class MyRemoteClient{	
	public static void main(String[] args)
	{
		new MyRemoteClient().go();
	}
	
	public void go()
	{	try{
		MyRemote servie = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
		String s = servie.sayHello();
		
		System.out.println(s);
		
		}catch(Exception ex){ex.printStackTrace();}
	
	}
}