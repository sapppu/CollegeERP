import java.util.*;
public class Armstrong{
public static void main(String[] args){
Scanner sc = new Scanner(System.in);
System.out.println("Enter number");
int n=sc.nextInt();
int t=n;
int s=0;

while(t>0){
int d=t%10;
s=s+d*d*d;
t/=10;
}
if(n==s){
System.out.println("Armstrong");
}
}
}
