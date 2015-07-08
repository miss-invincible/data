import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox2
{	


	public static void main(String[] args)
	{
		BeatBox2 bb = new BeatBox2();
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
	public void buildGUI()
	{

		theFrame = new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		//JPanel background = new JPanel(layout);
		WallPaper background = new WallPaper(layout);
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
		ImageIcon imgThisImg = new ImageIcon("inst.jpg");
		
		for(int i = 0;i<16;i++)
		{	JLabel lbl = new JLabel(instrumentNames[i]);
			lbl.setIcon(imgThisImg);
			nameBox.add(lbl);

		}

		background.add(BorderLayout.EAST,buttonBox);
		background.add(BorderLayout.WEST,nameBox);
		background.add(BorderLayout.NORTH,label);

		theFrame.getContentPane().add(background);

		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(1);

		mainPanel = new JPanel(grid);
		//WallPaper mainPanel = new WallPaper(grid);
		//mainPanel.setColor(Color.BLACK);
		//wall.add(label);
		background.add(BorderLayout.CENTER,mainPanel);
		
			for(int i=0;i<256;i++)
			{
				JCheckBox c = new JCheckBox();
				c.setSelected(false);
				checkboxList.add(c);
				mainPanel.add(c);

			}//end loop

			setUpMidi();
			theFrame.setBounds(50,50,300,300);
			theFrame.pack();
			theFrame.setVisible(true);
		
		

	}//close method
	
	public class WallPaper extends JPanel
	{
		public WallPaper(BorderLayout gd)//(GridLayout gd)//
		{
			super(gd);
		}
	
		public void paintComponent(Graphics g)
		{	System.out.println("hi");
			g.setColor(Color.BLACK);
			Image img = new ImageIcon("music.jpg").getImage();
			g.drawImage(img,3,4,this); // this tell the relative postion of the pic wrt to jpanel and NOT RELATIVE TO JFRAME
		}
	}
	
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
