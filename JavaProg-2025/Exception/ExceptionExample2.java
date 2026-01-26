
import java.io.*;
class ExceptionExample2
{
	public static void main(String args[])
	{
		int d,a;
		try{
			d=0;
			a=42/d ;
			System.out.println("This will be printed Y /N ");
			System.out.println("Hiiiiiiiiiiiiiiiii");
			}
		catch(ArithmeticException e)
			{
				System.out.println("Catched Error Division by Zero ::"+ e); // use of Exception object i.e 'e'
			}
		System.out.println("After catch this will be Executed Y/N::");
	}
}

/* OUT PUT ::
			F:\Exception>javac ExceptionExample2.java
			F:\Exception>java ExceptionExample2
				Catched Error Division by Zero ::
				After catch this will be Executed Y/N::

F:\Exception>

OUT PUT Using Exception Object :: That is  ' e ' in the above code

			F:\Exception>java ExceptionExample2
			Catched Error Division by Zero ::java.lang.ArithmeticException: / by zero
			After catch this will be Executed Y/N::

F:\Exception>
  */
  
 