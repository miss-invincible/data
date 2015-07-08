package check;
import java.io.*;

public class Tryy
{	public static void main(String[] args)
	{
		Tryy ty = new Tryy();
		ty.go();
	}
	
	public void go()
	{	try{
		BufferedWriter bf = new BufferedWriter(new FileWriter("trial.txt"));
		bf.write("done");
		bf.close();
		}catch(Exception ex){ex.printStackTrace();}
	}
}