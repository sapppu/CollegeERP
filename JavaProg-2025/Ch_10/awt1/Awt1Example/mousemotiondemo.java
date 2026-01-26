import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/*
<applet code = mousemotiondemo width=300 height=300>
</applet>*/

public class mousemotiondemo extends Applet implements MouseMotionListener
{
	String msg = "";
	int x=10,y=20;
	public void init()
	{
		addMouseMotionListener(this);
	}

	public void mouseDragged(MouseEvent e)
	{
		x=e.getX();
		y=e.getY();
		msg="";
		showStatus("Dragging mouse at" + x + ","+y);
		repaint();
	}

	public void mouseMoved(MouseEvent e)
	{
		x=e.getX();
		y=e.getY();
		showStatus("moving mouse at " + x + ","+y);
		repaint();
	}
	public void paint(Graphics g)
	{
		g.drawString(msg,x,y);
	}
}