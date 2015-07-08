import java.awt.*;
import javax.swing.*;

class RandomOval extends JPanel
{
	public void paintComponent(Graphics g)
	{	//since no color is set so bydefault its black.while filling
		g.fillRect(0,0,this.getWidth(),this.getHeight()); //fully fill the jpanel

		int red = (int) (Math.random()*255);
		int green = (int) (Math.random()*255);
		int blue = (int) (Math.random()*255);

		Color randomColor = new Color(red,green,blue); //setting rgb values for making a color
		g.setColor(randomColor);
		g.fillOval(70,70,100,100); //relative to jpanel distance from left and top
	}
}