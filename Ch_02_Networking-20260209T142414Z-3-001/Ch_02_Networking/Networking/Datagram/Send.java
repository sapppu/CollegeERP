/* Send data */

import java.io.*;
import java.net.*;
import java.net.DatagramPacket;

class Send{
 static DatagramSocket s;
// static DatagramPacket p;
public static void main(String[ ] args) throws Exception 
{
  byte [ ] b;
  String str=args[0];
  s=new DatagramSocket();
  b=new byte[str.length()];
  b=str.getBytes();
  InetAddress iAd=InetAddress.getLocalHost();
//java.net.DatagramPacket p = new java.net.DatagramPacket(b,0,15,iAd,4000);
DatagramPacket p = new DatagramPacket(b,b.length,iAd,4000);
s.send(p);
}
}


