import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
/* <applet code = ListEvents width=200 height=200>
*/
public class ListEvents extends Applet implements ActionListener, ItemListener
{
	TextArea txtarea;
	List list1;
	public void init()
	{
		list1 = new List();

		list1.add("Oxygen");
		list1.add("Hydrogen");
		list1.add("Potyasium");
		list1.add("Phosphate");
		list1.add("Carbon");
		list1.add("Phosphorus");
		list1.addActionListener(this);
		list1.addItemListener(this);

		add(list1);

		txtarea = new TextArea(10,20);
		add(txtarea);
	}

	public void actionPerformed(ActionEvent ae)
	{
		txtarea.append("ActionEvent   " + ae.getActionCommand() + "\n");
	}

	public void itemStateChanged(ItemEvent ie)
	{
		List l1 = (List)ie.getItemSelectable();

		txtarea.append("ItemEvent     " + l1.getSelectedItem() + "\n");
	}
}








