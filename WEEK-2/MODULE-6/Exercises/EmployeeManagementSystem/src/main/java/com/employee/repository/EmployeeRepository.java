package com.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;
import com.employee.projection.EmployeeDTO;
import com.employee.projection.EmployeeSummary;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // ── Derived query methods ──────────────────────────────────────────────

    // SELECT * FROM employees WHERE email = ?
    Optional<Employee> findByEmail(String email);

    // SELECT * FROM employees WHERE name = ?
    List<Employee> findByName(String name);

    // SELECT * FROM employees WHERE name LIKE %?%
    List<Employee> findByNameContainingIgnoreCase(String keyword);

    // SELECT * FROM employees WHERE department_id = ?
    List<Employee> findByDepartmentId(Long departmentId);

    // SELECT * FROM employees WHERE department.name = ?
    // Spring joins departments automatically
    List<Employee> findByDepartmentName(String departmentName);

    // SELECT * FROM employees WHERE salary >= ?
    List<Employee> findBySalaryGreaterThanEqual(Double salary);

    // SELECT * FROM employees WHERE salary BETWEEN ? AND ?
    List<Employee> findBySalaryBetween(Double min, Double max);

    // SELECT * FROM employees WHERE employment_type = ?
    List<Employee> findByEmploymentType(Employee.EmploymentType type);

    // SELECT (COUNT(*) > 0) FROM employees WHERE email = ?
    boolean existsByEmail(String email);

    // SELECT COUNT(*) FROM employees WHERE department_id = ?
    long countByDepartmentId(Long departmentId);

    // ── Pagination support ─────────────────────────────────────────────────
    // Returns a Page object containing content + metadata (totalPages, totalElements)
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);
    Page<Employee> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    // ── Custom JPQL queries ────────────────────────────────────────────────

    // Fetch employee with department in one query (avoids N+1 problem)
    @Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
    Optional<Employee> findByIdWithDepartment(@Param("id") Long id);

    // Find all employees in a department by department name
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findByDepartmentNameQuery(@Param("deptName") String deptName);

    // Find employees with salary above average
    @Query("SELECT e FROM Employee e WHERE e.salary > " +
           "(SELECT AVG(e2.salary) FROM Employee e2)")
    List<Employee> findEmployeesAboveAverageSalary();

       // Exercise 5 — Named query (defined on Employee entity with @NamedQuery)
       @Query(name = "Employee.findByDepartmentName")
       List<Employee> findByDepartmentNameNamed(@Param("deptName") String deptName);

    // ── Modifying query (UPDATE or DELETE) ────────────────────────────────
    // @Modifying: tells Spring this query changes data
    // @Transactional: required for any data-changing operation
    @Modifying
    @Query("UPDATE Employee e SET e.salary = e.salary * (1 + :percent / 100) " +
           "WHERE e.department.id = :deptId")
    int giveDepartmentRaise(@Param("deptId") Long deptId, @Param("percent") Double percent);

    @Modifying
    @Query("UPDATE Employee e SET e.salary = :salary WHERE e.id = :id")
    int updateSalary(@Param("id") Long id, @Param("salary") Double salary);

    // ── Projection queries (Exercise 8) ───────────────────────────────────

    // Interface-based projection — Spring generates proxy, fetches only declared fields
    List<EmployeeSummary> findAllProjectedBy();

    // DTO-based projection using JPQL constructor expression
    @Query("SELECT new com.employee.projection.EmployeeDTO(" +
           "e.id, e.name, e.email, e.salary, e.department.name) " +
           "FROM Employee e")
    List<EmployeeDTO> findAllAsDTO();

    // Dynamic projection — caller decides the return type at call time
    <T> List<T> findByDepartmentId(Long departmentId, Class<T> type);

    // ── Native SQL with projection ─────────────────────────────────────────
    @Query(value = "SELECT e.id, e.name, e.email, e.salary, d.name as departmentName " +
                   "FROM employees e JOIN departments d ON e.department_id = d.id " +
                   "WHERE e.salary > :minSalary",
           nativeQuery = true)
    List<Object[]> findHighEarnersNative(@Param("minSalary") Double minSalary);
}