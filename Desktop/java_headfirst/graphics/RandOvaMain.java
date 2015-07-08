import java.awt.*;
import javax.swing.*;

public class RandOvaMain
{
	public static void main(String[] args)
	{
		JFrame jfm = new JFrame();
		RandomOval ro = new RandomOval();
		jfm.getContentPane().add(ro);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);
	}
}