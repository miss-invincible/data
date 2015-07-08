import java.awt.*;
import javax.swing.*;

class MyPicPanel extends JPanel
{	
	public void paintComponent(Graphics g)
	{
		Image img = new ImageIcon("DSCN0262.jpg").getImage();
		g.drawImage(img,3,4,this); // this tell the relative postion of the pic wrt to jpanel and NOT RELATIVE TO JFRAME
	}
}