import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
/* <applet code = "ChoiceSelection" width=200 height=200> </applet>
*/
public class ChoiceSelection extends Applet implements ItemListener
{
	Choice choice1,choice2;
	Label l1;
	public void init()
	{
		choice1 = new Choice();

		choice1.addItem("Green");
		choice1.addItem("Brown");
		choice1.addItem("Red");
		choice1.addItem("Yellow");
		choice1.addItem("Blue");
		choice1.addItem("Orange");
		choice1.addItem("Rose");

		choice1.addItemListener(this);
		add(choice1);

		choice2 = new Choice();

		choice2.addItem("East");
		choice2.addItem("West");
		choice2.addItem("North");
		choice2.addItem("South");

		choice2.addItemListener(this);
		add(choice2);

		l1=new Label("         ");
		add(l1);

	}

	public void itemStateChanged(ItemEvent ie)
	{
		Choice c = (Choice)ie.getItemSelectable();

		l1.setText(c.getSelectedItem());
	}
}








