//Delegation event model with another class

import java.awt.*;
import java.awt.event.*;

class MyFrame extends Frame 
{
	TextField tf;
	Button b1,b2;

  	MyFrame()
	{
		setLayout(new FlowLayout());
		tf=new TextField(20);
		b1=new Button("Continue");
		b2=new Button("Break");
		add(tf);
		add(b1);
		add(b2);
		MyButtonHandler mbh=new MyButtonHandler(this);

		b1.addActionListener(mbh);
		b2.addActionListener(mbh);
		
		addWindowListener(new WindowAdapter()
						{
						public void windowClosing(WindowEvent e){
						System.exit(0);			}
						});

	}
	
}
class MyWindow extends WindowAdapter
{
	public void windowClosing(WindowEvent e){
						System.exit(0);			}
						}	
};
class MyButtonHandler implements ActionListener
{
	MyFrame mf;
	MyButtonHandler(MyFrame temp)
	{
		mf=temp;
	}
	public void actionPerformed(ActionEvent e)  
	{
		String str=e.getActionCommand();
		if(str.equals("Continue"))
			mf.tf.setText("world");
		if(str.equals("Break"))
			mf.tf.setText("Universe");
	}
};
class TestMyFrame
{
 
 public static void main(String args[])
     {
        Frame f = new MyFrame();
        f.setSize(100,100);
        f.show();
     }
};

