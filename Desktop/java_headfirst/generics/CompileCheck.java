import java.util.*;

public class CompileCheck
{

	public static void main()
	{
		CompileCheck cc = new CompileCheck();
		cc.go();
		
	}
	
	public void go()
	{
		ArrayList<Objects> obj = new ArrayList<Objects>();
		List<Objects> o2 = obj;
		//List<Objects> o3 = new List<String>(); creates error
	}
}