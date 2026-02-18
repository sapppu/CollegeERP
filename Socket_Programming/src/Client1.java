import java.io.*;
import java.net.*;

public class Client1 {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("198.168.10.1",9090);
        InputStream is = s.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readUTF();
        System.out.println("Received String : "+str);
        dis.close();
        s.close();
    }
}
