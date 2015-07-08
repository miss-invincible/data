public class Wrapper
{
	Integer i = new Integer();
	int j;
	public static void main (String[] args) 
	{	Wrapper t = new Wrapper();
		t.go();
	}

	public void go()
	{	j=i;
		System.out.println(j);
		System.out.println(i);
	}
}