import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextArea1 implements ActionListener
{	JTextArea text;
	public static void main(String[] args) 
	{
		TextArea1 ta1 = new TextArea1();
		ta1.go();		
	}

	public void go()
	{
		JFrame jfm = new JFrame();
		JButton jbt = new JButton("Just click it");
		JPanel jpl = new JPanel();
		jbt.addActionListener(this);
		text = new JTextArea(10,20);
		text.setLineWrap(true);

		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jpl.add(scroller);

		jfm.getContentPane().add(BorderLayout.CENTER,jpl);
		jfm.getContentPane().add(BorderLayout.SOUTH,jbt);

		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);
	}
	public void actionPerformed(ActionEvent event)
	{
		text.append("you clicked it\n");

	}
}