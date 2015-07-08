import javax.sound.midi.*;
public class MiniMiniMusicApp{
	
	public static void main(String[] args){
	MiniMiniMusicApp mini = new MiniMiniMusicApp();
	int instrument = Integer.parseInt(args[0]);
	int note = Integer.parseInt(args[1]);
	mini.play(instrument,note);
	System.out.println("hmmm");

	
	}
	public void play(int instrument,int note){
	try{
		Sequencer player = MidiSystem.getSequencer();
		player.open();
		
		Sequence seq = new Sequence(Sequence.PPQ,4);
		
		Track track = seq.createTrack();

		ShortMessage a = new ShortMessage();
		a.setMessage(144,instrument,44,120);
		MidiEvent noteOn = new MidiEvent(a,1);
		track.add(noteOn);
		System.out.println("hi");

		ShortMessage b = new ShortMessage();
		b.setMessage(128,1,note,100);
		MidiEvent noteOff = new MidiEvent(b,16);
		track.add(noteOff);
		System.out.println("hi2");

		player.setSequence(seq);
		player.start();
		System.out.println("hi");
		
	}
	catch(Exception ex){
		ex.printStackTrace();
		}
	}
}
			 