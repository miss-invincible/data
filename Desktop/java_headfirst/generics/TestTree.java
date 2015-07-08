import java.util.*;

public class TestTree
{
	public static void main(String[] args)
	{
		TestTree tt = new TestTree();
		tt.go();
	}
	
	public void go()
	{
		Book b1 = new Book("How Cats Work");
		Book b2 = new Book("Remix Your Body");
		Book b3 = new Book("Finding Emo");
		Book b4 = new Book("Finding Emo");
		Book book = new Book();
		
		TreeSet<Book> tree = new TreeSet<Book>(book);
		tree.add(b1);
		tree.add(b2);
		tree.add(b3);
		tree.add(b4);
		System.out.println(tree);
	}
	
	class Book implements Comparator<Book>
	{	String title;
		
		public Book(){}
		
		public Book(String t)
		{	title = t;
		}
		
		public String getTitle()
		{
			return title;
		}
		
		public String toString()
		{	
			return title;
		}
		
		public int compare(Book a, Book b)
		{	return a.getTitle().compareTo(b.getTitle());
		}
	}
	
	
}

