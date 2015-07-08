import java.util.ArrayList;
public class DotComGame
{	public static void main(String[] args)
	{
		int numOfGuesses = 0;
		GmaeHelper helper = new GmaeHelper();
		DotCom theDotCom = new DotCom();
		
		int randomNum = (int)(Math.random()*5);
	
		ArrayList<String> locations = new ArrayList<String>();
		locations.add(Integer.toString(randomNum));
		locations.add(Integer.toString(randomNum+1));
		locations.add(Integer.toString(randomNum+2));
			
	
		theDotCom.setLocationCells(locations);
		boolean isAlive = true;
		
		while(isAlive == true){
		
			String guess = helper.getUserInput("enter a number");
			String result = theDotCom.checkYourSelf(guess);
			numOfGuesses++;
			
			if(result.equals("kill")){
			isAlive=false;
			//System.out.println("hoi");
			System.out.println("you took "+numOfGuesses+" guesses");
			}

		}
		
	}
}