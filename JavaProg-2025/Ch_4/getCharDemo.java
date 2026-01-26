/* Example Demonstrte getChar() method of String Class */

import java.util.*;
import java.io.*;
import java.lang.String;
public class getCharDemo
{
  public static void main(String[] args)throws IOException
	{
  		String str = "How are you.";
  		char[] arr = new char[7];
  		//Syntax is ** getChar(int sourcebegin int sourceend,char[] destination , int destinationbegin)
		str.getChars(2, 9, arr, 0);
  		System.out.print("The Character array equals: ");
  		System.out.println(arr);
 	 }
}