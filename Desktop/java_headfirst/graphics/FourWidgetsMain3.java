import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//but this breaks encapsulation

public class FourWidgetsMain3
{	

	JFrame jfm ;
	JButton colorButton;
	JButton label;
	JButton labelButton;
	public static void main(String[] args)
	{
		FourWidgetsMain3 fwm = new FourWidgetsMain3();
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
	public JFrame getJFrame()
	{
		return jfm;
	}
	public JButton getLabel()
	{
		return label;
	}
	public JButton getColorButton()
	{
		return colorButton;
	}
	public JButton getLabelButton()
	{
		return labelButton;
	}

}

class ColorButtonActionListener implements ActionListener
{	JFrame widgets;
	JButton cbutton;
	

	public ColorButtonActionListener(FourWidgetsMain3 fwm3)
	{	widgets=fwm3.getJFrame();
		cbutton=fwm3.getColorButton();
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
	JFrame widgets;
	int count;
	public LabelButtonActionListener(FourWidgetsMain3 fwm3)
	{
		lbutton = fwm3.getLabelButton();
		widgets = fwm3.getJFrame();
		lbl = fwm3.getLabel();
		lbutton.addActionListener(this);
		int count=0;
	}

	public void actionPerformed(ActionEvent event)
	{	if(count%2==0)
		lbl.setText("voilaa!!ts OO way");
		else
		lbl.setText("u pressed again!!");

		count++;

	}

}
