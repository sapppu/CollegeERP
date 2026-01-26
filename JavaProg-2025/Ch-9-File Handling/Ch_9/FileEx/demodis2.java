
//Ex 2 To Accept character till user enters �q� and store them infile and display the file content
import java.io.*;

public class demodis2 {
	public static void main(String args[]) {
		try {
			int n;
			FileOutputStream fos = new FileOutputStream(
					"/home/sappu/IdeaProjects/JavaProg-2025/Ch-9-File Handling/Ch_9/FileEx/abc.txt");
			DataInputStream dis = new DataInputStream(System.in);
			System.out.println("Enter Characters,To end enter 'q' ");
			while ((char) (n = dis.read()) != 'q') {
				fos.write(n);
			}
			fos.close();
			dis.close();

			System.out.println("File Contains Following Data");
			FileInputStream fis = new FileInputStream("D:/JavaProg-2025/Ch_9/FileEx/abc.txt");
			while ((n = fis.read()) != -1) {
				System.out.print((char) n);
			}
			fis.close();
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
}
