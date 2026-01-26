import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/* <applet code = "CardLayoutDemo" width=200 height=200>
   </applet>
*/
public class CardLayoutDemo extends Applet implements ActionListener
{
	Button b1,b2,b3,b4;
	Panel buttonPanel;
	CardLayout buttonCardLayout;
	public void init()
	{
		buttonPanel = new Panel();
		add(buttonPanel);

		buttonCardLayout = new CardLayout();
		buttonPanel.setLayout(buttonCardLayout);

		b1 = new Button("FirstButton");
		b1.addActionListener(this);
		buttonPanel.add(b1,"FirstButton");

		b2 = new Button("SecondButton");
		b2.addActionListener(this);
		buttonPanel.add(b2,"SecondButton");

		b3 = new Button("ThirdButton");
		b3.addActionListener(this);
		buttonPanel.add(b3,"FirstButton");

		b4 = new Button("FourthButton");
		b4.addActionListener(this);
		buttonPanel.add(b4,"FirstButton");
	}

	public void actionPerformed(ActionEvent ae)
	{
		buttonCardLayout.next(buttonPanel);
	}

}








