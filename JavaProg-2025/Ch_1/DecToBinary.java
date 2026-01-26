import java.util.*;
class Q3{

public void DecToBinary(int num){

	int[] arr = new int[10];
	int cnt = 0;
	while(num > 0){
		int rem = num % 2;
		arr[cnt] = rem;
		num /= 2;
		cnt++;
	}
	
	for(int i = cnt-1; i >=0; i--){
		System.out.print(arr[i]);
	}
	
}


public static void main(String[] args){
	Scanner sc = new Scanner(System.in); 

	Q3 obj = new Q3();
	System.out.println("Enter Number : ");
	int num = sc.nextInt();
	obj.decToBinary(num);
	
	
}
}
