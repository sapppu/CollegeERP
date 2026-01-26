import  java.io.*;
class ByteArrayInputStreamResetDemo
 {
 	public static void main(String args[]) throws IOException
 		{
			String str="siom";
			byte b[]=str.getBytes();
			ByteArrayInputStream bin=new ByteArrayInputStream(b);
			for(int i=0;i<2;i++)
			{
				int c;
				while((c=bin.read())!= -1)
				{
					if(i==0)
					{
					   System.out.print((char)c);
					}else
					{
					System.out.print(Character.toUpperCase((char)c));
					}
				}
				System.out.println();   // Next Line
				bin.reset();
			}
		}
	}
