import java.awt.*;
import java.applet.*;
public class AppletParamDemo extends Applet
{
	Font f;
	String fontname,str;
	public void init()
	{

		fontname=getParameter("font"); // Applet can read value of a parametor by using getParametor() method
		int size=Integer.parseInt(getParameter("size"));
		f=new Font(fontname,Font.BOLD,size);
		str="Passing parameter demo";
	}
	public void paint(Graphics g)
	{

		g.setFont(f);
		g.drawString(str,30,40);
		setBackground(Color.RED);
		g.drawString("Hello Applet" ,30,70);
	}
}