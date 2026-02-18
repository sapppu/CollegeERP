import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class frm extends Frame implements ActionListener
{

	Label l1,l2,l3;
	Button b1;
	TextField tf1,tf2,tf3;
	
	Connection cn;
	PreparedStatement ps;
	ResultSet rs;

	
	public void frm()
	{
	
	setLayout(null);

	l1=new Label("Emp no");
	l1.setBounds(50,50,50,50);
	l2=new Label("Emp name");
	l3=new Label("Dept id");

	b1=new Button("show");

	tf1=new TextField(10);
	tf2=new TextField(10);
	tf3=new TextField(10);

	add(l1);
	add(l2);
	add(l3);
	add(b1);
	add(tf1);
	add(tf2);
	add(tf3);
	
	b1.addActionListener(this);

	try
	{
	 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	cn=DriverManager.getConnection("jdbc:odbc:mydsn7");
	}
	catch(Exception e)
	{
	}
	}


     public void actionPerformed(ActionEvent ae)
	{
	
	if(ae.getSource()==b1)
	{
	try
	{
	
	ps=cn.prepareStatement("select * from emp where emp_no=?");
	ps.setString(1,tf1.getText());
	
	rs=ps.executeQuery();

	while(rs.next())
	{
	tf3.setText(rs.getString(2));
	tf3.setText(rs.getString(3));
	}
	}
	catch(Exception e)
	{
	}
	}

	}
}

public class jdbc7
{
public static void main(String a[]) throws Exception
	{
	frm  f=new frm();
	f.setSize(500,500);
	f.setVisible(true);
	}
}
	