import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/* <applet code = "PanelDemo" width=200 height=200>
   </applet>
*/
public class PanelDemo extends Applet implements ItemListener
{
	Label label1;
	Checkbox chkb1 , chkb2 , chkb3 , chkb4;
	public void init()
	{
		setLayout ( new BorderLayout());
		Panel pn1 = new Panel();

		chkb1 = new Checkbox("Red",true);
		pn1.add(chkb1);

		chkb2 = new Checkbox("Blue",true);
		pn1.add(chkb2);

		chkb3 = new Checkbox("Green",true);
		pn1.add(chkb3);

		chkb1.addItemListener(this);
		chkb2.addItemListener(this);
		chkb3.addItemListener(this);

		pn1.setBackground(Color.green);
		add(pn1,"North");

		Panel pn2 = new Panel();
		pn2.setLayout(new GridLayout(3,2));
		for(int i=0;i<6;i++)
			pn2.add(new Button("Button"+i));
		add(pn2,"Center");


		Panel p3 = new Panel();
		label1 = new Label("                             ");
		p3.add(label1);
		p3.setBackground(Color.magenta);
		add(p3,"South");

	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getItemSelectable()== chkb1)
		{
			label1.setText("Checkbox 1 Selected");
		}

		if(ie.getItemSelectable()== chkb2)
		{
			label1.setText("Checkbox 2 Selected");
		}

		if(ie.getItemSelectable()== chkb3)
		{
			label1.setText("Checkbox 3 Selected");
		}

	}

}








