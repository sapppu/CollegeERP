package jdbc_supplier_project;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class jdbcsup 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		frmrecord f = new frmrecord();
		f.setSize(500,500);
		f.setVisible(true);
	}

}

class frmrecord extends Frame implements ActionListener
{

	 private static final long serialVersionUID = 1L;
	 Label l1,l2,l3,l4,l5;
	 TextField tf1,tf2,tf3,tf4,tf5;
	 CheckboxGroup chb;
	 Checkbox ch1,ch2;
	 TextArea ta;
	 Button b1,b2,b3,b4,b5;
	 Connection cn;
	 PreparedStatement ps;
     Statement st;
	 ResultSet rs;
	
	 String s="";
	 String str="";

	public frmrecord()
	{
	
		setLayout(null);
		l1=new Label("Sup id");
		l1.setBounds(50,50,100,50);

		l2=new Label("Sup Name");
		l2.setBounds(50,100,100,50);

		l3=new Label("Sup Add");
		l3.setBounds(50,150,100,50);

		l4=new Label("Email");
		l4.setBounds(50,200,100,50);

		l5=new Label("No of Records");
		l5.setBounds(50,300,100,50);
 		
 		
		chb=new CheckboxGroup();
		ch1=new Checkbox("Male",chb,false);
		ch2=new Checkbox("Female",chb,false);
 		
		ch1.setBounds(50,250,75,50);
		ch2.setBounds(150,250,75,50);
		tf1=new TextField(10);
		tf1.setBounds(150,50,150,40);

		
		tf2=new TextField(10);
		tf2.setBounds(150,100,150,40);

		tf3=new TextField(10);
		tf3.setBounds(150,150,150,40);
		
		tf4=new TextField(10);
		tf4.setBounds(150,200,150,40);
		
		tf5=new TextField(10);
		tf5.setBounds(150,300,150,40);

		ta=new TextArea(10,10);
		ta.setBounds(330,240,200,150);

		b1=new Button("Show");
		b1.setBounds(30,350,70,50);
		
	    b2=new Button("PREVIOUS");
		b2.setBounds(100,350,70,50);

		b3=new Button("Submit");
		b3.setBounds(170,350,70,50);
		
		b4=new Button("Exit");
		b4.setBounds(250,350,70,50);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(tf1);
		add(tf2);
		add(tf3);
		add(tf4);
		add(ch1);
		add(ch2);
		add(tf5);
		add(ta);

		add(b1);
		add(b2);
		add(b3);
		add(b4);
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");		
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS","root","root123");
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
	 if(ae.getSource()==b1)
	   {
		int cnt=0;
		int i=0;
		cnt=Integer.parseInt(tf5.getText());
		try
		{
			st=cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//TYPE_FORWORD_ONLY
			rs=st.executeQuery("select * from sup");
				
			while(rs.next() &&i<cnt)
			{
			str=str+(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\n");
			ta.setText(str);
			i++;
			}
			
		}
		catch(Exception e)
		{
		}
	   }	
	
       if(ae.getSource()==b2)
		{
    	   try
			{		
			st=cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rs=st.executeQuery("Select * from sup ");
			rs.afterLast();
			while(rs.previous())
			{
			str=str+(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\n");
			ta.setText(str);
			}
			}	
    	   catch(Exception e)
    	   	{
    		   System.out.println(e.getMessage());
    	   	}
	   }
 
	 if(ae.getSource()==b3)
		
	   {

		
		try
		{
			ps=cn.prepareStatement("Insert into sup values(?,?,?,?,?)");
			ps.setString(1,tf1.getText());
			ps.setString(2,tf2.getText());
			ps.setString(3,tf3.getText());
			ps.setString(4,tf4.getText());
			if(ch1.getState()==true)
			{
			ps.setString(5,ch1.getLabel());
			}
			else
			{
			ps.setString(5,ch2.getLabel());	
			}
			
			ps.executeUpdate();
			System.out.println("Inserted..");
			
		}

		catch(Exception e)
		{
		System.out.println("Error"+e);
		}
		
		finally
		{
			
			try
			{

			System.out.println("In finally");
			ps=cn.prepareStatement("Update sup set sname=?,sadd=?,email=?,gender=? where sid=?");
			ps.setString(1,tf2.getText());
			ps.setString(2,tf3.getText());
			ps.setString(3,tf4.getText());

			if(ch1.getState()==true)
			{
			ps.setString(4,ch1.getLabel());
			}
			else
			{
			ps.setString(4,ch2.getLabel());	
			}
			ps.setString(5,tf1.getText());
			ps.executeUpdate();
			System.out.println("Updated..");
			}
		catch(Exception e1)
		{
		}			
		}
	  }	

		if(ae.getSource()==b4)
		
	   {
		System.exit(1);

		}	
}
}

