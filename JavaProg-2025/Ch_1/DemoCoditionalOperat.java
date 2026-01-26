// Demo on Coditional operator-- >       ? :
import java.io.*;
class DemoCoditionalOperat
{
	public static void main (String args[]) throws IOException
	{
		String snum;
		int a,b,c,max1,max;
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the First No.      : : ");
		a = in.nextInt();
		System.out.println("Enter the Second No.      : : ");
		b = in.nextInt();
		System.out.println("Enter the Third No.      : : ");
		c = in.nextInt();
		
		max1=a>b?a:b;
		max=max1>c?max1:c;
		
		System.out.println("Number one is       : : ");
		System.out.println("Number Second is       : : ");
		System.out.println("Number Third is       : : ");

		System.out.println("Maximum Number is       : : "+max);	
	}
}