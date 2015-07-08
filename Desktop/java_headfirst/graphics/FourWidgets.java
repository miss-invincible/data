import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FourWidgets extends JPanel
{	public void paintComponent(Graphics g)
	{	Graphics2D g2d = (Graphics2D) g;

		int red = (int) (Math.random()*255);
		int green = (int) (Math.random()*255);
		int blue = (int) (Math.random()*255);

		Color startColor = new Color(red,green,blue);

		red = (int) (Math.random()*255);
		green = (int) (Math.random()*255);
		blue = (int) (Math.random()*255);

		Color endColor = new Color(red,green,blue);

		GradientPaint gp = new GradientPaint(0,0,startColor,this.getWidth(),this.getHeight(),endColor);
		g2d.setPaint(gp);
		g2d.fillOval(40,40,100,100);

	}

}