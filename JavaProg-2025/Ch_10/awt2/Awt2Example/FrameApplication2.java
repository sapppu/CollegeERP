
/* Program to change Borderlayout to gridlayout */

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

class Frame11 extends Frame implements ActionListener
{
	public Frame11()
	{
		
		Frame f = new Frame("FrameDemo");
		f.setSize(200,200);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
		borderlayout();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		gridlayout();
	}

	public void borderlayout()
	{
		Button e,w,n,s;
		e=new Button("East");
		w=new Button("West");
		n=new Button("North");
		s=new Button("South");
		TextArea txt1=new TextArea("Press Any Button To Change Border Layout to Grid Layout") ;
		add("Center",txt1);
		//add(new TextArea("Press To Change to Grid Layout"));
		//BorderLayout.CENTER;
		//setLayout(new BorderLayout(BorderLayout.CENTER));
		//add("West",vs1);
		add(e,BorderLayout.EAST);
		add(w,BorderLayout.WEST);
		add(n,BorderLayout.NORTH);
		add(s,BorderLayout.SOUTH);

		e.addActionListener(this);
		w.addActionListener(this);
		n.addActionListener(this);
		s.addActionListener(this);
		
		setVisible(true);
	}

	public void gridlayout()
	{
		setLayout(new GridLayout(3,4));

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

		setVisible(true);
	}	

}

public class FrameApplication2
{

	public static void main(String args[])
	{
		Frame11 fa = new Frame11();
		//fa.setSize(400,400); // Frame size is set and then it show on the screen
		//fa.setLayout(new FlowLayout());
		//fa.show();
	}

}

