	import java.util.Scanner;

	class emailexc extends Exception
	{
   		 emailexc(String s)
   	 	{
        			super(s);
   	 	}
	}

	public class EmailException 
	{
    		static void acceptemail() throws emailexc
    		{
       			String email;
        			Scanner sc = new Scanner(System.in);
        			System.out.printf("\n Enter Email:");
        			email = sc.next();
        			if(Character.isDigit(email.charAt(0)))
        			{
            				throw new emailexc("Email cannot starts with digit");
        			}
        			else
        			{
            				if(email.contains("@"))
           				 {
                					System.out.printf(email);
            				}
           				else 
				{
                					throw new emailexc("Email should contain @ symbol");
           				 }
       			 }
    	}
    	public static void main(String[] args) 
	{
        		try
        		{
            			acceptemail();
       		 } 
		catch (emailexc e1) 
		{
            			e1.printStackTrace();
        		}
   	 }
}
