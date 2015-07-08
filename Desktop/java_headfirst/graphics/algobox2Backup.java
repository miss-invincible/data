import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class AlgorithmBox2
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

	ArrayList<ListHandler> lh ;
	String selection;


	public static void main(String[] args)
	{
		AlgorithmBox2 ab = new AlgorithmBox2();
		ab.buildMainGUI();
		//ab.buildCodeGUI();
	}

	public void buildMainGUI()
	{	
		frame = new JFrame();

		//setting up menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("FILE");
		JMenuItem openM = new JMenuItem("Open");
		JMenuItem newM = new JMenuItem("New");
		JMenuItem saveM = new JMenuItem("Save/SaveAs");
		//openM.addActionListener(new OpenMenuListener());
		//newM.addActionListener(new NewMenuListener());
		//saveM.addActionListener(new SaveMenuListener());
		fileMenu.add(openM);
		fileMenu.add(newM);
		fileMenu.add(saveM);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);


		//putting setting for home page
		panelOpen = new JPanel();
		//String[] listEntries = getListEntries();
		//String[] listEntries = {"shivangi","prabhat","shanu","honey","akash","pari","maa"};
		
		lh = new ArrayList<ListHandler>();
		loadList(); //to read the file where name of algo and address are stored and load the list into arraylist;
		int size = lh.size();

		String[] listEntries = new String[size];
		for(int i = 0;i<size;i++)
		{
			listEntries[i] = lh.get(i).getNameofAlgo();
		}
		list = new JList(listEntries);
		scroller1 = new JScrollPane(list);
		scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelOpen.add(scroller1);
		panelOpen.setBackground(Color.darkGray);
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.addListSelectionListener(this);
		frame.getContentPane().add(panelOpen);

		//putting button to the south for open
		open = new JButton("OPEN");
		open.addActionListener(new openActionListener());
		frame.getContentPane().add(BorderLayout.SOUTH,open);

		//setting frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,730);
		frame.setVisible(true);

	}

	public void buildCodeGUI()
	{
		//i m not changing the frame
		//replacing panelOpen with panelSave
		//and open button with save/saveas button
		JFrame frame2 = new JFrame();
		//setting up menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("FILE");
		JMenuItem openM = new JMenuItem("Open");
		JMenuItem newM = new JMenuItem("New");
		JMenuItem saveM = new JMenuItem("Save/SaveAs");
		//openM.addActionListener(new OpenMenuListener());
		//newM.addActionListener(new NewMenuListener());
		//saveM.addActionListener(new SaveMenuListener());
		fileMenu.add(openM);
		fileMenu.add(newM);
		fileMenu.add(saveM);
		menuBar.add(fileMenu);
		frame2.setJMenuBar(menuBar);


		
		panelSave = new JPanel();
		algoName = new JTextField();
		code = new JTextArea(38,50);
		save = new JButton("Save/SaveAs");
		System.out.println("inside code gui");
		//text.setLineWrap(true); as we dont need line wrap
		String text = " ";
		scroller2 = new JScrollPane(code);
		scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		panelSave.add(scroller2);
		frame.setVisible(false);
		
		frame2.getContentPane().add(BorderLayout.CENTER,panelSave);
		frame2.getContentPane().add(BorderLayout.SOUTH,save);
		frame2.setSize(800,730);
		frame2.setVisible(true);

	}


	public class openActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{	
			System.out.println(lh.size());
			for(int i = 0;i<lh.size();i++)
			{	selection = (String) list.getSelectedValue();
				System.out.println("selection=" + selection);
				if(selection.equals(lh.get(i).getNameofAlgo()))
				{	System.out.println("math found Bingo");
					
					try
					{	buildCodeGUI(); //asking it to make gui

						File myFile = new File(lh.get(i).getAddress()); //store file names with .txt extensions
						FileReader filereader = new FileReader(myFile);
						BufferedReader reader = new BufferedReader(filereader);
						String line = null;
							
						while((line = reader.readLine()) != null)
						{
							code.append(line+"\n");
						}
						reader.close();
						break;
					}catch(Exception ex){ex.printStackTrace();}	
				}
			}
		}
	}

	public void loadList()
	{
		try
		{	//handlee when the list has no value
			BufferedReader reader = new BufferedReader(new FileReader("ListAlgoName.txt"));
			String line = null;

			while((line = reader.readLine()) != null)
			{
				String[] result = line.split("__");
				System.out.println("add=" + result[1]);
				//System.out.println(line);
				ListHandler data = new ListHandler(result[0],result[1]);
				lh.add(data);
				System.out.println(lh.size());
			}
			reader.close();
		}catch(Exception ex){ex.printStackTrace();}
			
	}

	public void creaateList()
	{	
		//this code runs when user clics on save button
		

	}

	//this class just acts like a structure to hold two variables together
	public class ListHandler
	{
		String nameOfAlgo;
		String address;

		public ListHandler(String inName,String inAddress)
		{
			address = inAddress;
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