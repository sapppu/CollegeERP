import java.util.*;
public class assignment1 {
    public static double VolumeOfCylinder(float r,float h){
        return (3.14)*(r*r*h);
    }
    public static int facto(int n){
        if(n==0)
            return 1;
        else
            return n*facto(n-1);
    }
    public static boolean Armstrong(int n){
        int original=n,res=0;
        int totaldigits=String.valueOf(n).length();
        while (n!=0){
            int digit=n%10;
            res+=Math.pow(digit,totaldigits);
            n/=10;
        }
        return res==original;
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int choice;
        do{
            System.out.println("1.Volume of cylinder \n2.Factorial \n3.Armstrong \n\n Enter choice : ");
            choice=sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("Enter radius : ");
                    float r = sc.nextFloat();
                    System.out.println("Enter height : ");
                    float h = sc.nextFloat();
                    double vl = VolumeOfCylinder(r,h);
                    System.out.println("Volume : "+vl);
                    break;
                case 2:
                    System.out.println("Enter no for factorial");
                    int n = sc.nextInt();
                    System.out.println("Factorial : "+facto(n));
                    break;
                case 3:
                    System.out.println("Enter to know a number is factorila or not : ");
                    int m = sc.nextInt();
                    System.out.println(Armstrong(m));
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice : ");
            }
        }while(choice!=3);
    }

}