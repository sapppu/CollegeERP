import java.util.Scanner;
class NegativeException extends Exception
{
    NegativeException(String s)
    {
        super(s);
    }
}
public class NegativeNumberEXception {
    public static void main(String[] args) throws NegativeException{
        Scanner sc = new Scanner(System.in);
        try{
            System.out.printf("\n Enter Number:");
            int n = sc.nextInt();
            if( n < 0)
            {
                throw new NegativeException("\n Number is Negative");
            }
            else{
                System.out.printf("\n Number is: %d",n);
            }
        }catch (NegativeException e)
        {
            e.printStackTrace();
        }
    }
}
