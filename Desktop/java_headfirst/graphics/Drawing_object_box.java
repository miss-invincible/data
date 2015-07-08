import java.awt.*;
import javax.swing.*;

class MyDrawPanel extends JPanel{
	public static void main(String[] args)
	{	MyDrawPanel mdp = new MyDrawPanel();
	}


	public void paintComponent(Graphics g){
		g.setColor(Color.orange);
		g.fillRect(20,50,100,100);
	}
	
}