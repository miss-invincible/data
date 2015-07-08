import java.awt.*;
import javax.swing.*;

public class MyDrawPanel extends JPanel  //making my own customised widget. it can be added to JFrame just like any other widgets.
{

	public void paintComponent(Graphics g)	//we never call this method.system call it and says here is a surface to paint of the type Graphics.
	{
		g.setColor(Color.orange);
		g.fillRect(20,50,100,100);	//now g acts like a surface and we tells paintComponet the color and shace to which it is supposed to be painted.
		g.setColor(Color.blue);
		g.fillRect(100,50,105,100);
	}
}