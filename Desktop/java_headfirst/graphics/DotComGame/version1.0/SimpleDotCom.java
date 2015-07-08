public class SimpleDotCom{
	int[] locationCells;
	int numOfHits = 0;
	int[] check= new int[3];  //hehee
	int c = 0;

	public void setLocations(int[] locs){
		locationCells = locs;
	}
	
	public String checkYourSelf(String stringGuess){
		int guess = Integer.parseInt(stringGuess);
		String result = "miss";
		for(int cell : locationCells)
		{	c=0;
			if(guess == cell)
			{
				
				for(int cell2 : check)
				{
					if(cell2 == guess)
					{
						c++;
					}
				}
				if(c == 0)
				{		check[numOfHits]=guess;
					
						result = "hit";
						numOfHits++;
						break;
			
				}
				
			}
		}
	
	if(numOfHits==locationCells.length){
		result = "kill";
	}
	
	System.out.println(result);
	return result;
	}
}
			