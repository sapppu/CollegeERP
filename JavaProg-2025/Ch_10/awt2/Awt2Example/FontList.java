import java.awt.*;
import java.awt.event.*;

class FontList extends Frame implements ItemListener
{
	//List lfont;
	Choice Cfont,Csize;
	int size;
	//CardLayout cl;
	Label lb1;
	Toolkit tk;
	String font_list[];
	String selfont;
	CheckboxGroup cbg;
	Checkbox cb1,cb2,cb3;
	FontList()
	{
		setSize(300,300);
		tk=Toolkit.getDefaultToolkit();	
		font_list=tk.getFontList();
		//lfont=new List(font_list.length,false);
		Cfont=new Choice();
		Csize=new Choice();
		CheckboxGroup cbg = new CheckboxGroup();
		cb1=new Checkbox("Bold", cbg, true);
		cb2=new Checkbox("Italic", cbg, false);
		cb3=new Checkbox("Normal", cbg, false);

		for(int i=0;i<font_list.length;i++)
		{
		//	lfont.add(font_list[i]);
			Cfont.add(font_list[i]);
		}
		for(int i=0;i<36;i+=2)
		{
			Csize.add(String.valueOf(i));
		}
		setLayout(new FlowLayout(FlowLayout.LEFT));
		lb1=new Label("Choose Font                                               ");
		//add(lfont);
		add(Cfont);add(Csize);
		add(cb1);add(cb2);add(cb3);
		add(lb1);
	//	addWindowListener(new inner());
	//	lfont.addActionListener(this);
	//	lfont.addItemListener(this);
	//	Cfont.addActionListener(this);
		Cfont.addItemListener(this);
		Csize.addItemListener(this);

		cb1.addItemListener(this);
		cb2.addItemListener(this);
		cb3.addItemListener(this);
		
		setVisible(true);
		
		
		
	}

	public void itemStateChanged(ItemEvent ie)
	{
		int style=Font.BOLD;
		if(cb1.getState())
			style=Font.BOLD;
		else
		if(cb2.getState())
			style=Font.ITALIC;
		else
		if(cb3.getState())
			style=Font.PLAIN;
		selfont=Cfont.getSelectedItem();
		size=Integer.parseInt(Csize.getSelectedItem());
		lb1.setFont(new Font(selfont,style,size));
		lb1.setText(selfont);

		System.out.println(selfont);
	}
	

	public static void main(String args[])
	{
		new FontList();
	}
}