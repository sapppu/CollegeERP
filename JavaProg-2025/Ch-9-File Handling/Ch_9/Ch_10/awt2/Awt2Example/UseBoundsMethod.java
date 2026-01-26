import java.awt.*;

class UseBoundsMethod  extends Frame
{
	Label l1;
	TextField t1;
	Button b1;
	UseBoundsMethod()
	{
		setSize(500,500);
		setVisible(true);
		setLayout(null);

		l1=new Label("Enter Number");
		l1.setBounds(100,100,120,10);
		add(l1);
		t1=new TextField(10);
		t1.setBounds(220,100,100,20);
		add(t1);
		//b1=new Button("Ok");

	}

	public static void main(String[] args) 
	{
		UseBoundsMethod obj=new UseBoundsMethod();

	}
}
