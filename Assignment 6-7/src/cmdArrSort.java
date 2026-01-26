import java.util.Arrays;
public class cmdArrSort {
    public static void main(String[] args) {
        if(args.length>5){
            System.out.println("Enter exact 5 numbers");
        }
        int num[] = new int[5];
        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.parseInt(args[i]);
        }
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }
    }
}
