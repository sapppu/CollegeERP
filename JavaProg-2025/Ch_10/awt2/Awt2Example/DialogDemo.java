import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/* <applet code = "DialogDemo" width=400 height=400>
	<param name = width value = 200>
	<param name = height value = 150>
   </applet>
*/
public class DialogDemo extends Applet implements ActionListener
{
	Button b;
	TextField t;
	public void init()
	{
		int width = Integer.parseInt(getParameter("width"));
		int height = Integer.parseInt(getParameter("height"));
		Frame f = new Frame("DialogDemo");
		f.resize(width,height);
		f.show();
		Dialog d = new Dialog(f,"First Dialog",false);
		d.setLayout(new GridLayout(2,2));
		d.setSize(200,200);

		b = new Button("Click_me");
		d.add(b);
		b.addActionListener(this);

		t = new TextField(20);
		d.add(t);
		d.show(); // to make window visible which is initially invisible
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == b)
			t.setText("Button Clicked");
	}

}








