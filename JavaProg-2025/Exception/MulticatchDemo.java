class MulticatchDemo
{
	public static void main(String args[])
	{
		try
		{
			int a = args.length ;
			System.out.println("No of Command line argument are i. e a = :" + a);
			int b= 40/a;
			int arr[] = new int[10];
			arr[12]=30;
		}
		catch(ArithmeticException e)
			{
				System.out.println("Catched Error Divide by Zero ::"+ e); 
				// use of Exception object i.e 'e'
				
			}
		catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Array Index Out Of Bounds ::"+ e);
			}
		System.out.println("After Finishing Multiple catch clause ::");
	}
}

/* Out Put  
		0ut put 1:: Without passing no of command line parametor
			
		F:\Exception>java MulticatchDemo
		No of Command line argument are :0
		Catched Error Divide by Zero ::java.lang.ArithmeticException: / by zero
		After Finishing Multiple catch clause ::
		
		Out put2:: With passing no of command line parametor
		
		F:\Exception>java MulticatchDemo ABHIJIT
		No of Command line argument are i. e a = :1
		Array Index Out Of Bounds ::java.lang.ArrayIndexOutOfBoundsException: 12
		After Finishing Multiple catch clause ::

		F:\Exception>

*/