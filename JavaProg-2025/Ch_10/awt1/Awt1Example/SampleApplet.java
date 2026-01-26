
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/* <APPLET code = "SampleApplet" width = 300 height = 300>
</APPLET> */

public class SampleApplet extends Applet implements ActionListener
{
	Button b = new Button("Click_me");
	boolean flag = false;

	public void init()
	{
		System.out.println("Applet is initialized");
		add(b);
		b.addActionListener(this); // Registered an event which occurs
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
}