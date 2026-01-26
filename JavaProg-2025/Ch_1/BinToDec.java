import java.util.*;
class Q4{

public int BinToDec(String s){
	int num = 0;
	int Base = 1;
	int i = s.length()-1;
	while(i >= 0){
		if(s.charAt(i) == '1'){
			num += Base;
		}
		Base *= 2;
		i--;
	}
	
	return num;
	
}


public static void main(String[] args){
	Scanner sc = new Scanner(System.in); 

	Q4 obj = new Q4();
	System.out.println("Enter Binary Number : ");
	String num = sc.nextLine();
	System.out.println("Binary to Decimal : " + obj.BinToDec(num));
	
	
}
}
