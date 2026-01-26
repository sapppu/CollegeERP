// Demon Stration on Switch Statement
import java.io.*;
class Tax
{
	public static void main (String args[])	 throws IOException
	{

		InputStreamReader reader = new InputStreamReader (System.in);
		BufferedReader in = new BufferedReader (reader);
		System.out.println("Enter the Income      : : ");
		String text1 = in.readLine();
		int income = Integer.parseInt(text1);
		System.out.println("Enter Employee category ::");
		String text2 = in.readLine();
		int emp_category = Integer.parseInt (text2);
		double allownce_rate = 0 ;
		switch (emp_category)
			{
				case 1 : allownce_rate = 0.05 ;
						 break;
				case 2 : allownce_rate = 0.07 ;
						 break;
				case 3 : allownce_rate = 0.10 ;
						 break;
		}

		double allowance = income * allownce_rate ;
		System.out.println("Allownce to be paid      :  " + allowance);

	}
}

/*  OUTPUT---

	Enter the Income : 10000
	Enter the Employee Category : 1
	Allowance to be paid : 500.0

*/