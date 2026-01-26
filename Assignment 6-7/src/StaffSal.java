abstract class Staff {
    int no;
    String name;

    void Lint_Data(int no, String name) {
        this.no = no;
        this.name = name;
    }
    abstract void calculateSalary();
}

class Typist extends Staff {
    int speed;
    Typist(int no, String name, int speed) {
        Lint_Data(no, name);
        this.speed = speed;
    }

    void calculateSalary() {
        int salary = (speed < 40) ? 4000 : 5000;
        System.out.println("Typist No: " + no + ", Name: " + name + ", Speed: " + speed + ", Salary: " + salary);
    }
}

public class StaffSal {
    public static void main(String[] args) {
        Typist t1 = new Typist(1, "Swapnaj", 35);
        Typist t2 = new Typist(2, "Parth", 45);
        t1.calculateSalary();
        t2.calculateSalary();
    }
}
