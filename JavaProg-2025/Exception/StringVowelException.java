import java.util.Scanner;

class StringException extends Exception
{
    StringException(String s)
    {
        super(s);
    }
}

public class StringVowelException {
    static void vowelException(String str) throws StringException
    {
        if(str.contains("A") || str.contains("E") || str.contains("I") || str.contains("O") || str.contains("U"))
        {
            throw new StringException("String contains vowel character");
        }
        else
        {
            int len = str.length();
            for(int i=len-1;i>=0;i--)
            {
                System.out.printf("\n String is: %s",str.charAt(i));
            }

        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("\n Enter String:");
        String str = sc.next();
        try
        {
            vowelException(str);
        }catch (StringException s)
        {
            System.out.printf("\n We failed to print. Reason: ");
            System.out.println(s);
        }
    }
}
