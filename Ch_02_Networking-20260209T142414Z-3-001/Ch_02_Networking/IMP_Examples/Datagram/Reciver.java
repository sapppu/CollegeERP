/* receiver */

import java.net.*;
import java.io.*;

class Receiver{

public static void main(String args[])
{
  DatagramSocket s;
  DatagramPacket p;

s= new DatagramSocket(8000);
Byte [ ] b= new Byte[50];
p=new DatagramPacket(b,b.length);
s.receive(p);
String str=new String(b);
System.out.println(str);
}

}
