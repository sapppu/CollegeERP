/* try- catch ===
	== try blok allows you to fix the error.
	== Seconds its prevents the program autmatically terminating 
	== catch bolck enclose the exception that you wish to want catch.
*/
import java.io.*;
public class DivideByZero
{	
	public static void main (String args []) //throws ArithmeticException
	{
		int a = 10, b = 6, c = 5;
		int d=0;
		
		//System.out.println("a = " + a);
		//System.out.println("b = " + b);
		//System.out.println("c = " + c);
		
		//d = a/(b-c);			// Without Using try and  Catch
		//System.out.println("d = " + d);  // Without Using try and  Catch
		
		//int d1 = a/(b+c);			// Without Using try and  Catch
		//System.out.println("d = " + d1);  // Without Using try and  Catch
		
				
		try
		{
			d = a / (b - c);
		}
		catch (ArithmeticException e)
		{
			System.out.println("It leads to devision by zero error"+e);
			//b = 6;
			//d = a / (b - c); 	
		}
		/*System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
		System.out.println("d = " + d);*/
	}
}
	