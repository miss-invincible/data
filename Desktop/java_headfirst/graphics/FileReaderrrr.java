import java.io.*;

public class FileReaderrrr
{
	public static void main(String[] args)
	{
		try
		{
			File myFile = new File("MyText.txt");
			FileReader filereader = new FileReader(myFile);

			BufferedReader reader = new BufferedReader(filereader);
			//to send data at same time without waiting for reader to get buffer to get full use:reader.flush(); similary writer.fush() in case of wrinting

			String line = null;
			while((line = reader.readLine()) != null)
			{
				System.out.println(line);
			}
			reader.close();
		}catch(Exception ex){ex.printStackTrace();}

	}
}