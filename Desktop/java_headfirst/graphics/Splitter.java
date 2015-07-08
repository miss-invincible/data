import javax.swing.*;
import java.awt.*;
//import javax.swing.event.*;
import java.awt.event.*;

public class Splitter
{
	JButton splitIt;
	JTextArea textBox;
	JTextField field;
	JTextField splitter;
	public static void main(String[] agrs)
	{
		Splitter spt = new Splitter();
		spt.go();
	}

	public void go()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		field = new JTextField("enter the string to split");
		splitIt = new JButton("Split IT");
		splitter = new JTextField("enter the splitter");
		textBox =new JTextArea();

		splitIt.addActionListener(new SplitItActionListener());

		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

		JScrollPane scroller = new JScrollPane(textBox);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textBox.setLineWrap(true);

		panel.add(field);
		panel.add(splitter);
		panel.add(splitIt);
		panel.add(textBox);

		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setVisible(true);

	}

	public class SplitItActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			String[] list;
			String toSplit = field.getText();
			String splt = splitter.getText();
			list = toSplit.split(splt);

			for(String item : list)
			{
				textBox.append(item + "\n");

			}

		}
	}



}
