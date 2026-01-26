import java.applet.Applet;
import java.awt.*;
//import java.awt.color.*;
import java.awt.event.*;

/* <applet code = "BorderLayoutDemo" width=200 height=200>
   </applet>
*/
public class BorderLayoutDemo extends Applet implements AdjustmentListener
{
		Scrollbar hs1,hs2,vs1,vs2;
		TextField text1;
		public void init()
		{
			setLayout(new BorderLayout());

			hs1 = new Scrollbar(Scrollbar.HORIZONTAL,1,1,1,200);
			add("North",hs1);
			hs1.addAdjustmentListener(this);

			vs1 = new Scrollbar(Scrollbar.VERTICAL,1,1,1,200);
			add("West",vs1);
			vs1.addAdjustmentListener(this);

			hs2 = new Scrollbar(Scrollbar.HORIZONTAL,1,1,1,200);
			add("South",hs2);
			hs2.addAdjustmentListener(this);

			vs2 = new Scrollbar(Scrollbar.VERTICAL,1,1,1,200);
			add("East",vs2);
			vs2.addAdjustmentListener(this);

			text1 = new TextField(20);
			add("Center",text1);
		}

		public void adjustmentValueChanged(AdjustmentEvent ae)
		{

			if(ae.getAdjustable()== hs1)
			{
				hs1.setValue(hs1.getValue());
				hs2.setValue(hs1.getValue());
				text1.setText("Horizontal Location" + hs1.getValue());
			}

			if(ae.getAdjustable()== vs1)
			{
				vs1.setValue(vs1.getValue());
				vs2.setValue(vs1.getValue());
				text1.setText("Vertical Location" + vs1.getValue());
			}

			if(ae.getAdjustable()== hs2)
			{
				hs2.setValue(hs2.getValue());
				hs1.setValue(hs2.getValue());
				text1.setText("Horizontal Location" + hs2.getValue());
			}

			if(ae.getAdjustable()== vs2)
			{
				vs2.setValue(vs2.getValue());
				vs1.setValue(vs2.getValue());
				text1.setText("Vertical Location" + vs2.getValue());
			}
		}



}








