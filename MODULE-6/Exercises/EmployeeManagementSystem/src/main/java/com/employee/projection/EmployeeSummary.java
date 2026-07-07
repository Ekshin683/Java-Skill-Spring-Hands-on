package com.employee.projection;

/*
 * Interface-based projection:
 * Spring generates a dynamic proxy that implements this interface.
 * When you call findAllProjectedBy(), Spring executes:
 *   SELECT name, email FROM employees
 * instead of SELECT * — only the declared fields are fetched.
 *
 * No class body needed — Spring handles everything.
 */
public interface EmployeeSummary {
    // Getter names must exactly match the entity field names
    String getName();
    String getEmail();
    Double getSalary();

    // Computed value using @Value with SpEL expression
    // Spring evaluates the expression and includes the result
    @org.springframework.beans.factory.annotation.Value(
        "#{target.name + ' (' + target.email + ')'}"
    )
    String getNameWithEmail();
}