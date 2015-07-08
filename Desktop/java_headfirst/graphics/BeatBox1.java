import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.sound.midi.*;
import java.util.*;


public class BeatBox1 
{	ArrayList<JCheckBox> checkboxList;
	JLabel[] label;
	JButton button1,button2,button3,button0;
	Sequence sequence;
	Sequencer sequencer;
	Track track;
	JFrame theframe;
	JPanel mainPanel;

	String[] instrumentNames = {"BAss Drum","Closed Hi-Hat","Open Hi-Hat","Hi Bongo","MAracas","Wistle","Low Conga","Cowbell","Vibraslap","Low-mid Tom","High Agogo","Open Hi Conga"};
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	public static void main(String[] args)
	{
		BeatBox1 bb1 = new BeatBox1();
		bb1.buildGUI();
	}

	public void buildGUI()
	{	theframe = new JFrame("Cyber BeatBOx");
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);

		//button = new JButton[5];
		button0=new JButton();
		button1=new JButton();
		button2=new JButton();
		button3=new JButton();
		button1.setText("STOP");
		button2.setText("UPTEMPO");
		button3.setText("DOWNTEMPO");
		button0.setText("START");

		button0.addActionListener(new ButtonActionListener0()); 
		button1.addActionListener(new ButtonActionListener1());
		button2.addActionListener(new ButtonActionListener2());
		button3.addActionListener(new ButtonActionListener3());

		buttonBox.add(button0);
		buttonBox.add(button1);
		buttonBox.add(button2);
		buttonBox.add(button3);

		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for(int i=0;i<16;i++)
		{
			nameBox.add(new Label(instrumentNames[i]));
		}

		background.add(BorderLayout.EAST,buttonBox);
		background.add(BorderLayout.WEST,nameBox);

		theframe.getContentPane().add(background);

		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(2);
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

		theframe.setBounds(50,50,300,300);
		theframe.pack();
		theframe.setVisible(true);

	}//close method


	class ButtonActionListener0 implements ActionListener	
	{
		public void actionPerformed(ActionEvent event)
		{	
			buildTrackAndStart();
		}
	}
	class ButtonActionListener1 implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			sequencer.stop();
		}
	}//close inner class
	class ButtonActionListener2 implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{	float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*1.03));
			
		}
	}//close inner class
	class ButtonActionListener3 implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*.97));
		}
	}//close inner class

	public void setUpMidi()
	{
		try
		{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ,4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		}catch(Exception e){e.printStackTrace();}

	}//closing method

	public void buildTrackAndStart()
	{
		int[] trackList = null;

		sequence.deleteTrack(track);
		track = sequence.createTrack();

		for(int i = 0;i<16;i++)
		{	
			trackList = new int[16];

			int key = instruments[i];

			for(int j=0;j<16;j++)
			{
				JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
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

		}//closer outer loop

		track.add(makeEvent(192,9,1,0,15));
		try
		{
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);

		}catch(Exception e){e.printStackTrace();}

	}//close buildTrckAndStart method

	public void makeTracks(int[] list)
	{
		for(int i=0;i<16;i++)
		{
			int key = list[i];

			if(key!=0)
			{
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}//closing method

	public MidiEvent makeEvent(int comd,int chan,int one,int two,int tick)
	{
		MidiEvent event = null;
		try
		{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event = new MidiEvent(a,tick);

		}catch(Exception e){e.printStackTrace(); }
		return event;
	}//close class	

}