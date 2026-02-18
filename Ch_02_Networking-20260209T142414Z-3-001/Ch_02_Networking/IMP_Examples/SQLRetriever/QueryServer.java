//package Socket;

import java.io.*;
import java.net.*;
import java.sql.*;

class QueryServer {
    public static void main(String args[]) {
        try {
            System.out.println("Server connected");

            ServerSocket ss = new ServerSocket(8000);
            Socket s = ss.accept();
            System.out.println("Client connected");

            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            String query = dis.readUTF();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get ResultSet metadata to dynamically fetch column names and count
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Storing the result in a StringBuilder
            StringBuilder result = new StringBuilder();

            // Append column names
            for (int i = 1; i <= columnCount; i++) {
                result.append(metaData.getColumnName(i)).append("\t");
            }
            result.append("\n");

            // Append data
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    result.append(rs.getString(i)).append("\t");
                }
                result.append("\n");
            }

            // Sending the result back to the client
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(result.toString());

            // Closing resources
            rs.close();
            stmt.close();
            con.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}