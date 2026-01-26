
/* Example on Unit Test 1 */
import java.util.*;
public class Employee
{
	Date doa;
	String name;
	String empcode;
	Employee(String name,Date doa,String empcode)
	{
		this.name=name;
		doa = new Date();
		this.doa = doa;
		this.empcode = empcode;
	}
	void displayEmployee()
	{
		System.out.println(" \n Name :: " + name + "  \n Employee code :: "+ empcode +"  \n Date of appointment ::" + doa.getDate()+"/"+doa.getMonth()+"/"+doa.getYear());
	}
	public static void main(String args[])
	{
		Employee e[];
		int k;
		e = new Employee[5];
		e[0]= new Employee("Rohit",new Date(98,3,22),"EmpCd_111");
		e[1]= new Employee("Mahesh",new Date(96,3,22),"EmpCd_112");
		e[2]= new Employee("Aniket",new Date(95,3,22),"EmpCd_113");
		e[3]= new Employee("Aruna",new Date(98,4,22),"EmpCd_114");
		e[4]= new Employee("Priti",new Date(93,3,22),"EmpCd_115");
		
		for(int i=0;i<=3;i++)
		{
			for(int j=i+1;j<=4;j++)
			{
				if(e[i].doa.after(e[j].doa))
				{
				
					Employee temp;
					temp = e[i];
					e[i]= e[j];
					e[j]= temp;
				}
			}
		}
		for(k=0;k<5;k++)
		{
			e[k].displayEmployee();
		}
	}
}