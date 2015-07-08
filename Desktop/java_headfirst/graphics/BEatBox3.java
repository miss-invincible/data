import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox3 implements Serializable
{	//int count;
	//ArrayList<BeatBox3> savedBeats;
	transient Queue<BeatBox3> savedBeats;
	JPanel mainPanel;
	transient ArrayList<JCheckBox> checkboxList;
	transient Sequencer sequencer;
	transient Sequence sequence;
	Track track;
	JFrame theFrame;
	transient JButton saveIt;
	String nameb;
	JTextField nameBeat;
	transient MySavedBeat tt;
	/*String[] instrumentNames = {"Bass Drum","Closed Hi-Hat","Open Hi-Hat","Acoustic Snare","Crash Cymbal",
	"Hand Clap","High Tom","Hi Bongo","Marcas","Whistle",
	"Cowbell","Vibraslap","Low-mid Tom","High Agogo","Open Hi Conga","awe"};*/
	String[] instrumentNames = {"Bass Drum","Closed Hi-Hat","Open Hi-Hat","Acoustic Snare","Crash Cymbal","Hand Clap","High Tom","Hi Bongo","Maracas","Whistle","Low Conga","Cowbell","Vibraslap","Low-mid Tom","High Agogo","Open Hi Congo"};
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	static BeatBox3 bb;
	public static void main(String[] args)
	{
		bb = new BeatBox3();
		bb.buildGUI();
		

	}	

	public void buildGUI()
	{	tt = new MySavedBeat();
		theFrame = new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);

		JButton start = new JButton("start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);

		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);

		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);

		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);

		JButton saveIt = new JButton("Save Beat");
		saveIt.addActionListener(tt);
		buttonBox.add(saveIt);

		nameBeat = new JTextField("enter file name");
		buttonBox.add(nameBeat);

		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for(int i = 0;i<16;i++)
		{
			nameBox.add(new Label(instrumentNames[i]));

		}

		background.add(BorderLayout.EAST,buttonBox);
		background.add(BorderLayout.WEST,nameBox);

		theFrame.getContentPane().add(background);

		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(1);
		grid.setVgap(2);

		mainPanel = new JPanel(grid);
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

	public class MyStartListener implements ActionListener,Serializable
	{
		public void actionPerformed(ActionEvent a)
		{	
			buildTrackAndStart();
		}
	}//close inner class

	public class MyStopListener implements ActionListener,Serializable
	{
		public void actionPerformed(ActionEvent a)
		{
			sequencer.stop();
		}
	}//close inner class

	public class MyUpTempoListener implements ActionListener,Serializable
	{
		public void actionPerformed(ActionEvent a)
		{
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*1.03));

		}
	}//close inner class

	public class MyDownTempoListener implements ActionListener,Serializable
	{
		public void actionPerformed(ActionEvent a)
		{
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*.97));

		}
	} // close inner class

	public class MySavedBeat implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{	

			int c=0;
			nameBeat.setText("");
			nameb=nameBeat.getText();
			BeatBox3 temp;
			savedBeats = new LinkedList<BeatBox3>();

			ObjectInputStream is ;
			File f = new File("BeatBox3.ser");
			try
			{
				if(f.exists())
				{	is = new ObjectInputStream(new FileInputStream("BeatBox3.ser"));
					try{
					while(true)
					{
							
							BeatBox3 beatRestore = (BeatBox3) is.readObject();
							savedBeats.add(beatRestore);
							c++;
							System.out.println("working fine");
					}
					}catch(EOFException e){is.close();}
				}

			}catch(Exception ex){ex.printStackTrace();}


			try
			{	
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("BeatBox3.ser"));
				while(c>0)
				{	temp=savedBeats.poll();
					os.writeObject(temp);
					System.out.println("done");
					c--;
				}
				os.writeObject(bb);
				os.close();
				
			}catch(IOException ex){ex.printStackTrace();}
			
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

	public void playAgain()
	{	try
		{
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);

		}catch(Exception e){e.printStackTrace();}

	}
}//close class
