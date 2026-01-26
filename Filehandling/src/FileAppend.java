import java.util.*;
import java.io.*;
class FileAppend{
    public static void main(String[] args) throws FileNotFoundException,IOException{
        File f1=new File("abc.txt");
        File f2=new File("xyz.txt");
        try{
            if(f1.exists() && f2.exists()){
                FileInputStream fin = new FileInputStream(f2);
                FileOutputStream fout = new FileOutputStream(f1,true);
                int n;
                while((n=fin.read())!=-1){
                    fout.write(n);
                }
                fin.close();
                fout.close();
            }
            else{
                System.out.println("File doesnt exits");
            }
        }catch(FileNotFoundException e1){
            System.out.println("File Error : "+e1);
        }
        catch(IOException e2){
            System.out.println("Error : "+e2);
        }
    }
}