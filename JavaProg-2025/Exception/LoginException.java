import java.util.Scanner;

class UsernamePasswordException extends Exception
{
    UsernamePasswordException(String s)
    {
        super(s);
    }
}
class Login{
    String username, password;
    Scanner sc = new Scanner(System.in);
    void check(String username, String password) throws UsernamePasswordException
    {
        if(password.equals(username))
        {
            System.out.printf("\n Successfully Logged In!");
        }
        else {
            throw new UsernamePasswordException("\n Invalid Password");
        }
    }
    Login(){
        System.out.printf("\n Enter Username:");
        username = sc.next();
        System.out.printf("\n Enter Password:");
        password= sc.next();
        try {
            check(username,password);
        } catch (UsernamePasswordException e) {
            e.printStackTrace();
        }
    }
}
public class LoginException {
    public static void main(String[] args) {
        Login l = new Login();
    }
}
