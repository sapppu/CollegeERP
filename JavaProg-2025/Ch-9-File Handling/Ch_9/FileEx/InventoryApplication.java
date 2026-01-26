import java.io.*;
public class InventoryApplication
{
	public static void main(String args[]) throws IOException
	{
		try{
			File Master = new File("D:/JaliJavaProg/FileEx/Master.txt");
			FileOutputStream fos1 = new FileOutputStream(Master);
			DataOutputStream dos1 = new DataOutputStream(fos1);
			System.out.println("Eneter the Item Code and Quantity :");
			for(int i=0;i<3;i++)
			{
				int itemcode = valueReader2();
				int quantity = valueReader2();
				dos1.writeInt(itemcode);
				dos1.writeInt(quantity);
			}
			dos1.close();
			fos1.close();
		    }
		catch(Exception e)
		    {
			System.out.println(e);
		    }
		try
		{
			File Transaction = new File("D:/JaliJavaProg/FileEx/Transaction.txt");
			FileOutputStream fos2 = new FileOutputStream(Transaction);
			DataOutputStream dos2 = new DataOutputStream(fos2);
			System.out.println("Eneter the Item Code,Tran code and Tran Quantity :");
			for(int i=0;i<3;i++)
			{
				int itemcode = valueReader2();
				int trancode = valueReader2();
				int tranQuantity = ValueReader2();
				dos2.writeInt(itemcode);
				dos2.writeInt(trancode);
				dos2.writeInt(tranQuantity);
			}
			fos2.close();
			dos2.close();
		}
		catch(Exception e)
		    {
			System.out.println(e);
		    }
		try
		{
			FileInputStream fis1= new FileInputStream("D:/JaliJavaProg/FileEx/Master.txt");
			DataInputStream dis1= new DataInputStream(fis1) ;

			FileInputStream fis2= new FileInputStream("D:/JaliJavaProg/FileEx/Transaction.txt");
			DataInputStream dis2= new DataInputStream(fis2) ;
			
			File NewMaster =new File("D:/JaliJavaProg/FileEx/NewMaster.txt");
			FileOutputStream fos= new FileOutputStream(NewMaster);
			DataOutputStream dos= new DataOutputStream(fos) ;

			int itemcode;
			int updatedqty;
			for(int i=0;i<=3;i++)
			{
				int ic1 = dis1.readInt();
				int qty = dis1.readInt();
				int ic2 = dis2.readInt();
				int tc = dis2.readInt();
				int tqty= dis2.readInt();

				if(tc==1)
				{
					itemcode=ic1;
					updatedqty=qty+tqty;
				}
				else
				{
					itemcode=ic1;
					updatedqty=qty-tqty;
				}
				dos.writeInt(itemcode);
				dos.writeInt(updatedqty);
			}
				dos.close();
				fos.close();
				dis1.close();
				fis1.close();
				dis2.close();
				fis2.close();
			}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		try 
		{
			FileInputStream fis= new FileInputStream("D:/JaliJavaProg/FileEx/NewMaster.txt");
			DataInputStream dis= new DataInputStream(fis);
			for(int i=0;i<=3;i++)
			{
				System.out.println("Item Code ="+dis.readInt()+" ");
				System.out.println("Updated quantity = "+dis.readInt());
			}
			fis.close();
			dis.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}			// End of main() method 

		static String valueReader2()throws IOException
		{
			InputStreamReader r= new InputstreamReader(System.in);
			BufferedReader in=new BufferedReader(r);
			String s=in.readLine();
			return s;
		}

		static String valueReader2()throws IOException
		{
			InputStreamReader r= new InputstreamReader(System.in);
			BufferedReader in=new BufferedReader(r);
			String s=in.readLine();
			Integer x =new Integer(s);
			int y=x.intValue();
			return y;
		}
}