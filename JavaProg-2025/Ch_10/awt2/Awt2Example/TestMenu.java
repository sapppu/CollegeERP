 import java.awt.*;
import java.awt.event.*;
class  MyMenu extends Frame
{
	String msg="",drawshape="",fillcolor="";
	public 	MyMenu()
	{
		setSize(300,200);
		setTitle("Menu Application");
		MenuBar mbar=new MenuBar();
		setMenuBar(mbar);

		Menu shape=new Menu("Shape");
		Menu color=new Menu("Color");
		mbar.add(shape);
		mbar.add(color);

		MenuItem shape_rec,shape_sq,shape_circle;
		MenuItem color_red,color_blue,color_green;

		shape.add(shape_rec=new MenuItem("Rectangle"));
		shape.add(shape_sq=new MenuItem("Square"));
		shape.add(shape_circle=new MenuItem("Circle"));

		color.add(color_red=new MenuItem("Red"));
		color.add(color_blue=new MenuItem("Blue"));
		color.add(color_green=new MenuItem("Green"));


		MyMenuHandler handler=new MyMenuHandler(this);
				//MyWindowAdapter adapter=new MyWindowAdapter(this);

		shape_rec.addActionListener(handler);
		shape_sq.addActionListener(handler);
		shape_circle.addActionListener(handler);
		color_red.addActionListener(handler);
		color_blue.addActionListener(handler);
		color_green.addActionListener(handler);

	}
	public void paint(Graphics g)
	{
		/*if (drawshape=="rect" && fillcolor=="red")
			{
			g.setColor(Color.RED);

			g.drawRect(50,50,100,100);
			g.fillRect(51,51,101,101);

		    }


		if (drawshape=="rect" && fillcolor=="green")
			{
			g.setColor(Color.GREEN);
			g.drawRect(50,50,100,100);
			g.fillRect(51,51,101,101);
 		   }

		if (drawshape=="rect" && fillcolor=="blue")
			{
			g.setColor(Color.BLUE);
			g.drawRect(50,50,100,100);
			g.fillRect(51,51,101,101);
 		   }*/

 				if (drawshape=="cir" && fillcolor=="red")
					{
					g.setColor(Color.RED);

					g.drawOval(50,50,100,100);
					g.fillOval(51,51,101,101);

				    }


				if (drawshape=="cir" && fillcolor=="green")
					{
					g.setColor(Color.GREEN);
					g.drawOval(50,50,100,100);
					g.fillOval(51,51,101,101);
		 		   }

				if (drawshape=="cir" && fillcolor=="blue")
					{
					g.setColor(Color.BLUE);
					g.drawOval(50,50,100,100);
					g.fillOval(51,51,101,101);
 		   			}



	}
};


class MyMenuHandler implements ActionListener ,ItemListener
{
	MyMenu mymenu;
	public MyMenuHandler(MyMenu mymenu)
	{
		this.mymenu=mymenu;
	}

	public void actionPerformed(ActionEvent ae)
	{
		String arg=(String)ae.getActionCommand();
		if(arg.equals("Rectangle"))
		{
			mymenu.drawshape="rect";
		}
		if(arg.equals("Circle"))
		{
			mymenu.drawshape="cir";
		}
		if(arg.equals("Red"))
		{
			mymenu.fillcolor="red";
					}
		if(arg.equals("Green"))
		{
			mymenu.fillcolor="green";
		}

		if(arg.equals("Blue"))
		{
			mymenu.fillcolor="blue";
					}

		mymenu.repaint();
	}

	public void itemStateChanged(ItemEvent ie)
	{
		//mymenu.msg="rectangle";
		mymenu.repaint();
	}
}

class TestMenu
{
	public static void main(String arg[])
	{
		MyMenu mm = new MyMenu();
		mm.show();
	}
};