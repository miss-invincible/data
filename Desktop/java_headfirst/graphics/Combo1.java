import javax.swing.*;
import java.awt.*;

class Combo1 extends JPanel
{	public void paintComponent(Graphics g)
	{
		Graphics2D g2d= (Graphics2D) g;

		g2d.setColor(Color.red);
		g2d.fillRect(0,0,this.getWidth(),this.getHeight());

		int red = (int) (Math.random()*255);
		int green = (int) (Math.random()*255);
		int blue = (int) (Math.random()*255);

		Color startColor = new Color(red,green,blue);

		red = (int) (Math.random()*255);
		green = (int) (Math.random()*255);
		blue = (int) (Math.random()*255);

		Color endColor = new Color(red,green,blue);

		GradientPaint gp = new GradientPaint(0,0,startColor,300,300,endColor);
		g2d.setPaint(gp);
		g2d.fillOval(60,50,170,90);
		g2d.fillOval(260,250,190,170);
		
		g2d.fillRect(10,300,60,100);
		/*
		red = (int) (Math.random()*255);
		green = (int) (Math.random()*255);
		blue = (int) (Math.random()*255);
		
		startColor = new Color(red,green,blue);

		red = (int) (Math.random()*255);
		green = (int) (Math.random()*255);
		blue = (int) (Math.random()*255);

		endColor = new Color(red,green,blue);

		GradientPaint p = new GradientPaint(0,0,startColor,300,300,endColor);

		g2d.setPaint(p);
		 //it is doing nothig :( */
		
		//Color color = new Color(red,green,blue);
		
	}
} 