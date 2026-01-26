/*Write an application that will accept details of items such as */
import java.util.*;
class QE extends Exception{
    QE(String s){
        super(s);
    }
}

class PE extends Exception{
    PE(String s){
        super(s);
    }
}

class Item{
    String icode,des;
    int q,r;
    Scanner sc= new Scanner(System.in);
    void userdefined(int q,int p) throws QE,PE{
        if(q<=0){
            throw new QE("Quantity should be grater than 0");
        } else if (p<=0) {
            throw new PE("Price should be grater than 0");
        }
    }

    void display(){
        System.out.println("\n ItemCode : "+icode);
        System.out.println("\n Description : "+des);
        System.out.println("\n Price : "+r);
        System.out.println("\n Quantity : "+q);
    }

    Item(){
        System.out.println("Please enter the item code");
        icode=sc.next();
        System.out.println("Please enter the item description");
        des=sc.next();
        try{
            System.out.println("Please enter the quantity of this Item");
            q=sc.nextInt();
            System.out.println("Please enter the price of this Item");
            r=sc.nextInt();
            userdefined(q,r);
        }
        catch (PE e1){
            e1.printStackTrace();
        }
        catch (QE e2){
            e2.printStackTrace();
        }
    }
}
public class itemDet {
    public static void main(String[] args) {

        Item item[]=new Item[1];
        for(int i=0;i<1;i++){
            item[i]=new Item();
        }
        for(int i=0;i<1;i++){
            item[i].display();
        }
    }
}
