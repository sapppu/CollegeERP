import java.util.Scanner;

class QuantityException extends Exception
{
    QuantityException(String s)
    {
        super(s);
    }
}
class PriceException extends  Exception
{
    PriceException(String s)
    {
        super(s);
    }
}

class Item {
    String ItemCode,description;
    int quantity, rate;
    Scanner sc = new Scanner(System.in);

    void userdefined(int quantity, int price) throws QuantityException,PriceException {
        if(quantity <= 0)
        {
            throw new QuantityException("Quantity should be greater than 0");
        }
        else if(price <= 0)
        {
            throw new PriceException("\n Please increase the price!!");
        }

    }
    Item() {
        System.out.printf("\n Enter Item Code:");
        ItemCode = sc.next();
        System.out.printf("\n Enter Description:");
        description = sc.next();
        try {
            System.out.printf("\n Enter Quantity:");
            quantity = sc.nextInt();
            System.out.printf("\n Enter Price:");
            rate = sc.nextInt();
            userdefined(quantity,rate);
        }catch (PriceException e) {
            e.printStackTrace();
        } catch (QuantityException e) {
            e.printStackTrace();
        }
    }
    void display()
    {
        System.out.printf("\n ItemCode: %s \n Description: %s \n Rate: %d \n Quantity: %d",ItemCode,description,rate,quantity);
    }
}

public class ItemException {
    public static void main(String[] args) {
        Item item[] = new Item[4];

        for(int i=0;i<4;i++)
        {
            item[i] = new Item();
        }
        for(int i=0;i<4;i++)
        {
            item[i].display();
        }

    }
}
