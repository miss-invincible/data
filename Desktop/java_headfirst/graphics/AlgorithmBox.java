import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;


public class AlgorithmBox 
{
	JFrame theFrame;
	JPanel panel;
	JTextField txt;
	JTextArea code;
	JTextArea notes;
	JButton save;
	JButton open;
	JButton open2;
	JButton add;
	JList list;
	String selection;
	ArrayList<ListHandler> lh 

	public static void main(String[] args)
	{
		AlgorithmBox abox = new AlgorithmBox();
		abox.buildGUIMain();
	}

	public void buildGUIMain()
	{	theFrame = new JFrame();
		panel = new JPanel();
		open = new JButton("open existing algo");
		save = new JButton("save a new one");

		
		open.addActionListener(new OpenButtonActionListener());
		save.addActionListener(new SaveButtonActionListener());

		panel.add(open);
		panel.add(save);

		theFrame.getContentPane().add(panel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setSize(800,750);
		theFrame.setVisible(true);


	}

	public void buildGUIOpen()
	{	

		JButton open2 = new JButton("open");
		open2.addActionListener(new Open2ActionListener());

	}
	public void buildGUIAdd()
	{	
		
		txt = new JTextField("enter name of your algo");
		code = new JTextArea();
		notes = new JTextArea();

		JScrollPane scroller1 = new JScrollPane(code);
		scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		JScrollPane scroller2 = new JScrollPane(notes);
		scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


	}

	public class Open2ActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			for(int i = 0;i<lh.size();i++)
			{
				if(selection.equals(lh.get(i).getNameofAlgo()))
				{
					try
					{
						File myFile = new File(lh.get(i).getAddress());
						FileReader filereader = new FileReader(myFile);
						BufferedReader reader = new BufferedReader(filereader);
						String line = null;
						int i=0;
						Data data = new Data();
						while((line = reader.readLine()) != null)
						{
							if(i==0)
							data.setName(line);

							else if(i==1)
							data.setFilename(line);

							else if(i==2)
								

						}
						reader.close();
					}catch(Exception ex){ex.printStackTrace();}
					
					buildGUIOpen();
				}
			}
		}
	}

	public class OpenButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{	list = new JList();
			lh = new ArrayList<ListHandler>();
			ListHandler lobj = new ListHandler();
			try
			{
				File myFile = new File("ListOfAlgos.txt");
				FileReader filereader = new FileReader(myFile);
				BufferedReader reader = new BufferedReader(filereader);
				String line = null;
				String[] parts = new String[2];
				while((line = reader.readLine()) != null)
				{	parts = line.split("_/_");
					lobj.setName(parts[0]);
					lobj.setAddress(parts[1]);

					lh.add(lobj);
				}
				reader.close();
			}catch(Exception ex){ex.printStackTrace();}

			int size = lh.size();

			String[] listEntries = new String[size];

			for(int i=0;i<size;i++)
			{
				listEntries[i] = lh.get(i).getNameofAlgo();
			}

	
			
			JScrollPane scroller = new JScrollPane(list);
			scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scroller);

			list.setVisibleRowCount(5);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.addListSelectionListener(new ListttSelectionListener());

			list = new JList(listEntries);			

		}

		public class ListttSelectionListener implements ListSelectionListener
		{
			
			public String valueChanged(ListSelectionEvent lse)
			{
				if(!lse.getValueIsAdjusting())
				{
					selection = (String) list.getSelectedValue();
				}
			}
		}


	}


	

	public class SaveButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			
		}
	}

	public class ListHandler
	{
		String nameOfAlgo;
		String address;

		public void setAddress(String inAddress)
		{
			address = inAddress;
		}
		public void setName(String inName)
		{
			nameOfAlgo = inName;
		}

		public String getAddress()
		{
			return address;
		}
		public String getNameofAlgo()
		{
			return nameOfAlgo;
		}

	}
}













class Data
{
	String code;
	String notes;
	String name;
	String fileName;

	public void setName(String inName)
	{
		name = inName;
	}

	public void setCode(String inCode)
	{
		code = inCode;
	}
	public void setNotes(String inNotes)
	{	notes = inNotes;

	}
	public void setFilename(String infileName)
	{
		fileName = infileName;
	}
	public String[] getData()
	{	String[] outData = new String[4];
		outData[0] = name;
		outData[1] = code;
		outData[2] = notes;
		outData[3] = fileName;

		return outData;
	}
}