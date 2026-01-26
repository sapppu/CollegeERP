import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class FrameApplication extends Frame implements ActionListener
{
	Button b;
	TextField t;
	public FrameApplication()
	{
		t = new TextField(20);
		add(t);
		b = new Button("Click_me");
		add(b);
		b.addActionListener(this);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
		setVisible(true);
	}


	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == b)
			t.setText("Button Clicked");
	}

	public static void main(String args[])
	{
		FrameApplication fa =new FrameApplication();
		fa.setSize(400,400); // Frame size is set and then it show on the screen
		fa.setLayout(new FlowLayout());
		fa.show();
	}

}









