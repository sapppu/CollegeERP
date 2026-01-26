import java.io.*;

class Employee implements Serializable
{

     String name;
     int Salary;
     double incentive;
    public Employee(String name , int Salary , double incentive)
	{
               this.name = name;
               this.Salary = Salary;
               this.incentive = incentive;
    }
    public String toString()
    {
		return "Name :" + name + "Salary: "+ Salary + "Incentives"+ incentive;
    }
};

public class SerializationDemo
{
	public static void main(String args[])
	{
		// Object Serilization

		try{
				Employee object1 = new Employee("Rohit",5000,50.12);
				System.out.println(object1);
				FileOutputStream fos = new FileOutputStream("serial object");
			    	ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(object1);
		   	    oos.flush();
                oos.close();
			}
		catch(IOException io)
		{

		}


		// Object deserilization
		try
		{
			Employee object2;
			FileInputStream fis = new FileInputStream("serial object");
			ObjectInputStream ois = new ObjectInputStream(fis);
			object2 = (Employee) ois.readObject();
			ois.close();
			System.out.println("Object :: " + object2);
		}
		catch(IOException io)
		{

		}
	}
}
