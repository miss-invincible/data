public class MyFrstThread
{	public static void main(String[] args)
	{
		MyFrstThread mft = new MyFrstThread();
		mft.go();
	}
	
	public void go()
	{	
		Runnable threadJob = new MyRunnable();
		Thread thread = new Thread(threadJob);
		thread.start();
		try{
		Thread.sleep(10);
		}catch(InterruptedException ex){}
		System.out.println("inside  main");
	}
}

class MyRunnable implements Runnable
{
	public void run()
	{	doMore();
		try{
		Thread.sleep(7);
		}catch(InterruptedException ex){}
	}
	
	public void doMore()
	{	System.out.println("welcome to world");
	}
	
}