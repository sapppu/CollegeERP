// Example to acccept file address/name from Textfield and display its content in TextArea

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class FileViewDemo extends Frame implements ActionListener
{
	Label l1;
	Button bt;
	TextField tf;
	TextArea ta;
	public FileViewDemo()
	{
		setTitle("ViewFile");
		Panel p1=new Panel();
		p1.setLayout(new FlowLayout());
		ta=new TextArea(10,60);
		p1.add(ta);
		ta.setBackground(Color.yellow);
		ta.setForeground(Color.black);
		
		Panel p2=new Panel();
		p2.setLayout(new FlowLayout());
	        	l1=new Label("File name");
		tf=new TextField(" ",12);
		p2.add(l1);
		p2.add(tf);
		
		tf.setBackground(Color.yellow);
		tf.setForeground(Color.red);
		bt=new Button("View");
		p2.add(bt);

		setLayout(new FlowLayout());
		add(p1);
		add(p2);
		bt.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==bt)
		displayFile();
	}
	
	void displayFile()
	{
		BufferedReader inFile=null;
		String filename=tf.getText().trim();
		String inLine;
		try
		{
			FileReader fr=new FileReader(filename);
			inFile=new BufferedReader(fr);
			inLine=inFile.readLine();
			boolean firstLine=true;

			while(inLine!=null)
			{
				if(firstLine)
				{
					ta.append(inLine);
					firstLine =false;
				}
				else
				{
					ta.append("\n"+inLine);
				}
				inLine=inFile.readLine();
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found : "+ filename);
		}

		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}

		finally
		{
			try
			{
				if(inFile!=null)
				{
					inFile.close();
				}
			}
			catch(IOException e)
			{
				System.out.println("I/O Error encounter.");
			}
		}

	}

	public static void main(String args[])
	{
		FileViewDemo f=new FileViewDemo();
		f.setSize(400,300);
		f.setVisible(true);
	}
}

