import java.awt.*;
import javax.swing.*;

public class PutPic
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		MyPicPanel mypic = new MyPicPanel();
		//JButton bt = new JButton();
		frame.getContentPane().add(mypic);
		//frame.getContentPane().add(bt);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(820,1000);
	}
}