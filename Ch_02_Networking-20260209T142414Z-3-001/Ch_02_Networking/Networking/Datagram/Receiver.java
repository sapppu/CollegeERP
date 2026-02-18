import java.net.*;
import java.io.*;

class Receiver{

public static void main(String args[])
{

 DatagramSocket  s= new DatagramSocket(8000);
Byte b[ ]= new Byte[50];
DatagramPacket  p=new DatagramPacket(b,b.length);
s.receiver(p);
String str=new String(b);
System.out.println(str);
}

}