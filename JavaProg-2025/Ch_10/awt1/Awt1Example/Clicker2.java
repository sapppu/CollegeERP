/*
- With the help of ActionListener(or its object) we can receive action

- And with the help of addActionListener(ActionListener a1) listener can register the event

- And with the help of removeActionListener(ActionListener a1) listener can unregister the event 
*/

//import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.Frame.*;
public class Clicker2 extends Frame implements ActionListener
{
	TextField txt1;
	Button button1,button2;
	public Clicker2()
	{
		txt1 = new TextField(20);
		add(txt1);
		button1 = new Button("Welcome");
		button1.setBackground(Color.red);
		add(button1);
		button1.addActionListener(this);// OR void addActionListener(ActionListener a1)

		button2 = new Button("Java");
		button2.setBackground(Color.green);
		add(button2);
		button2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==button1)
		{
			txt1.setText("WELCOME");
		}

		if(ae.getSource()==button2)
		{
			txt1.setText("JAVA");
		}

	}

	public static void main(String args[])
	{
		Clicker2  f=new Clicker2();
		f.setSize(4000,3000);
		f.setVisible(true);
	}
}