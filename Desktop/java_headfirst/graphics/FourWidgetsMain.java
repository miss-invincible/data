import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FourWidgetsMain implements ActionListener
{	JFrame jfm ;
	JButton colorButton;
	JButton label;
	public static void main(String[] args)
	{
		FourWidgetsMain fwm = new FourWidgetsMain();
		fwm.go();
	}

	public void go ()
	{
		jfm = new JFrame();
		colorButton = new JButton("click me i change color");
		JButton labelButton = new JButton("click me i change label");
		label = new JButton("i m going to change :'(");
		FourWidgets fw = new FourWidgets();

		jfm.getContentPane().add(BorderLayout.SOUTH,colorButton);
		jfm.getContentPane().add(BorderLayout.EAST,labelButton);
		jfm.getContentPane().add(BorderLayout.CENTER,fw);
		jfm.getContentPane().add(BorderLayout.WEST,label);

		colorButton.addActionListener(this);
		labelButton.addActionListener(this);

		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(500,500);
		jfm.setVisible(true);
 

	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == colorButton)
			jfm.repaint();
		else
			label.setText("i m a new one");
	}

}