import javax.swing.*;
import java.awt.*;

public class Graphics2d_1main
{	public static void main(String[] args)
	{	JFrame jfm = new JFrame();

		Graphics2d_1 gf = new Graphics2d_1();
		jfm.getContentPane().add(gf);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);

	}
	
}