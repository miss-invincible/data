import java.io.*;
import java.net.*;

public class DailyAdviceServer
{
	String[] adviceList = {"ahave fun","enjoy life","love yor mom","try trust someone even though u might get  cheated","be positive","smile","drink lots of water"};

	public void go()
	{
		try
		{
			ServerSocket serverSock = new ServerSocket(4242);

			while(true)
			{
				Socket sock = serverSock.accept();

				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				String advice = getAdvice();
				writer.println(advice);
				writer.close();
				System.out.println(advice);
			}
		}catch(IOException ex){ex.printStackTrace();}

	}//close go

	private String getAdvice()
	{
		int random = (int) (Math.random()*adviceList.length);
		return adviceList[random];
	}

	public static void main(String[] args)
	{
		DailyAdviceServer server = new DailyAdviceServer();
		server.go();
	}


}