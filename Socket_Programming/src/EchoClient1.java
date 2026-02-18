import java.io.*;
import java.net.*;

public class EchoClient1 {
    public static void main(String[] args) {
        //String hostName = "127.0.0.1";
        int portNumber = 9090;

        try (
                Socket echoSocket = new Socket(InetAddress.getLocalHost(), portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);

                if ("quit".equalsIgnoreCase(userInput)) {
                    System.out.println("Server: " + in.readLine());
                    break;
                }

                System.out.println("echo: " + in.readLine());
            }
        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
            System.exit(1);
        }
    }
}