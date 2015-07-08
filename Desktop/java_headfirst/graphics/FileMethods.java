import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class FileMethods
{	JTextField text;
	JTextField fileName;
	JTextField fileName2; 
	public static void main(String[] args)
	{	

		FileMethods fm = new FileMethods();
		fm.go();
	}

	public void go()
	{
		File f = new File("hello.txt"); //just the name of the file is created n not hte actual file.

		File myDir = new File("madeByCode");
		myDir.mkdir();

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		text = new JTextField("enter directory name");
		JButton find = new JButton("find");
		fileName = new JTextField("enter file or dir name");
		JButton getAbsPath = new JButton("get absolute path of a file or dir");
		fileName2 = new JTextField("enter the file or dir name to be deleted");
		JButton delete = new JButton("delete a file or dir");
		
		getAbsPath.addActionListener(new AbsButtonActionListener());
		delete.addActionListener(new DeleteActionListener());
		find.addActionListener(new FindActionListener());
		
		panel.add(text);
		panel.add(find);
		panel.add(fileName);
		panel.add(getAbsPath);
		panel.add(fileName2);
		panel.add(delete);

		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);

	}

	public class FindActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			String name = text.getText();
			File dir = new File(name);
			if(dir.isDirectory())
			{
				String[] dirContents = dir.list();
				for(int i=0;i<dirContents.length;i++)
				{
					System.out.println(dirContents[i]);
				}
			}
		}
	}

	public class AbsButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			File obj = new File(fileName.getText());
			//System.out.println(obj.getAbsolutePath());
			System.out.println(obj.getPath());

		}
	}

	public class DeleteActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			File obj = new File(fileName2.getText());
			boolean isDeleted = obj.delete();
			System.out.println("the file is deleted:" + isDeleted );

		}
	}
}
