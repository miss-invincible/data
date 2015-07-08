import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // why we need to import it explicitely when we have imported full awt!!???

public class Combo1Miain implements ActionListener
{	JFrame jfm;
	public static void main(String[] args)
	{
		Combo1Miain c1m = new Combo1Miain();
		c1m.go();

	}

	public void go()
	{
		jfm = new JFrame();
		JButton jbt = new JButton("click to see the magic");
		Combo1 cb1 = new Combo1();

		jbt.addActionListener(this);


		jfm.getContentPane().add(BorderLayout.CENTER,cb1);
		jfm.getContentPane().add(BorderLayout.SOUTH,jbt);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);

	}

	public void actionPerformed(ActionEvent event)
	{
		jfm.repaint();
	}
}