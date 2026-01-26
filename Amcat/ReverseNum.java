import java.util.*;
public class ReverseNum{
public static void main(String[] args){
Scanner sc = new Scanner(System.in);
System.out.println("Enter number to reverse");
int num=sc.nextInt();
System.out.println("Number = "+num);

int rev=0;
while(num>0){
rev=rev*10+num%10;
num=num/10;
}
System.out.println("\nReverse="+rev);
}
}
