import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class ExternalBeat //implements Queue<BeatBox2>
{	String nameb;
	Queue<BeatBox2> savedBeats;
	JTextField textBox;
	BeatBox2 bb;

	public static void main(String[] args)
	{
		ExternalBeat ebt = new ExternalBeat();
		ebt.start();
	}

	public void start()
	{	JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton openBeatBox = new JButton("Open Beat Box");
		openBeatBox.addActionListener(new OpenBeatBoxListener());
		panel.add(openBeatBox);

		JButton saveBeat = new JButton("Save the Beat");
		saveBeat.addActionListener(new SaveMyBeat());
		panel.add(saveBeat);

		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);

		textBox = new JTextField("enter file name");
		panel.add(textBox);		

	}

	public class OpenBeatBoxListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			bb = new BeatBox2();
			bb.buildGUI();
		} 
	}

	

	public class SaveMyBeat implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{	

			int c=0;
			nameb=textBox.getText();
			bb.setName(nameb);
			BeatBox2 temp;
			savedBeats = new LinkedList<BeatBox2>();

			ObjectInputStream is ;
			File f = new File("BeatBox3.ser");
			try
			{
				if(f.exists())
				{	is = new ObjectInputStream(new FileInputStream("BeatBox3.ser"));
					try{
					while(true)
					{
							
							BeatBox2 beatRestore = (BeatBox2) is.readObject();
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
	}
}