import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.*;

public class MiniMusicPlayer1
{
	public static void main(String[] args)
	{
		try
		{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			Sequence seq = new Sequence(Sequence.PPQ,4);
			Track track = seq.createTrack();
				int i;
			for(i=5;i<161;i+=4)
			{	
				track.add(makeEvent(144,1,i,100,i));
				track.add(makeEvent(128,1,i,100,i+4));

			}//end loop


			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();


		}catch(Exception ex){ex.printStackTrace();}


	}//close main

	public static MidiEvent makeEvent(int comd,int chan,int one,int two,int tick)
	{
		MidiEvent event = null;
		try
		{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event = new MidiEvent(a,tick);

		}catch(Exception ex){}
		return event;

	}
}//close class