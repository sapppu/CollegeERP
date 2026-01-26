import java.io.*;
class OvelException extends Exception
{
	OvelException(String msg)
	{
		super(msg);
	}

}

class OvelExc
{
	public static void main(String[] args)throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter any string : ");

		try
		{
			String str=br.readLine();

			int len=str.length();

			for(int i=0;i<len;i++)
			{
				char ch=str.charAt(i);
					if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
					    throw new OvelException("String contain Oval,Dont enter ovel");
			}

			System.out.println();
			System.out.println("Given string in reverse order : ");
			for(int j=len-1;j>=0;j--)
					System.out.print(str.charAt(j));

			System.out.println();

		}
		catch(OvelException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
