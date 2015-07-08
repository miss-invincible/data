import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class TextFieldd2 implements ActionListener
{	JTextField jtf1; 
	public static void main(String[] args) 
	{
		TextFieldd2 tf2 = new TextFieldd2();
		tf2.go();		
	}

	public void go()
	{
		JFrame jfm = new JFrame();
		
		jtf1 = new JTextField(20);
		JTextField jtf2 = new JTextField("enter yor name");
		JPanel jpl = new JPanel();
		jpl.add(jtf2);

		jpl.add(jtf1);

		jpl.setBackground(Color.blue);
		jtf1.addActionListener(this); //return call back method when user presses enter or return key
		jfm.getContentPane().add(BorderLayout.CENTER,jpl);
		jtf1.requestFocus(); // not working:bringing cursor to frst dialog box
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);
	}

	public void actionPerformed(ActionEvent event)
	{	String maa = "maaa";
		System.out.println(jtf1.getText());
		if(maa.equals(jtf1.getText()))
			jtf1.selectAll();  //highlighting the text (or selecting it) 
		else
		jtf1.setText("");

	}
}