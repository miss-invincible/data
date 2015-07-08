public class SimpleDotComGame
{	public static void main(String[] args)
	{
		int numOfGuesses = 0;
		GmaeHelper helper = new GmaeHelper();
		SimpleDotCom theDotCom = new SimpleDotCom();
		
		int randomNum = (int)(Math.random()*5);
		
		int[] locations = {randomNum,randomNum+1,randomNum+2};
		
		theDotCom.setLocations(locations);
		boolean isAlive = true;
		
		while(isAlive == true){
		
			String guess = helper.getUserInput("enter a number");
			String result = theDotCom.checkYourSelf(guess);
			numOfGuesses++;
			
			if(result.equals("kill")){
			isAlive=false;
			
			System.out.println("you took "+numOfGuesses+" guesses");
			}

		}
		
	}
}