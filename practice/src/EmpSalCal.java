import java.util.*;
public class EmpSalCal {
    public static void main(String[] args) {
        try {
            Employee_pay e1 = new Employee_pay("Raj", 15000.0);
            Employee_pay e2 = new Employee_pay("Rohit", 2000.0);
            e1.calSal();
            e2.calSal();
        } catch (PayOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
class PayOutOfBoundsException extends Exception {
    PayOutOfBoundsException(double basic_sal) {
        super("Error in salary calculation:= " + basic_sal);
    }
}
class Employee_pay {
    String name;
    double basic_sal;
    Employee_pay(String name, double basic_sal) {
        this.name = name;
        this.basic_sal = basic_sal;
    }
    void calSal() throws PayOutOfBoundsException {
        double da, hra, va, grs, net, tds, pf;
        if (basic_sal < 3000)
            throw new PayOutOfBoundsException(basic_sal);
        da = (basic_sal * 0.25);
        hra = (basic_sal * 0.15);
        va = (basic_sal * 0.10);
        grs = basic_sal + da + hra + va;
        tds = (grs * 0.10);
        pf = (grs * 0.10);
        net = grs - (pf + tds);
        System.out.println("Employee: " + name);
        System.out.println("Basic Salary: " + basic_sal);
        System.out.println("DA: " + da + ", HRA: " + hra + ", VA: " + va);
        System.out.println("Gross Salary: " + grs);
        System.out.println("TDS: " + tds + ", PF: " + pf);
        System.out.println("Net Salary: " + net);
    }
}
