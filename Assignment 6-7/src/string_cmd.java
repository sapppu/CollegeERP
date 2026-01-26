import java.util.Arrays;

public class string_cmd {
    public static void main(String[] args) {
        if(args.length < 5){
            System.out.println("Enter 5 nos exactly");
        }
        int n[] = new int[5];
        for(int i = 0; i < args.length; i++){
            n[i] = Integer.parseInt(args[i]);
        }

        Arrays.sort(n);
        for(int i = 0; i < args.length; i++){
            System.out.println(n[i]);
        }
    }
}
