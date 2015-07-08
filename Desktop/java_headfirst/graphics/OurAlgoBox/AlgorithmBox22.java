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
	String adds;
	JFrame frame2;

	ArrayList<ListHandler> lh ;
	String initialCode;
	String finalCode;
	String selection;
	ListHandler currentCode;
	int check;
	//0 will represent frame and 1 will represent frame 2

	public static void main(String[] args)
	{
		AlgorithmBox2 ab = new AlgorithmBox2();
		ab.buildMainGUI();
		//ab.buildCodeGUI();
	}

	public AlgorithmBox2()
	{
		check = 0;
	}
	public void buildMainGUI()
	{	currentCode = new ListHandler();
		
		frame = new JFrame();
		if((check + 1) == 2)
		{	frame2.dispose();
			check = 0;
		}
		else
		{	System.out.println("worikg");
			frame.dispose();
			check = 0;
		}
		
		frame = new JFrame();
		//setting up menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("FILE");
		JMenuItem openM = new JMenuItem("Open");
		JMenuItem newM = new JMenuItem("New");
		JMenuItem saveM = new JMenuItem("Save/SaveAs");
		openM.addActionListener(new openMenuListener());
		newM.addActionListener(new NewMenuListener());
		saveM.addActionListener(new SaveMenuListener());
		fileMenu.add(openM);
		fileMenu.add(newM);
		fileMenu.add(saveM);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);


		//putting setting for home page
		panelOpen = new JPanel();
		
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
		scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelOpen.add(scroller1);
		panelOpen.setBackground(Color.darkGray);
		list.setVisibleRowCount(20);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.addListSelectionListener(this);
		frame.getContentPane().add(panelOpen);
		//frame.getContentPane().add(scroller1);

		//putting button to the south for open
		open = new JButton("OPEN");
		open.addActionListener(new openActionListener());
		frame.getContentPane().add(BorderLayout.SOUTH,open);

		//setting frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,600);
		frame.setVisible(true);

	}

	

	public void buildCodeGUI()
	{	frame2 = new JFrame();
		if(( check + 1) == 1)
		{
			frame.dispose();
			check=1;

		}
		else
		{	System.out.println("worikg2");
			frame2.dispose();
			check=1;
		}
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

		algoName = new JTextField();
		code = new JTextArea(38,50);
		save = new JButton("Save");
		save.addActionListener(new SaveActionListener());
		System.out.println("inside code gui");
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
		
		frame2.getContentPane().add(BorderLayout.CENTER,panelSave);
		frame2.getContentPane().add(BorderLayout.SOUTH,save);
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
				JLabel lbl = new JLabel("Are you sure you want to save it??");
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
				System.out.println("selection=" + selection);
				if(selection.equals(lh.get(i).getNameofAlgo()))
				{	System.out.println("math found Bingo");
					currentCode.setAddress(lh.get(i).getAddress());
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

	public class SaveMenuListener implements ActionListener
	{	JTextField enter;
		JFrame temp2 ;

		public void actionPerformed(ActionEvent eve)
		{	
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
			temp2.setSize(200,300);
			temp2.setVisible(true);
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
				//System.out.println(obj.getAbsolutePath());
				String flname = file.getPath();

				currentCode.setAddress(flname);
				creaateList();
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
				System.out.println(currentCode.getAddress() + "====================");
				writer.close();
		}catch(IOException ex)
		{
				System.out.println("couldn't write the cardList out");
				ex.printStackTrace();
		}
	}

	public void creaateList()
	{	
		try{	
				BufferedWriter writer = new BufferedWriter(new FileWriter("ListAlgoName.txt",true));
				writer.write(currentCode.getNameofAlgo() + "__" + currentCode.getAddress() + "\n");
				writer.close();
			}catch(IOException ex)
			{
				System.out.println("couldn't write the cardList out");
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
	public class ListHandler
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
	}		
}