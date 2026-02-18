import java.io.*;
import java.net.*;

class SqlRetrieverClient {

	static Socket s;
	static BufferedReader keyboard;
	static DataInputStream dis;
	static DataOutputStream dos;

	public static void main(String args[]) {
		try {
			s = new Socket("localhost", 9090);

			keyboard = new BufferedReader(new InputStreamReader(System.in));

			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());

			System.out.println("Enter SQL Query (Example: SELECT * FROM student):");
			String query = keyboard.readLine();

			dos.writeUTF(query);

			String result = dis.readUTF();
			System.out.println("\nResult from Server:");
			System.out.println(result);

			dos.close();
			dis.close();
			s.close();

		} catch (Exception e) {
			System.out.println("Exception on Client Side : " + e);
		}
	}
}
