import javax.swing.*;
import java.awt.*;

public class BorderLayout1
{
	public static void main(String[] args)
	{	JFrame jfm = new JFrame();
		JButton jbt = new JButton();
		jfm.getContentPane().add(BorderLayout.SOUTH,jbt);	//all the five positions are:SOUTH,EAST,NORTH,WEST,CENTER all these are capitals sinc they are constants.

		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);


	}
}