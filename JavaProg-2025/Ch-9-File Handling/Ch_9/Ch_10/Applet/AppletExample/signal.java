import java.awt.*;
import java.applet.*;
/*<applet code=signal width=300 Height=300>
</applet>*/
public class signal extends Applet implements Runnable
{
	Thread t;
	int cnt;
	public void init()
	{
		cnt=1;
	}
	public void paint(Graphics g)
	{
		if(cnt==1)
		{
			g.setColor(Color.green);
				g.fillOval(5,5,40,40);
			g.setColor(Color.black);
				g.drawOval(5,55,40,40);
				//g.fillOval(5,55,40,40);
				g.drawOval(5,105,40,40);
				//g.fillOval(5,105,40,40);
		}
		else if(cnt==2)
		{
			g.setColor(Color.black);
				g.drawOval(5,5,40,40);
				//g.fillOval(5,5,40,40);
			g.setColor(Color.yellow);
				g.fillOval(5,55,40,40);
			g.setColor(Color.black);
				g.drawOval(5,105,40,40);
				//g.fillOval(5,105,40,40);
		}
		else if (cnt==3)
		{
			g.setColor(Color.black);
				g.drawOval(5,5,40,40);
				//g.fillOval(5,5,40,40);
				g.drawOval(5,55,40,40);
				//g.fillOval(5,55,40,40);
			g.setColor(Color.red);
				g.fillOval(5,105,40,40);
		}
	}
	public void start()
	{
		t=new Thread(this,"signal");
		t.start();
	}

	public void run()
	{
		for(;;)
		{
			try
			{
				if(cnt==3)
				{
					cnt=1;
				}
				else
				{
					cnt++;
				}
				repaint();
				Thread.sleep(2000);
			}
			catch(InterruptedException e)
			{
				System.out.println(e);
			}
		}
	}
}
