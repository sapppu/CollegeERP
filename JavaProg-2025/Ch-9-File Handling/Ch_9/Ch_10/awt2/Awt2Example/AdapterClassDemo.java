
/* Following example uses MouseAdapter class

	In this program , the MyMouseAdapter class extends MouseAdater
	as a result empty implementation
	of all methods declared in the MouseListener interface
	are now available to the MyMouseAdapter class via the MouseAdapter class */

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/* <applet code = AdapterClassDemo width = 300 height=100> </applet> */

public class AdapterClassDemo extends Applet
{
	public void init()
	{
		setBackground(Color.yellow);
		addMouseListener(new MyMouseAdapter(this));

	}
}

class MyMouseAdapter extends MouseAdapter
{
	AdapterClassDemo acd;

	public MyMouseAdapter(AdapterClassDemo acd)
	{
		this.acd = acd;
	}

	public void mousePressed(MouseEvent me)
	{
		acd.setBackground(Color.red);
		acd.repaint();
	}

	public void mouseReleased(MouseEvent e)
	{

		acd.setBackground(Color.green);
		acd.repaint();
	}

}