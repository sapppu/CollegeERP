import java.io.*;
class RandomAccessDemo1

{
	public static void main(String args[])
	{
		//RandomAccessFile rf = null;
		try{
			RandomAccessFile raf = new RandomAccessFile("D:/JavaProg-2025/Ch_9/FileEx/PQR.txt","r");

			long count1 = Long.parseLong(args[0]); // Starting position
			long count2 = Long.parseLong(args[1]); // Total Number of bytes to be needed
			
			// file pointer should be of type long because files are usally very large

			if (count1 + count2 > raf.length())
				count2 = raf.length()- count1;

			raf.seek(count1); // seek() method used to move a file pointer to a desired position 

				for (int i=0 ;i<count2;i++)
				{
						try
						{
							byte b = raf.readByte();
							System.out.print((char)b);
						}
						catch(EOFException e1)
						{
							break;
						}
				}
			}
			catch(Exception e){ }
	}

}