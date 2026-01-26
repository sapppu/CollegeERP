import java.util.Scanner;
public class ArrayEleSwap {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int size = s.nextInt();
        int[] array = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = s.nextInt();
        }
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        if (newArray.length > 0) {
            int temp = newArray[0];
            newArray[0] = newArray[newArray.length - 1];
            newArray[newArray.length - 1] = temp;
        }
        System.out.println("New array after swapping first and last elements:");
        for (int num : newArray) {
            System.out.print(num + " ");
        }
    }
}
