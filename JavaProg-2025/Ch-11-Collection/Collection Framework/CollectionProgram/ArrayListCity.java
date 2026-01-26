import java.util.ArrayList;
import java.util.Scanner;
public class ArrayListCity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("\n Total Number of city:");
        int n = sc.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("\n Enter city name:");
            String city = sc.next();
            list.add(city);
        }
        System.out.println("\n Array List contains:"+ list);
        list.clear();
        System.out.println("\n After using clear method:");
        System.out.println("\n Array List contains:"+ list);

    }
}