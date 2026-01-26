/* Q1:  W.A.P to define INTERFACE  CalculateResult with methods CalculateTotal(),CalculatePercentage(),
    CalculateGrade(); 
    Define a Class Student which implements this interface and has appropritate method  
    definiation/ overriding  for this.
    Take details of n students with array of objects and display the result and if marks is 
     less than zero fire User defined Exception << ZeroMarksException>>
 */
import java.util.Scanner;

interface CalculateResult 
{
    void CalculateTotal();
    void CalculatePercentage();
    void CalculateGrade();
}

class Student implements CalculateResult
{
   	 int rollno, marks[] = new int[5];
    	String name;
    	double per,total;
   	Student(String name,int roll,int m[])
	{
    		this.name = name;
    		this.rollno =roll;
    		this.marks =m;
	}

	public void CalculateTotal()
	{
         		for (int i = 0; i < marks.length; i++)
		{
            			total =total + marks[i];
         		}
	}

	public void CalculatePercentage()
	{
       	 	per = total/5.0;
	}
	public void CalculateGrade()
	{
        		System.out.println("Total Marks :"+total);
        		System.out.println("Percentage  :"+per);
        		if(per >= 90)
		{
            			System.out.println("Student is passed with grade A+ ");
        		}
        		else if(per >= 80 && per<90)
		{
            			System.out.println("Student is passed with Grade A");
       	 	}
        		else if(per >= 70 && per < 80)
		{
            			System.out.println("Student is passed with Grade B");
      	  	}
       		 else if(per >=55 && per <70)
		{
            			System.out.println("Grade is passed with Grade C");
       	 	}
       		 else if(per >=35 && per<55)
		{
            			System.out.println("Student is passed with Grade D");
       	 	}
        		else
		{
            			System.out.println("Student is Fail ");
       	 	}
   	 }   
}
class  ZeroMarksException extends Exception 
{
    ZeroMarksException(String msg)
	{
        		super(msg);
        		System.out.println("Marks Entered cannot be zero!!");
    	}
}
public class interface11
{
    public static void main(String[] args) throws ZeroMarksException
	{      
       	try (Scanner sc = new Scanner(System.in)) 
	{
           		System.out.println("Enter the number of Students you want save details::");
            		int n = sc.nextInt();
            		//int m[] = new int[5];
           	 	Student a[] = new Student[n];
            		System.out.println("Enter the Stuent Details\n");
            		for (int i = 0; i < a.length; i++) 
		{
			int m[] = new int[5];
                			System.out.println("Enter the name of Student"+(i+1)+":\t");
                			String name = sc.next();
                			System.out.println("Enter the roll no of student "+(i+1)+":\t");
                			int roll = sc.nextInt();
                			System.out.println("Enter the Marks of 5 subjects of  Student"+(i+1)+":");
                			for(int j =0;j<m.length;j++)
			{
                    			m[j] = sc.nextInt();
                    			if (m[j] <=0) 
				{
                        				throw new ZeroMarksException("MARKS CANNOT BE ZERO \n");
                    			}
                			}

                			a[i] = new Student(name,roll,m);
               			a[i].CalculateTotal();;
                			a[i].CalculatePercentage();
            		}
            		System.out.println("Displaying Student Details::");
           		for (int i = 0; i < a.length; i++) 
		{
               			 System.out.println("------------------------------");
                			a[i].CalculateGrade();
                			System.out.println("------------------------------");
        		}
       		sc.close();
        	} 
	catch (ZeroMarksException  | ArrayIndexOutOfBoundsException e) 
	{
            		System.out.println("\t "+e.getMessage());
		System.out.println("\t "+e.getMessage());
        	} 
	/*catch (ArrayIndexOutOfBoundsException e) 
	{
            		System.out.println("\t "+e1.getMessage());
        	}*/
    }
}