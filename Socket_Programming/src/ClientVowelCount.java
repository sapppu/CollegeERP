import java.net.*;
import java.util.Scanner;

public class ClientVowelCount {
    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string:");
        String str = sc.nextLine();

        byte[] send = str.getBytes();

        InetAddress ip = InetAddress.getByName("localhost");

        DatagramPacket dp = new DatagramPacket(send, send.length, ip, 1234);

        ds.send(dp);

        byte[] receive = new byte[1024];
        DatagramPacket dpReceive = new DatagramPacket(receive, receive.length);

        ds.receive(dpReceive);

        String result = new String(dpReceive.getData(), 0, dpReceive.getLength());

        System.out.println("Server Response:");
        System.out.println(result);

        ds.close();
    }
}
