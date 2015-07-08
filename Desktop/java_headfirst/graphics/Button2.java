import javax.swing.*;

public class Button2
{
	public static void main(String[] args)
	{	JFrame frame1= new JFrame();
		JFrame frame2=new JFrame();

		JButton button1=new JButton("button1");
		JButton button2=new JButton("button2");

		//frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// this fuctions acts as master closing button which closes complete program.other wise only particular jframe closes
																	//if u dont put this the program will not get close
		
		frame1.getContentPane().add(button1);
		frame2.getContentPane().add(button2);

		frame1.setSize(300,300);
		frame2.setSize(600,600);

		frame1.setVisible(true);
		frame2.setVisible(true);

	}
}