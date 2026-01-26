// Example of Passing Parametor to Applet

import java.io.*;
import java.awt.*;
import java.applet.*;
/*
<applet code ="ParamDemo" width = 300 height=300>
<param name= fontaname value="Courier">
<param name= fontsize value="14">
<param name= leading value="2">
<param name= accountenabled value="true">
</applet>
*/

public class ParamDemo extends Applet
{
	String fontname;
	int fontsize;
	float leading;
	boolean active;

	public void start()
	{
		String param;
			fontname = getParameter("fontname");
			if(fontname==null)
			fontname="Not Found";

		param = getParameter("fontsize");
		try{
			if(param != null) // if not found
			fontsize=Integer.parseInt(param);
			else
			fontsize=0;
			}
		catch(NumberFormatException e)
		{
			fontsize= -1;
		}

		param = getParameter("leading");
		try{
			if(param != null)
			leading=Float.valueOf(param).floatValue();
			else
			leading=0;
			}
		catch(NumberFormatException e)
		{
			leading = -1;
		}

		param = getParameter("accountenabled");
			if(param != null)
			active = Boolean.valueOf(param).booleanValue();

	}

	public void paint(Graphics g)
	{
		g.drawString("font name :- "+ fontname,0,10);
		g.drawString("font size :- "+ fontsize,0,25);
		g.drawString("Leading :- "+ leading,0,40);
		g.drawString("Account active :- "+ active,0,60);

	}

}