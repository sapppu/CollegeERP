import java.awt.*;
import java.awt.event.*;
public class AddNumberKey extends Frame implements  KeyListener
{
		TextField tf1,tf2,tf3;
	
		public AddNumberKey()
		{
			tf1=new TextField(10);
			tf2=new TextField(10);
			tf3=new TextField(10);
			setLayout(new FlowLayout());
			add(tf1);
			add(tf2);			
			add(tf3);
	         //Register the event
			tf2.addKeyListener(this);
	
		/*	addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}});*/
		}
		
		public void keyPressed(KeyEvent e)
		{
		  if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
		    	String s1=tf1.getText();
				String s2=tf2.getText();
				int a=Integer.parseInt(s1);
				int b=Integer.parseInt(s2);
				tf3.setText(String.valueOf(a+b));
			}
		}
		public void keyTyped(KeyEvent e)
		{		
		}
		public void keyReleased(KeyEvent e)
	    {
		}
		public static void main(String a[])
		{
			Frame f=new AddNumberKey();
			f.setSize(200,200);
			f.setVisible(true);
		}
}