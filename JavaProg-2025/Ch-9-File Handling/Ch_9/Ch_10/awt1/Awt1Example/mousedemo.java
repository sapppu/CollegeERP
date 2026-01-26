import java.awt.*;
import java.applet.*;
// Handling Mouse events

import java.awt.event.*;

/* <applet code="mousedemo" width = 300 height=100> </applet> */

public class mousedemo extends Applet implements MouseListener
{
	String msg="";
	int x=10,y=20;
	public void init()
	{
		addMouseListener(this);

	}

	public void mouseClicked(MouseEvent e)
	{
		msg = "Mouse Clicked";
		repaint();
	}

	public void mouseEntered(MouseEvent e)
	{
		msg = "Mouse Entered";
		repaint();
	}

	public void mouseExited(MouseEvent e)
	{
		msg = "Mouse Excited";
		repaint();
	}

	public void mousePressed(MouseEvent e)
	{
		msg = "Mouse Pressed";
		repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		msg = "Mouse Released";
		repaint();
	}

	public void paint(Graphics g)
	{
		g.drawString(msg,x,y);
	}
}