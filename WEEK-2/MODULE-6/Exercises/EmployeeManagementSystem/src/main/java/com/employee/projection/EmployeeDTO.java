package com.employee.projection;

/*
 * Class-based projection (DTO — Data Transfer Object):
 * Used with JPQL constructor expression:
 *   SELECT new com.employee.projection.EmployeeDTO(e.id, e.name, ...)
 *
 * Spring maps each row directly to this constructor.
 * Fields are final (immutable) — good practice for DTOs.
 */
public class EmployeeDTO {

    private final Long id;
    private final String name;
    private final String email;
    private final Double salary;
    private final String departmentName;

    // Constructor must match the JPQL SELECT new ... parameters exactly
    public EmployeeDTO(Long id, String name, String email,
                       Double salary, String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.departmentName = departmentName;
    }

    // Getters only — no setters (immutable DTO)
    public Long getId()             { return id; }
    public String getName()         { return name; }
    public String getEmail()        { return email; }
    public Double getSalary()       { return salary; }
    public String getDepartmentName() { return departmentName; }

    @Override
    public String toString() {
        return "EmployeeDTO{id=" + id + ", name=" + name
                + ", dept=" + departmentName + ", salary=" + salary + "}";
    }
}