import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/*
<applet code = keyboarddemo width=300 height=300>
</applet>*/

public class keyboarddemo extends Applet implements KeyListener
{
	String msg = "";
	int x=10,y=20;
	public void init()
	{
		addKeyListener(this);
		requestFocus(); // request for input focus defined by component class
	}

	public void keyPressed(KeyEvent ke)
	{
		showStatus("key down");
	}

	public void keyReleased(KeyEvent ke)
	{
		showStatus("Key Up");
	}

	public void keyTyped(KeyEvent ke)
	{
		msg+=ke.getKeyChar();
		repaint();
	}
	public void paint(Graphics g)
	{
		g.drawString(msg,x,y);
	}
}