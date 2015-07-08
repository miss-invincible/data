import java.util.ArrayList;

public class DotCom{
	private ArrayList<String> locationCells;

	public void setLocationCells(ArrayList<String> loc)
	{	locationCells=loc;
	}
	
	public String checkYourSelf(String userInput)
	{	String result = "miss";
		
		int index = locationCells.indexOf(userInput);
		if(index>=0)
		{	locationCells.remove(index);
			
			if(locationCells.size()==0)
			{	result = "kill";
				//break;

			}

			else
			{	result = "hit";
			}
		}
		System.out.println(result);
		return result;
	}
}