
/* In the following Ex. AnonymousClassDemo ,
	the init() method instantiate an anonymous adapter class
	and registers  that object to receive mouse listener events

	Here in this example "new MouseAdapter(){ ----Statements ...} )"
	indicates the compiler that an anonymous inner class
	that extends the MouseAdapter adapter class is being defined

	Within the anonymous inner class the mousePressed() and mouseRelesed()
	methods alone are overriden

*/



import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/* <applet code = AdapterClassDemo width = 300 height=100> </applet> */

public class AnonymousClassDemo extends Applet
{
	public void init()
	{
		setBackground(Color.yellow);
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me)
				{
					setBackground(Color.red);
					repaint();
				}

			public void mouseReleased(MouseEvent me)
				{

					setBackground(Color.green);
					repaint();
				}
		} );

	}
}