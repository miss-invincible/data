import java.awt.*;
import javax.swing.*;

public class LayoutBox1
{
	public static void main(String[] args)
	{
		LayoutBox1 lb1 = new LayoutBox1();
		lb1.go();
	}

	public void go()
	{
		JFrame jfm =new JFrame();
		JPanel jpl = new JPanel();
		JButton jbt1 = new JButton("hello shivangi......");
		JButton jbt2 = new JButton("up");
		JButton jbt3 = new JButton("no");

		jpl.setLayout(new BoxLayout(jpl,BoxLayout.Y_AXIS));

		
		jpl.add(jbt1);
		jpl.add(jbt2);
		jpl.add(jbt3);
		jpl.setBackground(Color.darkGray);
		
		jfm.getContentPane().add(BorderLayout.EAST,jpl);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);

	}

}