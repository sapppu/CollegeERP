import java.util.ArrayList;
import java.util.Iterator;

public class ArrayList1 {
    public static void main(String[] args) {
        ArrayList<String> l1= new ArrayList<>();
        l1.add("Swapnaj");
        System.out.println(l1);
        Iterator<String> it = l1.iterator();
        System.out.println(it.hasNext());
        System.out.println(it.next());
    }
}