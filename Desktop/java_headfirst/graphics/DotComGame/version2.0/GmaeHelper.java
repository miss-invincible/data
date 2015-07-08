import java.io.*;
public class GmaeHelper{
	public String getUserInput(String prompt){
		String inputLine = null;
		System.out.print(prompt+" ");
		try{
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
inputLine = is.readLine();
if(inputLine.length()==0)
return null;
}
catch(IOException e)
{	System.out.print("IOException:" + e );
	
}
return inputLine;
}
}