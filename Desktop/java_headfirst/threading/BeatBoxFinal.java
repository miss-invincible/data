import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class BeatBoxFinal{	


	public static void main(String[] args)
	{
		BeatBoxFinal bb = new BeatBoxFinal();
		bb.buildGUI();

	}
	JLabel label = new JLabel("save file with .ser extention only");
	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	String nameOfBeat;
	String[] instrumentNames = {"Bass Drum","Closed Hi-Hat","Open Hi-Hat","Acoustic Snare","Crash Cymbal","Hand Clap","High Tom","Hi Bongo","Maracas","Whistle","Low Conga","Cowbell","Vibraslap","Low-mid Tom","High Agogo","Open Hi Congo"};
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	
	JButton start;
	JButton stop; 
	JButton upTempo;
	JButton downTempo; 
	boolean check;
	
	Socket sock;
	JTextArea incoming;
	JTextField outgoing;
	PrintWriter writer;
	BufferedReader reader;
	
	public void buildGUI()
	{

		theFrame = new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		nameOfBeat = "null";
		start = new JButton("start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);

		stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);

		upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);

		downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("FILE");
		JMenuItem loadMenuItem = new JMenuItem("Load a beat");
		loadMenuItem.addActionListener(new OpenMenuListener());
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		theFrame.setJMenuBar(menuBar);


		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for(int i = 0;i<16;i++)
		{
			nameBox.add(new Label(instrumentNames[i]));

		}

		
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel,BoxLayout.Y_AXIS));
		
		outgoing = new JTextField();
		incoming = new JTextArea(15,20);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JButton send = new JButton("SEND");
		send.addActionListener(new sendActionListener());
		
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		newPanel.add(buttonBox);
		newPanel.add(qScroller);
		newPanel.add(outgoing);
		newPanel.add(send);
		newPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		background.add(BorderLayout.EAST,newPanel);
		background.add(BorderLayout.WEST,nameBox);
		background.add(BorderLayout.NORTH,label);
		
		theFrame.getContentPane().add(background);
	
		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(2);
		grid.setHgap(1);

		mainPanel = new JPanel(grid);
		//mainPanel.add(label);
		background.add(BorderLayout.CENTER,mainPanel);
		
			for(int i=0;i<256;i++)
			{
				JCheckBox c = new JCheckBox();
				c.setSelected(false);
				checkboxList.add(c);
				mainPanel.add(c);

			}//end loop

			setUpMidi();
			theFrame.setBounds(50,50,300,600);
			theFrame.pack();
			theFrame.setVisible(true);
		
			setUpNetworking();
			
			Thread readerThread = new Thread(new IncomingReader());
			readerThread.start();
	}//close method

	public void setUpMidi()
	{
		try
		{	sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ,4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);


		}catch(Exception e){e.printStackTrace();}

	}//close method

	public void buildTrackAndStart()
	{
		int[] trackList = null;

		sequence.deleteTrack(track);
		track = sequence.createTrack();

		for(int i = 0; i<16; i++)
		{
			trackList = new int[16];

			int key = instruments[i];

			for(int j = 0;j<16;j++)
			{
				JCheckBox jc = (JCheckBox) checkboxList.get(j+(16*i));
				if(jc.isSelected())
				{
					trackList[j] = key;
				}
				else
				{
					trackList[j] = 0;
				}
			}//close inner loop

			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
		}//close outer loop

		track.add(makeEvent(192,9,1,0,15));

		try
		{
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);

		}catch(Exception e){e.printStackTrace();}

	}//close buildTrackAndStart method

	public class MyStartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			buildTrackAndStart();
		}
	}//close inner class

	public class MyStopListener implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			sequencer.stop();
		}
	}//close inner class

	public class MyUpTempoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*1.03));

		}
	}//close inner class

	public class MyDownTempoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*.97));

		}
	} // close inner class

	public void makeTracks(int[] list)
	{
		for(int i=0;i<16;i++)
		{
			int key = list[i];

			if(key != 0)
			{
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}//close method

	public MidiEvent makeEvent(int comd,int chan,int one,int two,int tick)
	{
		MidiEvent event = null;
		try
		{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event = new MidiEvent(a,tick);
		}catch(Exception e){e.printStackTrace();}
		return event;
	}

	public void setName(String name)
	{
		nameOfBeat = name;
	}

	public class SaveMenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(theFrame);
			SaveFile(fileSave.getSelectedFile());

		}
	}

	public class NewMenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			for(int i=0;i<256;i++)
			{
				JCheckBox jc = (JCheckBox) checkboxList.get(i);
				jc.setSelected(false);
				checkboxList.set(i,jc);
			}
		}
	}

	public class OpenMenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(theFrame);
			System.out.println("in open Listeer");
			loadFile(fileOpen.getSelectedFile());

		}
	}

	public void loadFile(File file)
	{
		try
		{	ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
			CheckBoxData oneRestore = (CheckBoxData) is.readObject();
			is.close();
			System.out.println("inside loadFile");
			setCheckBoxes(oneRestore.getData());

		}catch(Exception ex){ex.printStackTrace();}
	}

	

	public void SaveFile(File file)
	{	//file should have .ser extension
		try
		{	
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
			CheckBoxData cbd = new CheckBoxData();
			cbd.setData(checkboxList);
			os.writeObject(cbd);
			os.close();
		}catch(IOException ex){ex.printStackTrace();}

	}

	public void setCheckBoxes(ArrayList<Boolean> data)
	{	check = true;
		boolean bool;
		System.out.println("inside setCheckBoxes");
		for(int i=0;i<256;i++)
		{	
			bool = (boolean) data.get(i);
			if(bool==true)
			{	
				checkboxList.get(i).setSelected(true);
			}
			else
			{
				checkboxList.get(i).setSelected(false);
			}
					
		}
		sequencer.stop();
		buildTrackAndStart();

	}
	
	public void setUpNetworking()
	{	
		try{
			sock = new Socket("192.168.1.2",5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("network setup done!!");
			
		}catch(IOException ex){ex.printStackTrace();}
		
	}
	
	public class sendActionListener implements ActionListener
	{
		//ArrayList<Boolean> state;
		JCheckBox jc;
		public void actionPerformed(ActionEvent eve)
		{
			//state = new ArrayList<Boolean>();
			
			for(int i = 0; i<256 ; i++)
			{
				jc = (JCheckBox) checkboxList.get(i);
				
				if(jc.isSelected())
				writer.println("1" + "\n");
				//state.add(true);
				
				else
				writer.println("0" + "\n");
				//state.add(false);
			} //beat packed to send
			
			
			try{
				writer.println(outgoing.getText());
				writer.flush();
			}catch(Exception ex){ex.printStackTrace();}
			outgoing.setText("");
			outgoing.requestFocus();  

		}
	}
	
	
	public class IncomingReader implements Runnable
	{	String message;
		JCheckBox jc;
		public void run()
		{	try{
					int i=0;
					System.out.println("hello");
					jc = new JCheckBox(); 
				while(!((message = reader.readLine()).equals(null)))
				{	
					if(i < 512 )
					{	System.out.print(message);
						if(message.equals("1"))
						{	
							checkboxList.get(i/2).setSelected(true);
						}
						
						else if(message.equals("0"))
						{	System.out.println(i/2);
							checkboxList.get((i/2)).setSelected(false);
						}
					}
					
					else
					{	
						incoming.append(message+ "\n");
						System.out.println(message);
						i=-1;
					}
					
					i++;
					
					
				}
				
				sequencer.start();
			
			}catch(Exception ex){ex.printStackTrace();}
			
		}
	}
	

}//close class

class CheckBoxData implements Serializable
	{		static final long serialVersionUID = 1634977245911346923L;
		ArrayList<Boolean> state = new ArrayList<Boolean>();

		public void setData(ArrayList<JCheckBox> checkboxList)
		{	
			JCheckBox jc;
			for(int i=0;i<256;i++)
			{
					jc = (JCheckBox) checkboxList.get(i);
					if(jc.isSelected())
					state.add(true);
					else
					state.add(false);	
					
			}
		}

		public ArrayList<Boolean> getData()
		{	System.out.println("safely reached in getdata" + state.size());

			return state;
		}

	}
