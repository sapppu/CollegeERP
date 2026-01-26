class NestTry11
{
	public static void main(String args[])
	{
		try{
			int a = args.length;
			int b = 42/a;
			System.out.println(" a = "+a);
			try{
				if (a==1)
					a=a/(a-a);
				if(a==2)
				{
					int arr[] = new int[10];
					arr[8]= 23;
				}
			}
			catch(ArrayIndexOutOfBoundsException e2)
			{
				System.out.println(" Array is out of bounds = "+e2);
			}
			catch(ArithmeticException e3)
			{
				System.out.println(" Divide by zero :: "+e3);
			}
		}
		catch(Exception e1)
			{
				System.out.println(" Error :: "+e1);
			}
		
	}
}