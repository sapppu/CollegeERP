
package AWT_JDBC_Ex;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Jdbc_TextField_TextArea extends Frame implements ActionListener
{
	Label la;
	TextField tf;
	TextArea ta;
	Button bt;
	ResultSet rs=null;

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Jdbc_TextField_TextArea  f=new Jdbc_TextField_TextArea();
		f.setSize(600,400);
		f.show();
	}
	
	public Jdbc_TextField_TextArea()
	{

		setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		la=new Label("Enter the query");
		add(la);
		
		tf=new TextField(20);
		add(tf);

		ta=new TextArea(" ", 25,40);
		add(ta);

		bt=new Button("DisplayData");
		add(bt);

		bt.addActionListener(this);
		
	}

    public void actionPerformed(ActionEvent ae)
    {
		if(ae.getSource()==bt)
    	 	{
    		 try
    		 {
    			 Class.forName("com.mysql.cj.jdbc.Driver");
    			 Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS","root","root123");
    			
    			 Statement stmt =cn.createStatement();
    			 String str = tf.getText();
    			 rs=stmt.executeQuery(str);
	
    			 ResultSetMetaData meta= rs.getMetaData();
    			 int columns= meta.getColumnCount();

    			 while(rs.next())
    			 {
    				 for(int j=1; j<=columns; j++)
    				 {
    					 String data = rs.getString(j);
    					 ta.append(data + "  ");
    				 }
    				 ta.append("\n");
    			 }
    		 }
    		 	catch(Exception e)
    		 	{
    		 		System.out.println(e);
    		 	}
    	 	}  	 
    	}
}