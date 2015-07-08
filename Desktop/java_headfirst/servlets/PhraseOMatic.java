public class PhraseOMatic 
{
	public static String makePhrase()
	{
		String[] wordListOne = { "24/7","hi","watsup","well done","come again"};
		String[] wordListTwo = {"cool","its working","nice","yeah"};
		String[] wordListThree = {"hmm","ok","bbi"};
		
		int oneLength = wordListOne.length;
		int twoLength = wordListTwo.length;
		int threeLength = wordListThree.length;
		
		int rand1 = (int) (Math.random() * oneLength);
		int rand2 = (int) (Math.random() * twoLength);
		int rand3 = (int) (Math.random() *threeLength);
		
		String phrase = wordListOne[rand1] + " " + wordListTwo[rand2] + " " + wordListThree[rand3];
		
		return ("What we need is a" + phrase);
	}
}