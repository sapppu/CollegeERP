import java.applet.Applet;
import java.awt.*;

/* <applet code = "FlowLayoutDemo" width=200 height=200>
   </applet>
*/
public class FlowLayoutDemo extends Applet
{
	public void init()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		for(int i=0;i<20;i++)
		{
			add(new Button(" " +i));
		}

	}

}








