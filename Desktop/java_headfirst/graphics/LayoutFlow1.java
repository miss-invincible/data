import javax.swing.*;
import java.awt.*;

public class LayoutFlow1
{
	public static void main(String[] args)
	{
		LayoutFlow1 lf1 = new LayoutFlow1();
		lf1.go();

	}

	public void go()
	{
		JFrame jfm = new JFrame();
		JPanel jpl = new JPanel();
		jpl.setBackground(Color.darkGray);
		JButton jbt1 = new JButton("hi there");
		JButton jbt2 = new JButton("cool boy yo");
		JButton jbt3 = new JButton("hi there");
		JButton jbt4 = new JButton("cool boy yo");
		JButton jbt5 = new JButton("ahaan ahaan");
		JButton jbt6 = new JButton("voila !!!!");
		JButton jbt7 = new JButton("yoooooooooooooo");
		JButton jbt8 = new JButton("uuuuuummmmmmmm");
		//jpl.add(jbt2);
		//jpl.add(jbt1); this wont work as jbt 1 and jbt 2 are jframes component
		jpl.add(jbt3);
		jpl.add(jbt4);
		jpl.add(jbt5);
		jpl.add(jbt6);
		jpl.add(jbt7);
		//jfm.getContentPane().add(BorderLayout.NORTH,jbt2);
		//jfm.getContentPane().add(BorderLayout.SOUTH,jbt1);
		
		//jfm.getContentPane().add(BorderLayout.WEST,jbt6);
		jfm.getContentPane().add(BorderLayout.EAST,jpl);//these two are eater of each other jo pehle ata h use front milta h baad wala hide ho jata h

		
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);

	}
}
