//Single Responsibility Principle (SRP): A class should have only one reason to change. Every class should do exactly one thing and do it well.


// here only on reponsiblity of calculating salary
public class Single_Responsibility {
    public String name;
    public int salary;
    public Single_Responsibility(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    public int calculateSalary(){
        return salary * 12;
    }

}
// here another reposibility but in other class of employee report generation
public class EmployeeReporter {
    public String generateReport(Single_Responsibility employee) {
        return employee.name + employee.calculateSalary();
    }
}