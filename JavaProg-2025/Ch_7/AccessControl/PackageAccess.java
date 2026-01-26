package s;
class Q1	// Q1 belong to same package i.e s
{
	void show1()    // default package access control
	{
		System.out.println("Show1 method is invoked");
	}
	
	void show2()   // default package access control
	{
		System.out.println("Show1 method is invoked");
	}

}
class Q2		// Q2 belongs to same package i.e s
{
	void display1()
	{
		Q1 obj =new Q1();
		obj.show1();
	}
}
class Q3	// Q3 belongs to same package i.e s
{
	void display2()
	{
		Q1 obj =new Q1();
		obj.show2();
	}
}
public class PackageAccess	// class PackageAccess  belongs to same package i.e s
{
	public static void main(String args[])
	{
		Q2 q2 =new Q2();
		q2.display1();
		Q3 q3 =new Q3();
		q3.display2();
	}
}