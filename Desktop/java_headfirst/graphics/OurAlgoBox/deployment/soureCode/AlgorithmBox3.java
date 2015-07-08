//in this version i m adding sorting to the open list on the basis of file name and algo name both.

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class AlgorithmBox3
{
	JFrame frame;
	JPanel panelOpen;
	JPanel panelSave;
	JTextArea code;
	JTextField algoName;
	JButton save;
	JButton saveAs;
	JButton open;
	JScrollPane scroller1;
	JScrollPane scroller2;
	JList list;
	String adds;
	JFrame frame2;

	ArrayList<ListHandler> lh ;
	String initialCode;
	String finalCode;
	String selection;
	ListHandler currentCode;
	int check;
	int mode;
	//0 will represent frame and 1 will represent frame 2

	public static void main(String[] args)
	{
		AlgorithmBox3 ab = new AlgorithmBox3();
		ab.buildMainGUI();
		//ab.buildCodeGUI();
	}

	public AlgorithmBox3()
	{
		check = 0;
	}
	public void buildMainGUI()
	{	currentCode = new ListHandler();
		
		//frame = new JFrame();
		try
		{
			if(check == 1)
			{	frame2.dispose();
				check = 0;
			}	
			else
			{	frame.dispose();
				check = 0;
			}
		}catch(Exception ex){ System.out.println("it is logically correct");}
		
		frame = new JFrame();
		//setting up menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("FILE");
		JMenuItem openM = new JMenuItem("Open");
		JMenuItem newM = new JMenuItem("New");
		JMenuItem saveM = new JMenuItem("SaveAs");
		
		JMenu editMenu = new JMenu("EDIT");
		JMenu sortSubMenu1 = new JMenu("sort by");
		JMenuItem sortByFileName = new JMenuItem("File Name");
		JMenuItem sortByAlgoName = new JMenuItem("Algorithm Name");
		
		
		openM.addActionListener(new openMenuListener());
		newM.addActionListener(new NewMenuListener());
		saveM.addActionListener(new SaveMenuListener());

		
		sortByAlgoName.addActionListener(new SortByAlgoNameActionListener());
		sortByFileName.addActionListener(new SortByFileNameActionListener());
		
		fileMenu.add(openM);
		fileMenu.add(newM);
		fileMenu.add(saveM);
		editMenu.add(sortSubMenu1);
		sortSubMenu1.add(sortByAlgoName);
		sortSubMenu1.add(sortByFileName);
		
		
		
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		frame.setJMenuBar(menuBar);

		


		//putting setting for home page
		panelOpen = new JPanel();
		
		lh = new ArrayList<ListHandler>();
		loadList(mode); //to read the file where name of algo and address are stored and load the list into arraylist;
		mode = 0; //setting mode to default value
		int size = lh.size();

		String[] listEntries = new String[size];
		for(int i = 0;i<size;i++)
		{
			listEntries[i] = lh.get(i).getNameofAlgo();
		}
		list = new JList(listEntries);
		scroller1 = new JScrollPane(list);
		scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panelOpen.add(BorderLayout.CENTER,scroller1);
		//panelOpen.setBackground("wal1.jpeg");
		list.setVisibleRowCount(20);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.addListSelectionListener(this);
		
		//frame.getContentPane().add(scroller1);
		MyPicPanel mypic = new MyPicPanel();
		mypic.setBorder(BorderFactory.createEmptyBorder(120,10,10,10));

		mypic.add(BorderLayout.CENTER,panelOpen);
		frame.getContentPane().add(mypic);
		//frame.getContentPane().add(BorderLayout.CENTER,panelOpen);
		//putting button to the south for open
		open = new JButton("OPEN");
		open.setBackground(Color.BLACK);
		open.addActionListener(new openActionListener());
		frame.getContentPane().add(BorderLayout.SOUTH,open);

		//setting frame
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,730);
		frame.setVisible(true);

	}

	public class MyPicPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Image img = new ImageIcon("final.jpg").getImage();
			g.drawImage(img,3,4,this); // this tell the relative postion of the pic wrt to jpanel and NOT RELATIVE TO JFRAME
		}
	}
	
	

	public void buildCodeGUI()
	{	//frame2 = new JFrame();
		try
		{
			if(check == 0)
			{
				frame.dispose();
				check=1;

			}	
			else
			{	frame2.dispose();
				check=1;
			}
		}catch(Exception ex){System.out.println("its logically correct");}
		frame2 = new JFrame();
		//i m not changing the frame
		//replacing panelOpen with panelSave
		//and open button with save/saveas button
		
		//setting up menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("FILE");
		JMenuItem openM = new JMenuItem("Open");
		JMenuItem newM = new JMenuItem("New");
		JMenuItem saveM = new JMenuItem("SaveAs");
		openM.addActionListener(new openMenuListener());
		newM.addActionListener(new NewMenuListener());
		saveM.addActionListener(new SaveMenuListener());
		fileMenu.add(openM);
		fileMenu.add(newM);
		fileMenu.add(saveM);
		menuBar.add(fileMenu);
		frame2.setJMenuBar(menuBar);

		

		panelSave = new JPanel();
		panelSave.setLayout(new BoxLayout(panelSave,BoxLayout.Y_AXIS));
		JLabel name = new JLabel("NAME:" + selection);
		JLabel adrs = new JLabel("ADDRESS:" + adds);
		JPanel panelExtra = new JPanel();
		panelExtra.add(name);
		panelExtra.add(adrs);
		panelSave.add(panelExtra);
		MyPicPanel mypic2 = new MyPicPanel();
		mypic2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		mypic2.add(panelSave);
		
		algoName = new JTextField();
		//38 50
		code = new JTextArea(38,70);
		save = new JButton("Save");
		MyPicPanel plnSav = new MyPicPanel();
		plnSav.add(save);
		plnSav.setBorder(BorderFactory.createEmptyBorder(100,200,100,200));
		save.addActionListener(new SaveActionListener());
		if(adds == null && selection == null)
		{
			save.setEnabled(false);
		}
		//text.setLineWrap(true); as we dont need line wrap
		String text = " ";
		scroller2 = new JScrollPane(code);
		scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		panelSave.add(scroller2);
		//frame.setVisible(false);
		
		//frame2.getContentPane().add(BorderLayout.CENTER,panelSave);
		frame2.getContentPane().add(mypic2);
		frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame2.getContentPane().add(BorderLayout.EAST,plnSav);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(800,730);
		frame2.setVisible(true);

	}

	public class SaveActionListener implements ActionListener
	{
		JFrame temp;
		public void actionPerformed(ActionEvent eve)
		{	
			finalCode = code.getText(); 
			
			if(!(finalCode.equals(initialCode)))
			{
				temp = new JFrame();
				JButton sure = new JButton("OK");
				JButton cancel = new JButton("CANCEL");
				JLabel lbl = new JLabel("Are you sure you want to make changes??");
				JPanel tempPanel1 = new JPanel();
				JPanel tempPanel2 = new JPanel();
				sure.addActionListener(new SureActionListener());
				cancel.addActionListener(new CancelActionListener());
				tempPanel2.setLayout(new BoxLayout(tempPanel2,BoxLayout.Y_AXIS));
				tempPanel1.add(sure);
				tempPanel1.add(cancel);
				tempPanel2.add(lbl);
				tempPanel2.add(tempPanel1);
				temp.getContentPane().add(tempPanel2);
				temp.setSize(300,100);
				temp.setVisible(true);

			}

		}

		public class SureActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent eve)
			{
				writeCodeToFile();
				temp.dispose();

			}
		}
		public class CancelActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent eve)
			{
				temp.dispose();
			}
		}
	}

	public class openActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{	
			System.out.println(lh.size());
			for(int i = 0;i<lh.size();i++)
			{	selection = (String) list.getSelectedValue();
				if(selection.equals(lh.get(i).getNameofAlgo()))
				{	currentCode.setAddress(lh.get(i).getAddress());
					currentCode.setNameofAlgo(lh.get(i).getNameofAlgo());
					try
					{	
						adds = lh.get(i).getAddress();
						buildCodeGUI(); //asking it to make gui
						File myFile = new File(lh.get(i).getAddress()); //store file names with .txt extensions
						FileReader filereader = new FileReader(myFile);
						BufferedReader reader = new BufferedReader(filereader);
						String line = null;
							
						while((line = reader.readLine()) != null)
						{
							code.append(line+"\n");
						}
						initialCode = code.getText();
						reader.close();
						break;
					}catch(Exception ex){ex.printStackTrace();}	
				}
			}
		}
	}

	public class openMenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{	
			buildMainGUI();
			
		}
	}

	public class SortByAlgoNameActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			mode = 0;
			buildMainGUI();
		}
	}
	
	public class SortByFileNameActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			
			mode = 1;
			buildMainGUI();
		}
	}
	
	public void loadList(int m) //m tells the sort by nature
	{
		try
		{	//always keep few names to ListNameAlgo to avoid error
			BufferedReader reader = new BufferedReader(new FileReader("ListAlgoName.txt"));
			String line = null;

			while((line = reader.readLine()) != null)
			{
				String[] result = line.split("__");
				ListHandler data = new ListHandler(result[0],result[1]);
				lh.add(data);
			}
			if(m == 0)
			Collections.sort(lh);
			else 
			{
				//CompareByNameOfFile compareList = new CompareByNameOfFile();
				ListHandler compareList = new ListHandler();
				Collections.sort(lh,compareList);
			}
			
			reader.close();
		}catch(Exception ex){ex.printStackTrace();}
			
	}

	public class SaveMenuListener implements ActionListener
	{	JTextField enter;
		JFrame temp2 ;

		public void actionPerformed(ActionEvent eve)
		{	//gui handling
			temp2 = new JFrame();
			JPanel pnl = new JPanel();
			JLabel ask = new JLabel("enter unique name of algo");
			enter = new JTextField(" ");
			JButton ok = new JButton("OK");

			pnl.setLayout(new BoxLayout(pnl,BoxLayout.Y_AXIS));
			pnl.add(ask);
			pnl.add(enter);
			pnl.add(ok);
			ok.addActionListener(new okActionListener());

			temp2.getContentPane().add(pnl);
			temp2.setSize(200,110);
			temp2.setVisible(true);
			//gui complete
		}

		public class okActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent eve)
			{	temp2.dispose();
				currentCode.setNameofAlgo(enter.getText());
				JFileChooser fileSave = new JFileChooser();
				fileSave.showSaveDialog(frame);
				File file;
				file=fileSave.getSelectedFile();
				String flname = file.getAbsolutePath(); //getting file name of currently saving algo

				currentCode.setAddress(flname);
				createList();
				finalCode = code.getText();
				writeCodeToFile();
				initialCode = finalCode;
			}
		}
	}

	public void writeCodeToFile()
	{
		try
		{
				BufferedWriter writer = new BufferedWriter(new FileWriter(currentCode.getAddress()));

				writer.write(finalCode);
				writer.close();
		}catch(IOException ex)
		{
				System.out.println("WRITING CODE TO FILE FAILED");
				ex.printStackTrace();
		}
	}

	public void createList()
	{	
		try{	
				BufferedWriter writer = new BufferedWriter(new FileWriter("ListAlgoName.txt",true));
				writer.write(currentCode.getNameofAlgo() + "__" + currentCode.getAddress() + "\n");
				writer.close();
			}catch(IOException ex)
			{
				System.out.println("WRITING ALGOLIST TO FILE FAILED");
				ex.printStackTrace();
			}
	}

	
	public class NewMenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			adds = null;
			selection = null;
			buildCodeGUI();
		}
	}



	//this class just acts like a structure to hold two variables together
	public class ListHandler implements Comparable<ListHandler> ,Comparator<ListHandler>
	{
		String nameOfAlgo;
		String address;

		public ListHandler()
		{
			address = "default";
			nameOfAlgo = "default.txt";
		}
		public ListHandler(String inName,String inAddress)
		{
			address = inAddress;
			nameOfAlgo = inName;
		}

		public void setAddress(String inAddress)
		{
			address = inAddress;
		}
		public void setNameofAlgo(String inName)
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
		
		public int compareTo(ListHandler x)		//i m treating sort by name of algorithm as default operation so there would be two types of sort operations possible: using comprable and second one using comparator
		{	
			return nameOfAlgo.compareTo(x.nameOfAlgo); 
		}
		
		public String toString() //this function is used when we write statement: System.out.println(ls); where ListHandler ls;
		{	return nameOfAlgo;
		}
		
		public int compare(ListHandler a,ListHandler b)
		{	
			return a.getAddress().compareTo(b.getAddress()); // i m not making my own function here to detect which one is better just like compareTo method used in abouve class because String implements comparable so it has it own copareTo method to sort in lexicographic order.
		}
		
		
	}		
	/*
	public class CompareByNameOfFile implements Comparator<ListHandler>  //this is one is separate class for comparator 
	{	
		public int compare(ListHandler a,ListHandler b)
		{	
			return a.getAddress().compareTo(b.getAddress()); // i m not making my own function here to detect which one is better just like compareTo method used in abouve class because String implements comparable so it has it own copareTo method to sort in lexicographic order.
		}
	}*/
}