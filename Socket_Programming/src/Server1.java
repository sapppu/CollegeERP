import java.io.*;
import java.net.*;
public class Server1 {
    public static void main(String[] args) throws UnknownHostException, IOException {
        ServerSocket ss = new ServerSocket(9090);
        System.out.println("Server Started");
        Socket s = ss.accept();
        System.out.println("Client Connected");

        DataInputStream dis = new DataInputStream(System.in);
        System.out.println("Enter the message to send from client");
        String msg = dis.readLine();

        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF(msg);

        System.out.println("Message Sent to Client");
        dis.close();
        s.close();
    }
}
