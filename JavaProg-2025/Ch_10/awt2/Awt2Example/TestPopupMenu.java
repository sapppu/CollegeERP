import java.awt.*;
import java.awt.event.*;

class TestPopupMenu extends  Frame implements ActionListener
{
	PopupMenu pop;
	Button b;
	TestPopupMenu()
	{
		setLayout(new FlowLayout());
	b=new Button("Component");
		add(b);	
		b.addActionListener(this);
		pop=new PopupMenu();
		Menu m1=new Menu("File");
		Menu m2=new Menu("Edit");
		pop.add(m1);
		pop.add(m2);
		add(pop);		
	}
	public void actionPerformed(ActionEvent e)
	{
		pop.show(b,40,40);
	}
	public static void main(String[] args)
	{
		TestPopupMenu mymenu=new TestPopupMenu();
		mymenu.setSize(300,300);
		mymenu.show();
		System.out.println("Hello! World");
	}
}