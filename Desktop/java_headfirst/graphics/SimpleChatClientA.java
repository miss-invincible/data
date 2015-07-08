import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClientA
{
	JTextField outGoing;
	PrintWriter writer;
	Socket sock;

	public static void main(String[] args)
	{
		SimpleChatClientA run = new SimpleChatClientA();
		run.go();
	}

	public void go()
	{
		JFrame frame= new JFrame();
		outGoing = new JTextField();
		JButton send = new JButton("send");
		send.addActionListener(new SendButtonListener());

		frame.getContentPane().add(BorderLayout.CENTER,outGoing);
		frame.getContentPane().add(BorderLayout.SOUTH,send);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}

	private void setUpNetworking()
	{	try{
			sock = new Socket("127.0.0.1",4900);

		}catch(Exception ex){ex.printStackTrace();}
		

	}

	public class SendButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{	
			try
			{	setUpNetworking();
				String msg = outGoing.getText();
				writer = new PrintWriter(sock.getOutputStream());
				writer.println(msg);
				writer.close();
			}catch(IOException ex){ex.printStackTrace();}

		}
	}
}