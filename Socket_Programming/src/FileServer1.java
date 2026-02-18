import java.net.*;
import java.io.*;

public class FileServer1 {
    public static void main(String[] args) {
        int portNumber = 9090;
        try (ServerSocket ss = new ServerSocket(portNumber)) {
            System.out.println("Server started on port " + portNumber);
            Socket s = ss.accept();
        }
        catch (IOException e) {

        }
    }
}
