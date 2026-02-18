package AWT_JDBC_Ex;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Jdbc_View_Record 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		frm  f=new frm();
		f.setSize(500,500);
		f.setVisible(true);
	}

}


class frm extends Frame implements ActionListener
{

	Label l1,l2,l3;
	Button b1;
	TextField tf1,tf2,tf3;
	
	Connection cn;
	PreparedStatement ps;
	ResultSet rs;

	
	public frm()
	{
	
		setLayout(null);

		l1=new Label("EmpID");
		l1.setBounds(50,50,50,50);
		l2=new Label("Empname");
		l2.setBounds(50,250,50,50);
		l3=new Label("Age");
		l3.setBounds(50,350,50,50);

		b1=new Button("View_Record");

		tf1=new TextField(10);
		tf1.setBounds(50,150,150,150);
		tf2=new TextField(10);
		tf2.setBounds(50,250,150,150);
		tf3=new TextField(10);
		tf3.setBounds(50,350,150,150);

		add(l1);
		add(l2);
		add(l3);
		add(b1);
		add(tf1);
		add(tf2);
		add(tf3);
	
		b1.addActionListener(this);
		
		setLayout(new FlowLayout());

		try
		{
				Class.forName("com.mysql.cj.jdbc.Driver");
				cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS","root","root123");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

     public void actionPerformed(ActionEvent ae)
     {
	
    	 if(ae.getSource()==b1)
    	 {
    		 try
    		 {
    			
    			 ps=cn.prepareStatement("select * from emp where id=?");
    			 ps.setString(1,tf1.getText());// 1 indicate first argument: passing one value to the placeholder
    			 rs=ps.executeQuery();

    			 while(rs.next())
    			 {
    				 tf2.setText(rs.getString(2));
    				 tf3.setText(rs.getString(3));
    			 }
    		 }
    		 catch(Exception e)
    		 {
    			 System.out.println(e);
    		 }
    	 }

	}
}

