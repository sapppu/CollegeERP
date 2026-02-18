
import java.net.*;

public class ServerVowelCount {
    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[1024];

        System.out.println("Server started...");

        while (true) {

            DatagramPacket dp = new DatagramPacket(receive, receive.length);
            ds.receive(dp);

            String str = new String(dp.getData(), 0, dp.getLength()).toLowerCase();

            int a = 0, e = 0, i = 0, o = 0, u = 0;

            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);

                if (ch == 'a') a++;
                else if (ch == 'e') e++;
                else if (ch == 'i') i++;
                else if (ch == 'o') o++;
                else if (ch == 'u') u++;
            }

            int total = a + e + i + o + u;

            String result = "Total Vowels: " + total +
                    "\na:" + a +
                    "\ne:" + e +
                    "\ni:" + i +
                    "\no:" + o +
                    "\nu:" + u;

            byte[] send = result.getBytes();

            InetAddress ip = dp.getAddress();
            int port = dp.getPort();

            DatagramPacket dpSend =
                    new DatagramPacket(send, send.length, ip, port);

            ds.send(dpSend);
        }
    }
}
