import java.util.Scanner;
public class DiagSumMtrx {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the size of the square matrix:");
        int size = s.nextInt();
        int[][] matrix = new int[size][size];
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = s.nextInt();
            }
        }
        int pds = 0;
        int sds = 0;
        for (int i = 0; i < matrix.length; i++) {
            pds += matrix[i][i];
            sds += matrix[i][matrix.length - 1 - i];
        }
        System.out.println("Sum of principal diagonal: " + pds);
        System.out.println("Sum of secondary diagonal: " + sds);
    }
}
