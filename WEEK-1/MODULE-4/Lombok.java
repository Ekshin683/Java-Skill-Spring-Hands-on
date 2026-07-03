//Lombok: it's a java library that automatically write boilerplate code using getters, setters and constructors. It reduces the code and makes it more readable.

//without lombok:
public class Employee {
    private String name;
    private String email;
    private double salary;

    public Employee() {}
    public Employee(String name, String email, double salary) {
        this.name = name; this.email = email; this.salary = salary;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Employee{name=" + name + ", email=" + email + ", salary=" + salary + "}";
    }
    @Override
    public boolean equals(Object o) { /* ... 10 more lines */ }
    @Override
    public int hashCode() { /* ... 5 more lines */ }
}

//with lombok:
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee {
    private String name;
    private String email;
    private double salary;
}