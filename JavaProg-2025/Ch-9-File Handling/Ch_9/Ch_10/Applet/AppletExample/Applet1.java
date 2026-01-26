
import java.awt.*;
import java.applet.*;

public class Applet1 extends Applet
{

	public void init()
	{
		System.out.println("am in init");
	}
	public void start()
	{
		System.out.println("am in start");
	}
	public void paint(Graphics g)
	{
		setBackground(Color.pink);

		g.drawString("mayur",30,40);

		g.setColor(Color.green);

		g.drawArc(120,20,560,560,0,180);

		int n=5;

		int xdata[] = {10,60,90,140,190};
		int ydata[] = {100,10,140,90,10};

		g.setColor(Color.red);
		g.drawPolygon(xdata,ydata,n);

	}

}