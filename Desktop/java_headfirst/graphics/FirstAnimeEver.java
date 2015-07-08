import javax.swing.*;
import java.awt.*;

public class FirstAnimeEver
{	int x;
	int y;
	

	public static void main(String[] args)
	{
		FirstAnimeEver fae = new FirstAnimeEver();
		fae.go();	

	}

	

	public void go()
	{	
		JFrame jfm = new JFrame();
		x=0;
		y=0;

		Paintt pt = new Paintt();
		jfm.getContentPane().add(BorderLayout.CENTER,pt);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(400,400);
		jfm.setVisible(true);
		JButton ju = new JButton();
		int i;
		

		for(i=0;i<300;i++)
		{
			x++;
			y++;

			//jfm.repaint(); uncomment this to see tha magic
			pt.repaint();

			try
			{
				Thread.sleep(50);

			}catch(Exception e){}

		}

	}
	class Paintt extends JPanel
	{	public void paintComponent(Graphics g)
		{	g.fillRect(0,0,this.getWidth(),this.getHeight()); // required when we repaint the panel
			g.setColor(Color.red);	
			//g.fillOval(40,40,x,y);
			g.fillOval(x,y,40,40);
		}

	}

	
}