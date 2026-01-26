import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
/* <applet code = "ScrollBar" width=200 height=200>
   </applet>
*/
public class ScrollBar extends Applet implements AdjustmentListener
{
	TextField text1;
	Scrollbar scroll1,scroll2;
	public void init()
	{
		text1 = new TextField(20);
		add(text1);
		scroll1 = new Scrollbar(Scrollbar.HORIZONTAL , 1,10,1,100);
		add(scroll1);
		scroll1.addAdjustmentListener(this);

		scroll2 = new Scrollbar(Scrollbar.VERTICAL, 1,10,1,100);
		add(scroll2);
		scroll2.addAdjustmentListener(this);

	}

	public void adjustmentValueChanged(AdjustmentEvent ae)
	{
		if(ae.getAdjustable() == scroll1)
		{
			scroll1.setValue(scroll1.getValue());
			text1.setText("Horizontal position" + scroll1.getValue());
		}

		if(ae.getAdjustable() == scroll2)
		{
			scroll2.setValue(scroll2.getValue());
			text1.setText("Vertical position" + scroll2.getValue());
		}
	}
}








