import java.io.*;
import java.net.*;


class Sender{
 DatagramSocket ns;
 DatagramPacket p;
public static void main(String[ ] args)
{
 Byte [ ] b;
 String str=args[0];
 s=new DatagramSocket();
 b=new Byte(str.length());
str.getBytes(0,str.length(),b,0);
p= new DatagramPacket(b,b.length,InetAddress.getLocalHost(),4000);
s.send(p);
}
}


