import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//but this breaks encapsulation

public class FourWidgetsMain2
{	

	public JFrame jfm ;
	public JButton colorButton;
	public JButton label;
	public JButton labelButton;
	public static void main(String[] args)
	{
		FourWidgetsMain2 fwm = new FourWidgetsMain2();
		fwm.go();
	}

	public void go ()
	{
		jfm = new JFrame();
		colorButton = new JButton("click me i change color");
		labelButton = new JButton("click me i change label");
		label = new JButton("i m going to change :'(");
		FourWidgets fw = new FourWidgets();

		jfm.getContentPane().add(BorderLayout.SOUTH,colorButton);
		jfm.getContentPane().add(BorderLayout.EAST,labelButton);
		jfm.getContentPane().add(BorderLayout.CENTER,fw);
		jfm.getContentPane().add(BorderLayout.WEST,label);

		ColorButtonActionListener cbal = new ColorButtonActionListener(this);
		LabelButtonActionListener lbal = new LabelButtonActionListener(this);
		//colorButton.addActionListener(this);
		//labelButton.addActionListener(this);

		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(500,500);
		jfm.setVisible(true);
 

	}
	/*
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == colorButton)
			jfm.repaint();
		else
			label.setText("i m a new one");
	}
	*/

}

class ColorButtonActionListener implements ActionListener
{	JFrame widgets;
	JButton cbutton;
	

	public ColorButtonActionListener(FourWidgetsMain2 fwm2)
	{	widgets=fwm2.jfm;
		cbutton = fwm2.colorButton;
		cbutton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event)
	{	
		widgets.repaint();

	}
	
}

class LabelButtonActionListener implements ActionListener
{	JButton lbl;
	JButton lbutton;
	public LabelButtonActionListener(FourWidgetsMain2 fwm2)
	{
		lbl = fwm2.label;
		lbutton=fwm2.labelButton;
		lbutton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event)
	{
		lbl.setText("voilaa!!  its OO way");

	}

}
