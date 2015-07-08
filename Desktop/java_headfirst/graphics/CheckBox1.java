import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckBox1 implements ItemListener
{	JCheckBox jckb;
	JButton b;
	public static void main(String[] args) 
	{	CheckBox1 cb1 = new CheckBox1();
		
		cb1.go();

	}

	public void go()
	{
		JFrame jfm = new JFrame();
		jckb = new  JCheckBox("goes to you");
		JPanel jpl = new JPanel();
		jpl.add(jckb);
		jckb.setSelected(false); //keeping intitally unselected
		jckb.addItemListener(this);
		b = new JButton("change state");
		b.addActionListener(new ButtonListener());
		jpl.add(b);
		jfm.getContentPane().add(jpl);

		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);

	}

	public void itemStateChanged(ItemEvent event)
	{

		String onOrOff = "off";
		if(jckb.isSelected())
		{
			onOrOff = "true";
			
		}	
		System.out.println("checkBox is "+ onOrOff);
	}

	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			if(jckb.isSelected())
				jckb.setSelected(false);
			else
				jckb.setSelected(true);
		}
	}
}