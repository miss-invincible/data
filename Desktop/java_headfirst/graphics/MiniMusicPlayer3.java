import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer3
{
	static JFrame f = new JFrame("My first Music Vedio");
	static MyDrawPanel ml;

	public static void main(String[] args)
	{
		MiniMusicPlayer3 mini = new MiniMusicPlayer3();
		mini.go();
	}//close method

	public void setUpGui()
	{
		ml = new MyDrawPanel();
		f.getContentPane().add(ml);
		f.setSize(600,600);
		//f.setContentPane(ml);
		//f.setBounds(30,30,300,300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//close method

	public void go()
	{
		setUpGui();

		try
		{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addControllerEventListener(ml,new int[] {127} );
			Sequence seq = new Sequence(Sequence.PPQ,4);
			Track track = seq.createTrack();

			int r=0;
			
				for(int i=0;i<=260;i+=4)
				{
					r=(int) ((Math.random()*50)+1);
					track.add(makeEvent(144,1,r,100,i));
					track.add(makeEvent(176,1,127,0,i));
					track.add(makeEvent(128,1,r,100,i+2));

				}//end loop
				sequencer.setSequence(seq);
				sequencer.start();
				sequencer.setTempoInBPM(220);

				
	
		
			

		}catch(Exception ex){ex.printStackTrace();}

	}//close method

	public MidiEvent makeEvent(int comd,int chan,int one,int two,int tick)
	{
		MidiEvent event = null;
		try
		{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event = new MidiEvent(a,tick);

		}catch(Exception e){}

		return event;
	}//close function

	class MyDrawPanel extends JPanel implements ControllerEventListener
	{
		boolean msg = false;

		public void controlChange(ShortMessage event)
		{
			msg = true;
			repaint();
		}//close method

		public void paintComponent(Graphics g)
		{
			if(msg)
			{
				Graphics2D g2 = (Graphics2D) g;

				int r = (int) (Math.random()*250);
				int gr = (int) (Math.random()*250);
				int b = (int) (Math.random()*250);

				g2.setColor(new Color(r,gr,b));

				int ht = (int) ((Math.random()*120)+10);
				int width = (int) ((Math.random()*120)+10);
				int x = (int) ((Math.random()*540)+10);
				int y = (int) ((Math.random()*540)+10);
				g.fillRect(x,y,ht,width);
				msg=false;
			}//close if
		}//close method
	}//close inner class
}//close outer class