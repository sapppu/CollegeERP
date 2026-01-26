import java.util.Scanner;

class weightexc extends Exception
{
    weightexc(String s)
    {
        super(s);
    }
}

public class WeightException {
    static void accept() throws weightexc
    {
        int p_id,p_weight;
        String p_name;
        Scanner sc = new Scanner(System.in);
        System.out.print("\n Enter ProductId:");
        p_id = sc.nextInt();
        System.out.printf("\n Enter Product Name:");
        p_name = sc.next();
        System.out.printf("\n Enter Product weight:");
        p_weight = sc.nextInt();
        if(p_weight > 100)
        {
            throw new weightexc("Invalid Product Exception");
        }
        else{
            System.out.printf("\n Product Details : \n ID: %d \n Name: %s \n Weight: %d",p_id,p_name,p_weight);
        }
    }
    public static void main(String[] args) {
        try{
            accept();
        } catch (weightexc weightexc) {
            weightexc.printStackTrace();
        }
    }
}
