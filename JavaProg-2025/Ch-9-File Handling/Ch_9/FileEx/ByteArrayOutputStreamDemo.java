


// Ex 1 :  ByteArrayOutputStream.Also use of reset() and writeTo() method.


import  java.io.*;
 class ByteArrayOutputStreamDemo
 {
 	public static void main(String args[]) throws IOException
 		{
			String str="Java is created by sun microsystem";
			char X;
			ByteArrayOutputStream bout=new ByteArrayOutputStream();
			byte b[]=str.getBytes();
			bout.write(b);
			System.out.println("Buffer as a string");
			System.out.println(bout.toString()); // String contains writting on Console
			System.out.println("To an OutputStream ");
			FileOutputStream fos=new FileOutputStream("D:/JavaProg-2025/Ch_9/FileEx/abc.txt");
			bout.writeTo(fos);	// String Contains Writing in to the specified file using writeTo() Method
			System.out.println("Doing a reset");
			bout.reset();
			for(int i=0;i<3;i++)
		               	bout.write('X');
			System.out.println(bout.toString());
			bout.writeTo(fos);
			fos.close();
			bout.close();
		}
	}
