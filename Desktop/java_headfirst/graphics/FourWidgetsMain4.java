import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FourWidgetsMain4
{	JFrame frame;
	JLabel label;
	public static void main(String[] args)
	{	
		FourWidgetsMain4 fwm4 = new FourWidgetsMain4();
		fwm4.go();

	}

	public void go()
	{
		frame = new JFrame();
		label = new JLabel("i m going to change :(");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton colorButton = new JButton("click me to see the magic");
		colorButton.addActionListener(new LabelListener());

		JButton labelButton = new JButton("click me to see the magic");
		labelButton.addActionListener(new ColorListener());

		label = new JLabel("I m Lable");

		FourWidgets fwm = new FourWidgets();

		frame.getContentPane().add(BorderLayout.SOUTH,colorButton);
		frame.getContentPane().add(BorderLayout.CENTER,fwm);
		frame.getContentPane().add(BorderLayout.WEST,label);
		frame.getContentPane().add(BorderLayout.EAST,labelButton);

		frame.setSize(500,500);
		frame.setVisible(true);

	}

	class LabelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{	label.setText("i m changed");

		}
	}

	class ColorListener implements ActionListener
	{	public void actionPerformed(ActionEvent event)
		{
			frame.repaint();
		}

	}
}