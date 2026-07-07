package com.employee.service;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.projection.EmployeeDTO;
import com.employee.projection.EmployeeSummary;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // ── CREATE ──────────────────────────────────────────────────────────────
    public Employee createEmployee(Employee employee, Long departmentId) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalStateException(
                "Email already registered: " + employee.getEmail());
        }
        // Look up the department and link it
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException(
                    "Department not found: " + departmentId));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    // ── READ ────────────────────────────────────────────────────────────────
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    public Employee getEmployeeWithDepartment(Long id) {
        return employeeRepository.findByIdWithDepartment(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByNameContainingIgnoreCase(keyword);
    }

    // ── UPDATE ──────────────────────────────────────────────────────────────
    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = getEmployeeById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setSalary(updated.getSalary());
        existing.setHireDate(updated.getHireDate());
        existing.setEmploymentType(updated.getEmploymentType());
        return employeeRepository.save(existing);
    }

    public Employee transferDepartment(Long employeeId, Long newDepartmentId) {
        Employee employee = getEmployeeById(employeeId);
        Department newDept = departmentRepository.findById(newDepartmentId)
                .orElseThrow(() -> new RuntimeException(
                    "Department not found: " + newDepartmentId));
        employee.setDepartment(newDept);
        return employeeRepository.save(employee);
    }

    // ── DELETE ──────────────────────────────────────────────────────────────
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found: " + id);
        }
        employeeRepository.deleteById(id);
    }

    // ── Exercise 5: Custom queries ───────────────────────────────────────────
    public List<Employee> getEmployeesAboveAverageSalary() {
        return employeeRepository.findEmployeesAboveAverageSalary();
    }

    public List<Employee> getHighEarners(Double minSalary) {
        return employeeRepository.findBySalaryGreaterThanEqual(minSalary);
    }

    @Transactional
    public int giveDepartmentRaise(Long deptId, Double percent) {
        return employeeRepository.giveDepartmentRaise(deptId, percent);
    }

    // ── Exercise 6: Pagination and Sorting ──────────────────────────────────

    // Basic pagination — returns page of employees
    public Page<Employee> getEmployeesPaged(int page, int size) {
        // PageRequest.of(pageNumber, pageSize) — pageNumber is 0-indexed
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    // Pagination with sorting
    public Page<Employee> getEmployeesPagedAndSorted(
            int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }

    // Search with pagination + sorting
    public Page<Employee> searchEmployeesPaged(
            String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("name").ascending());
        return employeeRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    // Sorted list without pagination
    public List<Employee> getEmployeesSortedBySalary() {
        return employeeRepository.findAll(Sort.by("salary").descending());
    }

    public List<Employee> getEmployeesSortedByName() {
        return employeeRepository.findAll(
                Sort.by("name").ascending());
    }

    // ── Exercise 8: Projections ──────────────────────────────────────────────

    // Interface projection — returns only name, email fields
    public List<EmployeeSummary> getAllSummaries() {
        return employeeRepository.findAllProjectedBy();
    }

    // DTO projection — maps to EmployeeDTO constructor
    public List<EmployeeDTO> getAllDTOs() {
        return employeeRepository.findAllAsDTO();
    }

    // Dynamic projection — caller decides the type
    public <T> List<T> getByDepartmentAs(Long deptId, Class<T> type) {
        return employeeRepository.findByDepartmentId(deptId, type);
    }
}