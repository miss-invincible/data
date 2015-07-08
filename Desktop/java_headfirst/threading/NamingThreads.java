public class NamingThreads implements Runnable
{	public static void main(String[] args)
	{	
		NamingThreads runner = new NamingThreads();
		Thread alpha = new Thread(runner);
		Thread beta = new Thread(runner);
		alpha.setName("Alpha thread");
		beta.setName("Beta thread");
		alpha.start();
		beta.start();
	}
	
	public void run()
	{
		for(int i=0;i<25;i++)
		{	
			String threadNames = Thread.currentThread().getName();
			System.out.println(threadNames + "is running");
		}
	}
}