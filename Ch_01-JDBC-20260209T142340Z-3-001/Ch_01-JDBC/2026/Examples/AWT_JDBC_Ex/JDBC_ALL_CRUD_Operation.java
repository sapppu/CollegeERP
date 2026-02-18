package AWT_JDBC_Ex;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBC_ALL_CRUD_Operation extends Frame implements ActionListener 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel p1,p2,p3;
	TextField tf1,tf2,tf3,tf4,tf5;
	TextArea ta;
	Button b1,b2,b3,b4,b5,b6,b7;
	ResultSet r=null;
	Statement st=null;

	public JDBC_ALL_CRUD_Operation() 
	{

		setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		try
		 {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 }
		catch(ClassNotFoundException e1)
		{
			System.out.println(e1);
		}	
		
		p1 = new Panel();
		p1.setLayout(new BorderLayout());
		ta = new TextArea(" ",15,100);
		p1.add(ta);
		add("North",p1);
		
		p2 = new Panel();
		p2.setLayout(new GridLayout(5,2));
		Label l1= new Label("Enter Search Command");
		p2.add(l1);
		
		tf1 = new TextField(30);
		p2.add(tf1);
		Label l2= new Label("Enter Insert Command");
		p2.add(l2);
		
		tf2 = new TextField(30);
		p2.add(tf2);
		Label l3= new Label("Enter Delete Command");
		p2.add(l3);
		
		tf3 = new TextField(30);
		p2.add(tf3);
		Label l4= new Label("Enter Update Command");
		p2.add(l4);
		
		tf4 = new TextField(30);
		p2.add(tf4);
		Label l5= new Label("Enter Create Command");
		p2.add(l5);
		
		tf5 = new TextField(30);
		p2.add(tf5);
		add("Center",p2);
		
		p3= new Panel();
		
		b1 = new Button("Search");
		b1.addActionListener(this);
		p3.add(b1);
		
		b2 = new Button("Insert");
		b2.addActionListener(this);
		p3.add(b2);
		
		b3 = new Button("Delete");
		b3.addActionListener(this);
		p3.add(b3);
		
		b4 = new Button("Update");
		b4.addActionListener(this);
		p3.add(b4);
		
		b5 = new Button("Create");
		b5.addActionListener(this);
		p3.add(b5);
		
		b6 = new Button("Clear");
		b6.addActionListener(this);
		p3.add(b6);
		
		b7 = new Button("Exit");
		b7.addActionListener(this);
		p3.add(b7);
		
		add("South",p3);
		try 
		{
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS","root","root123");
			st=c.createStatement();
		}
		catch(Exception e2)
		{
			System.out.println("Exception" + e2);
		}
		
	}
	
	public void actionPerformed(ActionEvent ae)
    {
		int i=0;
		if(ae.getSource() == b1)
			i=1;
		if(ae.getSource() == b2)
			i=2;
		if(ae.getSource() == b3)
			i=3;
		if(ae.getSource() == b4)
			i=4;
		if(ae.getSource() == b5)
			i=5;
		if(ae.getSource() == b6)
			i=6;
		if(ae.getSource() == b7)
			i=7;
		
		switch(i)
		{
			case 1:
			try
			{
				ResultSetMetaData meta= null;
				int columns=0;
				String s= tf1.getText();
				r= st.executeQuery(s);
				meta= r.getMetaData();
				while(r.next())
				{
					columns = meta.getColumnCount();
					for(int j=1;j<=columns;j++)
					{
						String data= r.getString(j);
						ta.append(data +"\t\t");
					} 
					ta.append("\n");
				}
			}
			catch(SQLException e3)
			{
				System.out.println("Exception e3"+e3);
			}
			break;
			
			case 2:
				try
				{
					String s= tf2.getText();
					st.executeUpdate(s);
				}
				catch(Exception e4)
				{
					System.out.println("Exception e4"+e4);
				}
				break;
				
			case 3:
				try
				{
					String s= tf3.getText();
					st.executeUpdate(s);
				}
				catch(Exception e5)
				{
					System.out.println("Exception e5"+e5);
				}
				break;
				
			case 4:
				try
				{
					String s= tf4.getText();
					st.executeUpdate(s);
				}
				catch(Exception e6)
				{
					System.out.println("Exception e3"+e6);
				}
				break;
				
			case 5:
				try
				{
					String s= tf5.getText();
					st.executeUpdate(s);
				}
				catch(Exception e7)
				{
					System.out.println("Exception e7"+e7);
				}
				break;
				
			case 6:
				ta.setText(" ");
				tf1.setText(" ");
				tf2.setText(" ");
				tf3.setText(" ");
				tf4.setText(" ");
				tf5.setText(" ");
				break;
				
			case 7:
				System.exit(i);
				break;
		}
    }
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		JDBC_ALL_CRUD_Operation  f=new JDBC_ALL_CRUD_Operation();
		f.setSize(6000,400);
		f.show();
	}
}
		
		
		
