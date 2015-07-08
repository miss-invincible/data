import java.util.*;
import java.io.*;


public class OverloadingSortComparableType implements Comparable
{
	ArrayList<Songs> playlist;
	
	public static void main()
	{}
	
	public void createList()
	{
		playlist = new ArrayList<Songs>();
		
	}
	
	public class Songs
	{
		String title;
		String artist;
		int bmp;
		
		public class Title
		{	String tt;
			public Title(String ttl)
			{	tt= ttl;
			}
			public String getTt()
			{	return tt;
			}
		}
		
		public class 
		
		Songs(String intitle,String inartist,int inbmp)
		{
			title = intitle;
			artist = inartist;
			bmp = inbmp;
		}
		
		
	}
	
	public int compareTo()
	{}
	
	
	
	
}