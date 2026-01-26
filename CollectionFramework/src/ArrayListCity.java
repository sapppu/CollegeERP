import java.util.ArrayList;
import java.util.Scanner;


public class ArrayListCity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list=new ArrayList<>();
        System.out.println("Total cities :");
        int n = sc.nextInt();
        for(int i=0;i<n;i++){
            System.out.println("Enter city name:");
            String city=sc.next();
            list.add(city);
        }

        System.out.println("Array List contains"+list);
        if(list.contains("Solapur")){
            System.out.println("HEHEHEHEH");
        }
        System.out.println(list.contains("Solapur"));
        list.add(5,"Mumbai");
        list.remove("Akkalkot");
        System.out.println("Array List contains"+list);
        list.clear();
        System.out.println("After clearing");
        System.out.println("Array List contains"+list);
    }
}
