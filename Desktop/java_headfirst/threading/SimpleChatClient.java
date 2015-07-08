import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClient
{	JTextArea incoming;
	//JTextField incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;
	
	public static void main(String[] args)
	{
		SimpleChatClient client = new SimpleChatClient();
		client.go();
	}
	
	public void go()
	{	JFrame frame = new JFrame("very simple chat client");
		JPanel mainPanel = new JPanel();
		incoming = new JTextArea(15,20);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		//incoming = new JTextField("checkk");
		incoming.setEditable(false);
		//incoming.append("check");
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(qScroller);
		//mainPanel.add(incoming);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		setUpNetworking();
		JTextField name = new JTextField("enter name");
		mainPanel.add(name);
		Thread readerThread = new Thread(new IncomingReader());	
		//readerThread.setName(name.getText());
		//readerThread.start();
		
		frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		frame.setSize(400,500);
		frame.setVisible(true);
	}
	
	private void setUpNetworking()
	{	
		try
		{	sock = new Socket("127.0.0.1",5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");		
		}catch(IOException ex){ex.printStackTrace();}
		
	}//close networking
	
	public class SendButtonListener implements ActionListener
	{	
		public void actionPerformed(ActionEvent eve)
		{
			try{
				writer.println(outgoing.getText());
				writer.flush();
			}catch(Exception ex){ex.printStackTrace();}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}	//close inner class
	
	public class IncomingReader implements Runnable
	{	
		public void run()
		{	String message;
			try{
				//while((message = reader.readLine()).equals("yo"))
				while(!((message = reader.readLine()).equals(null)))
				{	
					System.out.println("read" + message);
					incoming.append(message + "\n");
					//incoming.setText(message);
				}//close while
				System.out.println("death of thread");
			}catch(Exception ex){ex.printStackTrace();}
		}//close run
	} //close inner class
	
}//close outer class