import javax.swing.*;
import java.awt.event.*;	//ActionListener and ActionEvent lives in this package

public class ButtonActionListner implements ActionListener // this says "an instance of ButtonActionListner IS-A ActionListener". as the button gives itd eventd only to ActionListener implementers.
{	JButton button;
	public static void main(String[] args)
	{
		ButtonActionListner gui = new ButtonActionListner(); //spelling of listener is wrong in the name of the class thats why the diferences :)
		gui.go();

	}

	public void go()
	{
		JFrame frame = new JFrame();
		button = new JButton("i m more powerful now:click me");
		
		button.addActionListener(this);		//it says to button "add me to yor listener list".The argument passed must be an object from the class that implements ActionListener.

		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent event)	 //here we are implemeting ActionListener's actionPerformed() method.It is event-handling method
	{
		button.setText("i love u :*");
	}
	//button call this method to tell us that an event hasd happened.it also sends us an ActionEvent object event but we dont need it.We just want to know that event has occured which is satisfied by the calling of this method.
	//event object holds the data about the event.
}