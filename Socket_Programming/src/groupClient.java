import java.net.*;
import java.io.*;
import java.util.Scanner;

public class groupClient {

    public static void main(String[] args) {
        try {
            Socket s = new Socket(InetAddress.getByName("localhost"), 9090);
            System.out.println("Connected to chat server");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);


            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected from server");
                }
            });

            Thread sendThread = new Thread(() -> {
                try {
                    while (true) {
                        String msg = sc.nextLine();
                        String send = "Sappu: "+msg;
                        dos.writeUTF(send);
                        dos.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            receiveThread.start();
            sendThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
