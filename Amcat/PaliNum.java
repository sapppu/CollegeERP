import java.util.*;
public class PaliNum{
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
System.out.println("Enter number to check palindrome or not");
int num=sc.nextInt();
int t=num;
int rev=0;
while(t>0){
rev=rev*10+t%10;
t=t/10;
}
if(num==rev){
System.out.println("Number is Palindrome");
}else{System.out.println("Not palindrome");}
}
}
