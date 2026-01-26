class divideException extends Exception
{
    divideException(String s)
    {
        super(s);
    }
}
public class CommandLineException {
    public static void main(String[] args) throws divideException{
        if(args.length == 0)
        {
            System.out.printf("\n Please pass the arguments!");
        }
        else {
            int a = Integer.parseInt(args[0]);

            if(a % 7 != 0)
            {
                throw new divideException("\n Not divisible by 7");
            }
            else
            {
                System.out.printf("\n Number is divisible by 7");
            }
        }
    }
}
