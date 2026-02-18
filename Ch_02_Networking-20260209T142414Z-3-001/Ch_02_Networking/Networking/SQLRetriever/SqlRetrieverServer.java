import java.net.*;
import java.io.*;
import java.sql.*;

class SqlRetrieverServer {
	public static void main(String args[]) {
		ServerSocket ss;
		Socket s;

		try {
			ss = new ServerSocket(9090);
			System.out.println("Server Started... Waiting for clients");

			while (true) {
				s = ss.accept();
				System.out.println("Client Connected");
				new MyThread(s).start();
			}
		} catch (Exception e) {
			System.out.println("EXCEPTION ON SERVER SIDE : " + e);
		}
	}
}

class MyThread extends Thread {

	Connection con;
	Statement st;
	ResultSet rs;

	DataInputStream dis;
	DataOutputStream dos;
	Socket socket;

	MyThread(Socket s) {
		try {
			socket = s;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/studentdb",
					"root",
					"sappu0"
			);

			st = con.createStatement();

		} catch (Exception e) {
			System.out.println("EXCEPTION IN THREAD CONSTRUCTOR : " + e);
		}
	}

	public void run() {
		try {
			String query = dis.readUTF();
			System.out.println("Query Received: " + query);

			rs = st.executeQuery(query);

			String result = "";

			while (rs.next()) {
				result = result +
						rs.getInt(1) + "\t" +
						rs.getString(2) + "\t" +
						rs.getInt(3) + "\n";
			}

			dos.writeUTF(result);

			rs.close();
			st.close();
			con.close();
			socket.close();

		} catch (Exception e) {
			System.out.println("EXCEPTION IN THREAD RUN : " + e);
		}
	}
}
