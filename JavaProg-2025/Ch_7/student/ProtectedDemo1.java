import java.io.*;
class ProtectedDemo1
{
	public static void main(String args[])
	{
		StudentDetails1 std1 = new StudentDetails1();
		std1.StudentDetails1();
		StudentDetails2 std2 = new StudentDetails2();
		std1.StudentDetails2();
		ProtectedDemo std3 = new ProtectedDemo();
		std3.ProtectedDemo();
	}
}