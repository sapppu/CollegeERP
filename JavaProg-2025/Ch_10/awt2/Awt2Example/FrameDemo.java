import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/* <applet code = "FrameDemo" width=400 height=400>
   </applet>
*/
public class FrameDemo extends Applet implements ActionListener
{
	Button b;
	TextField t;
	public void init()
	{
		Frame f = new Frame("FrameDemo");
		f.setLayout(new GridLayout(2,2));
		f.setSize(200,200);

		b = new Button("Click_me");
		f.add(b);
		b.addActionListener(this);

		t = new TextField(20);
		f.add(t);
		f.show(); // to make window visible which is initially invisible
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == b)
			t.setText("Button Clicked");
	}

}








