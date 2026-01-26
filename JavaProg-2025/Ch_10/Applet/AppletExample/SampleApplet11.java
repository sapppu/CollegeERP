// Applet can be viewed from a web browser by writing APPLET tag with the HTML code


import java.applet.*;
import java.awt.*;
//import java.awt.event.*;

public class SampleApplet11 extends Applet
{

	String msg ="";
	public void init()
	{
		msg = msg+"init();";
	}

	public void start()
	{
		msg = msg+"start();";
	}

	public void destroy()
	{
		msg = msg+"destroy();";
	}
	public void stop()
	{
		msg = msg+"stop();";
	}

	public void paint(Graphics g)
	{
		msg = msg+"paint();";
		g.drawString(msg,50,50);
	}
}

/*public class SampleApplet11 extends Applet implements ActionListener
{
	Button b = new Button("Click_me");
	boolean flag = false;

	public void init()
	{
		System.out.println("Applet is initialized");
		add(b);
		b.addActionListener(this);
	}

	public void start()
	{
		System.out.println("Applet is Started");
	}

	public void destroy()
	{
		System.out.println("Applet is destroyed");

	}
	public void stop()
	{
		System.out.println("Applet is Stoped");
	}
	public void actionPerformed(ActionEvent ae)
	{
		flag = true;
		repaint();
	}

	public void paint(Graphics g)
	{
		System.out.println("paint() i called");
		if(flag)
		g.drawString("This is simple applet", 50 ,50);
	}
}*/