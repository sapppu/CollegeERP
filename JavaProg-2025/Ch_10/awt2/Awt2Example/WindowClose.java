import java.awt.*;
import java.awt.event.*;
class WindowClose extends Frame
{
	WindowClose ()
	{
		setVisible(true);		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
		setSize(500,500);
	}
	public static void main(String a[])
	{
		new WindowClose();
	}
};


