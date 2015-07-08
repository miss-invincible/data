import javax.swing.*;
import java.awt.*;

class Graphics2d_1 extends JPanel
{
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g; //converting the reference of g from Graphics to Graphics2D subclass intially it was Graphics g = new Graphics2D();
		
		//setting RGB colors balancing
		int red = (int) (Math.random()*255);
		int green = (int) (Math.random()*255);
		int blue = (int) (Math.random()*255);

		Color startColor = new Color(red,green,blue);

		//int red = (int) (Math.random()*255) is wrong as redeclaration is invalid;
		red = (int) (Math.random()*255);
		blue = (int) (Math.random()*255);
		green = (int) (Math.random()*255);

		Color endColor = new Color(red,green,blue);

		GradientPaint gradient = new GradientPaint(0,0,startColor,300,300,endColor);	//gradient only works on 2d objects and not on 3d
		g2d.setPaint(gradient);
		//g2d.draw3DRect(10,10,30,30,false);
		//g2d.fill3DRect(40,40,70,70,false);
		g2d.fillRect(40,40,70,70);


		g2d.setPaint(gradient);	//this changes the brush from default solid to gradient
		g2d.fillOval(80,120,140,140);



		g2d.draw3DRect(10,10,30,30,false);
		g2d.setColor(Color.pink);
		g2d.fill3DRect(10,10,30,30,true);
		g2d.rotate(90,90,90);

		//a different color can be seen evrytime u minimize it

		g2d.setColor(Color.yellow);
		g2d.fill3DRect(10,30,60,90,true);

	}
}