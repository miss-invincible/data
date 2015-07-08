import javax.swing.*;

public class Button1
{
	public static void main(String[] args)
	{
		JFrame frame1 = new JFrame();					//	code to bring Jframe
		JButton button1 = new JButton("CLICK ME");		//code to create button and passing text to be written on constructor
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//putting close operation on red cross
		frame1.getContentPane().add(button1);					//setting button on jframe
		frame1.setSize(500,500);								//giving the size to jframe
		frame1.setVisible(true);								//making jframe visible
	}
	
}