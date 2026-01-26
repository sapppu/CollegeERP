

/* This program creats a custom or user defined exception type.

IMPORTENT = throws keyword syntax 
 
	type methodname (parametor_list) throws exception_list 
	{
		Statements--- ;
	}
	
	Note :  exception_list is a comma-separated list of 
		exception that a method can throw.
*/


class MyException extends Exception
{
	private int detail;
	MyException (int a)
	{
		detail = a;
	}

	/* String toString() ==  Returns String object containing brief 
				 description of the exception of throwable object.
				 This method is defined by Throwable class 
	*/

	public String toString() //Overloaded toString method that display value of exception
	{
		return "MyException [" + detail + "]"; /* This value(String) returned
						         for your User defined Exception */
	}
}
class ExceptionDemo 
{
	static void compute (int a) throws MyException
        {

	  /* 1 = Use throw to create instance of type Throwable class or subclass of 
		 Throwable class or  Exception  class 

	     2 = Flow of execution stops immediately after throw statement  
	         i.e any subsequent statements are not executed 	
			  
	     Syntax =   throw new < Exception object >
          */
		
	        System.out.println("Called compute (" + a + ")");
		
		if (a >10)
			throw new MyException (a);
		
		System.out.println("Normal Exit");

	}
	
	public static void main (String args[])
	{
		try
		{
			compute (10);
			compute (15); // Exception throws
		}
		catch (MyException e)
		{
			System.out.println("You caught a user defined exception i.e. " + e);

			/*'e' object contains value (String ) accepted or returned
			 from your user defined exception  */
		}
	}
}	

/* OUT PUT  ===

F:\Amol\Amol\Exception>java ExceptionDemo
Called compute (10)
Normal Exit
Called compute (15)
You caught a user defined exception i.e. MyException [15]

F:\Amol\Amol\Exception>
 
 */