import javax.swing.*;
import java.awt.*; //borderlayout lives here

public class LayoutBorder1
{
	public static void main(String[] args)
	{
		LayoutBorder1 lb1 = new LayoutBorder1();
		lb1.go();
	}

	public void go()
	{
		JFrame jfm = new JFrame();
		JButton jbtN = new JButton("north");
		JButton jbtS = new JButton("south");
		JButton jbtE = new JButton("east ho ho ho lo lo");
		JButton jbtW = new JButton("west i i i i i ");
		JButton jbtC = new JButton("center lo lo lo");
		Font bigFont = new Font("comic sans ms",Font.BOLD,30);
		jbtN.setFont(bigFont);
		jfm.getContentPane().add(BorderLayout.WEST,jbtW);
		jfm.getContentPane().add(BorderLayout.EAST,jbtE);
		jfm.getContentPane().add(BorderLayout.NORTH,jbtN);
		jfm.getContentPane().add(BorderLayout.SOUTH,jbtS);
		jfm.getContentPane().add(BorderLayout.CENTER,jbtC);
		
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);

	}
}
