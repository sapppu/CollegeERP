import java.util.Scanner;
public class string3vowelPrint {
    public static void main(String[] args) {
        try{
            vowels_demo v1 = new vowels_demo(str1);
            vowels_demo v2 = new vowels_demo(str2);
            vowels_demo v3 = new vowels_demo(str3);
            v1.join();
            v2.join();
            v3.join();

        }
        catch (){}
    }
}

class vowels_demo implements Runnable {
    String str1;
    Thread t;
    vowels_demo(String str1){
        str1=this.str1;
        t=new Thread(this);
        t.start();
    }
    public void run() {
        if(str1.contains("a"||"e"||"i"||"o"||"u"||"A"||"E"||"I"||"O"||"U")){

        }
    }
}
