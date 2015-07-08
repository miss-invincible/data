import java.io.*;
import java.net.*;

public class ChatServerA
{
	public static void main(String[] args)
	{
		ChatServerA scs = new ChatServerA();
		scs.makeServer();
	}

	public void makeServer()
	{
		try
		{
			ServerSocket serverSock = new ServerSocket(4900);

			while(true)
			{
				Socket sock = serverSock.accept();
				InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
				BufferedReader reader = new BufferedReader(streamReader);

				String line = reader.readLine();
				recieveData(line);

			}
		}catch(Exception ex){ ex.printStackTrace(); }
	}

	public void recieveData(String str)
	{
		System.out.println(str);

	}
}