import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class List1 implements ActionListener
{	JList jl ;
	public static void main(String[] args) 
	{	List1 l1 = new List1();
		l1.go();
	}

	public void go()
	{
		JFrame jfm = new JFrame();
		JPanel jpl = new JPanel();
		String[] listEntries = {"shivangi","prabhat","shanu","honey","akash","pari","maa"};
		jl = new JList(listEntries);

		JScrollPane scroller = new JScrollPane(jl);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jpl.add(scroller);

		jl.setVisibleRowCount(5);
		jl.setSelectionMode(ListSelectionMode.SINGLE_SELECTION);
		jl.addListSelectionListener(this);


		jfm.getContentPane().add(jpl);
		jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setSize(300,300);
		jfm.setVisible(true);

	}

	public void valueChanged(ListSelectionEvent lse)
	{
		if(!lse.getValueIsAdjusting())
		{
			String selection = (String) jl.getSelectedValue();
			System.out.println(selection);
		}
	}
}