import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class TextFieldd1 implements ActionListener
{	JTextField jtf1; 
	public static void main(String[] args) 
	{
		TextFieldd1 tf1 = new TextFieldd1();
		tf1.go();		
	}

	public void go()
	{
		JFrame jfm = new JFrame();
		JButton jbt = new JButton("ok");
		jtf1 = new JTextField(20);
		JTextField jtf2 = new JTextField("enter yor name");
		JPanel jpl = new JPanel();
		jpl.add(jtf2);
		jpl.add(jtf1);

		jpl.setBackground(Color.blue);
		jbt.addActionListener(this);
		jfm.getContentPane().add(BorderLayout.CENTER,jpl);
		jfm.getContentPane().add(BorderLayout.SOUTH,jbt);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);
	}

	public void actionPerformed(ActionEvent event)
	{
		System.out.println(jtf1.getText());
		jtf1.setText("");

	}
}