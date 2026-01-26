import javax.swing.*;
//import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;


class MyColorDialog extends JDialog implements ActionListener
{
	Color c;
	JRadioButton chred, chgreen, chblue, chblack, chgray, chorange, chpink;
	JButton btok, btcancel;
	
	MyColorDialog(Frame f, String title)
	{
		super(f,title);
		chred = new JRadioButton("Red");
		chgreen = new JRadioButton("Green");
		chblue = new JRadioButton("Blue");
		chblack = new JRadioButton("Black");
		chgray = new JRadioButton("Gray");
		chorange = new JRadioButton("Orange");
		chpink = new JRadioButton("Pink");

		chred.setBackground(Color.red);
		chgreen.setBackground(Color.green);
		chblue.setBackground(Color.blue);
		chblack.setBackground(Color.black);
		chgray.setBackground(Color.gray);
		chorange.setBackground(Color.orange);
		chpink.setBackground(Color.pink);

		btok = new JButton("OK");
		btok.addActionListener(this);
		btcancel = new JButton("Cancel");
		btcancel.addActionListener(this);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		/*p1.setLayout(new GridLayout(2,3));
		p2.setLayout(new FlowLayout());*/
		p1.add(chred);
		p1.add(chgreen);
		p1.add(chblue);
		p1.add(chblack);
		p1.add(chpink);
		p1.add(chgray);
		p2.add(btok);
		p2.add(btcancel);

		c.add(p1);
		c.add(p2,BorderLayout.SOUTH);
		setSize(400,400);
		show();
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==btok)
		{
		}
		else
		{
			c=null;
			this.dispose();
		}
	}
	public static void main(String args[])
	{
		new MyColorDialog(new Frame(),"Hello");
		System.out.println("Hello World! By Deepak Kalra");
	}
}