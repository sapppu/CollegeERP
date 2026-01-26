/* Implementation of try catch via call to methods */
class NestTryMethod
{
	static void nesttry(int a)
	{
		try{
				if (a==1)
					a=a/(a-a);
				if(a==2)
				{
					int arr[] = new int[10];
					arr[12]= 23;
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println(" Array is out of bounds = "+e);
			}
	}
	
	public static void main(String args[])
	{
		try{
			int a = args.length;
			int b = 42/a;
			System.out.println(" a = "+a);
			nesttry(a); // calling  method
			}
		catch(ArithmeticException e)
		{
			System.out.println(" Divide by zero :: "+e);
		}
			
}