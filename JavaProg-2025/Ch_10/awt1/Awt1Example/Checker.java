// prog. explain the methods of creating and using three checkboxes

// According to selection of checkbox diplay the color within text box

// use of itemStateChanged method to perform the event which occur

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/* <applet code = Checker width=400 height=400> </applet> */

public class Checker extends Applet implements ItemListener
{
	Checkbox c1,c2,c3;
	TextField text1;
	public void init()
	{
		c1=new Checkbox("C1");
		add(c1);
		c1.addItemListener(this);

		c2=new Checkbox("C2");
		add(c2);
		c2.addItemListener(this);

		c3=new Checkbox("C3");
		add(c3);
		c3.addItemListener(this);

		text1=new TextField(20);
		add(text1);

	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getItemSelectable()==c1)
		{
			text1.setText("RED");
		}

		if(ie.getItemSelectable()==c2)
				{
					text1.setText("GREEN");
		}

		if(ie.getItemSelectable()==c3)
		{
					text1.setText("BLUE");
		}
	}
}