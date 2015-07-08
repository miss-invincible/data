import java.awt.*;
import javax.swing.*;

public class Drawing_widget extends JPanel{
	public void paintComponent(Graphics g)
	{	g.setColor(Color.blue);
		g.fillRect(20,50,100,100);
		Image img = new ImageIcon("DSCN0262.jpg").getImage();
		g.drawImage(img,5,5,this);
	}
	public static void main(String[] args)
	{	Drawing_widget dw1 = new Drawing_widget();
	}
}
	