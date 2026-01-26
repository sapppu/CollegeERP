
abstract class Employee1 {
    String name;
    double salary;

    abstract void calcsal();
}

class TemporaryEmployee extends Employee1 {
    int hoursPerMonth, overtimeHours;
    double hourlyRate = 200;

    TemporaryEmployee(String name, int hours, int overtime) {
        this.name = name;
        this.hoursPerMonth = hours;
        this.overtimeHours = overtime;
    }

    void calcsal() {
        salary = (hoursPerMonth + overtimeHours) * hourlyRate;
        System.out.println("Temporary Employee: " + name + ", Salary: " + salary);
    }
}

class PermanentEmployee extends Employee1 {
    int workingDays;
    double dailyRate = 800;

    PermanentEmployee(String name, int days) {
        this.name = name;
        this.workingDays = days;
    }

    void calcsal() {
        salary = workingDays * dailyRate;
        System.out.println("Permanent Employee: " + name + ", Salary: " + salary);
    }
}

public class TempoPermEmp {
    public static void main(String[] args) {
        TemporaryEmployee t = new TemporaryEmployee("Swapnaj", 160, 10);
        PermanentEmployee p = new PermanentEmployee("Parth", 26);
        t.calcsal();
        p.calcsal();
    }
}
