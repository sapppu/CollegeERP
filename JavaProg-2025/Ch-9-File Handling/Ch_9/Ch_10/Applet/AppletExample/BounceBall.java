import java.awt.*;
import java.awt.event.*;
import  java.applet.*;
// To obtain graphics context (without using paint() ) by calling getGraphics()(defined by component)
// and then use this graphics context to output to the window
class Ball extends Thread
{
	Canvas box;
	
    int x=0,dx=2,y=0,dy=2;
	
    public Ball(Canvas c)
	{
		box=c;
	}
	public void move()
	{
		Graphics  g = box.getGraphics();
		g.setXORMode(box.getBackground()); // to place new objects XORed onto window by using this method and
		g.fillOval(x,y,10,10);		       // To obtain current setting of of background use getBackround() method
		x += dx;
		y += dy;
		Dimension d = box.getSize(); // Dimension getSize() method retruns the curnet size of a window 
		if(x<0)
		{	
			x=0;
			dx=-dx;
		}
		if(x+10 >=d.width)
		{	
			x=d.width-10;
			dx=-dx;
		}
		if(y<0)
		{	
			y=0;
			dy=-dy;
		}
		if(y+10 >=d.height)
		{	
			y=d.height-10;
			dy=-dy;
		}
		g.fillOval(x,y,10,10);
		//g.dispose();
	}
	public void  run()
	{
		draw();
		for(;;)
		{
			move();
			try{
				Thread.sleep(5);
			}catch(InterruptedException e)
			{  }

		}	
	}
	public void draw()
	{
	 	Graphics g = box.getGraphics();
		g.setColor(Color.blue);
		g.fillOval(x,y,10,10);
		g.dispose();

	}
 }

public class  BounceBall extends Frame implements  ActionListener
{
	Canvas  canvas;	// Canvas encapsulates  blank window upon which you can draw
	Button start,quit;
	public BounceBall()
	{
		setTitle("Bouncing Ball");
		canvas  = new Canvas();
		add(canvas,BorderLayout.CENTER); // adding canvas window
		Panel p = new Panel();
		p.add(start = new Button("Start"));
		p.add(quit = new Button("Quit"));
		add(p,BorderLayout.SOUTH);
		start.addActionListener(this);
		quit.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String msg = ae.getActionCommand();
		if(msg.equals("Start"))
		{
			Ball b = new Ball(canvas);
			b.start();
		}
		if(msg.equals("Quit"))
		{
			System.exit(0);
		}

	}
	public static void main(String as[])
	{
		BounceBall bb = new BounceBall();
		bb.setSize(500,500); // new size of the window is specified by void setsize(int newWidth, int newHeight) method
		bb.show();
	}
}