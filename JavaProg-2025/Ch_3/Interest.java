
// proram to illustrate the structure of Nested IF Statement

import java.io.*;
class Interest
{
	public static void main (String args[]) throws IOException
	{
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader (reader);
		System.out.println("Enter the Deposit amount :: ");
		String text = in.readLine();
		int p = Integer.parseInt (text);
		System.out.println("Enter the period of deposit :: ");
		text = in.readLine();
		int n = Integer.parseInt(text);
		double rate ;
		if (p > 10000)
				rate = 0.11 ;
			else
				if (n > 2)
					rate = 0.10 ;
				else
					rate = 0.09 ;
		double interest = p * n * rate ;
		System.out.println("Interest to be paid = " + interest);

	}
}