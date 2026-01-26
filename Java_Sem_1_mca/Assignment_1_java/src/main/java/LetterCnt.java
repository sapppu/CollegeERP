import java.util.Scanner;
public class LetterCnt {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = s.nextLine();
        int l = 0, sp = 0, n = 0, o = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                l++;
            } else if (Character.isDigit(c)) {
                n++;
            } else if (Character.isSpaceChar(c)) {
                sp++;
            } else {
                o++;
            }
        }
        System.out.println("Letters: " + l);
        System.out.println("Spaces: " + sp);
        System.out.println("Numbers: " + n);
        System.out.println("Other characters: " + o);
    }
}
