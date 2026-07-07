package com.employee.controller;

import com.employee.entity.Employee;
import com.employee.projection.EmployeeDTO;
import com.employee.projection.EmployeeSummary;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // GET /api/employees?page=0&size=10&sortBy=name&direction=asc
    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return employeeService.getEmployeesPagedAndSorted(
                page, size, sortBy, direction);
    }

    // GET /api/employees/1
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeWithDepartment(id));
    }

    // GET /api/employees/department/2
    @GetMapping("/department/{deptId}")
    public List<Employee> getByDepartment(@PathVariable Long deptId) {
        return employeeService.getEmployeesByDepartment(deptId);
    }

    // GET /api/employees/search?keyword=john&page=0&size=5
    @GetMapping("/search")
    public Page<Employee> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size) {
        return employeeService.searchEmployeesPaged(keyword, page, size);
    }

    // GET /api/employees/high-earners?minSalary=70000
    @GetMapping("/high-earners")
    public List<Employee> getHighEarners(@RequestParam Double minSalary) {
        return employeeService.getHighEarners(minSalary);
    }

    // GET /api/employees/above-average
    @GetMapping("/above-average")
    public List<Employee> getAboveAverage() {
        return employeeService.getEmployeesAboveAverageSalary();
    }

    // GET /api/employees/summaries — projection: name + email only
    @GetMapping("/summaries")
    public List<EmployeeSummary> getSummaries() {
        return employeeService.getAllSummaries();
    }

    // GET /api/employees/dtos — DTO projection
    @GetMapping("/dtos")
    public List<EmployeeDTO> getDTOs() {
        return employeeService.getAllDTOs();
    }

    // POST /api/employees?departmentId=1
    // Body: { "name": "Alice", "email": "alice@co.com", "salary": 75000 }
    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee,
            @RequestParam Long departmentId) {
        Employee saved = employeeService.createEmployee(employee, departmentId);
        return ResponseEntity.status(201).body(saved);
    }

    // PUT /api/employees/1
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    // PUT /api/employees/1/transfer?newDepartmentId=2
    @PutMapping("/{id}/transfer")
    public ResponseEntity<Employee> transferEmployee(
            @PathVariable Long id, @RequestParam Long newDepartmentId) {
        return ResponseEntity.ok(
                employeeService.transferDepartment(id, newDepartmentId));
    }

    // PUT /api/employees/department/1/raise?percent=10
    @PutMapping("/department/{deptId}/raise")
    public ResponseEntity<String> giveDepartmentRaise(
            @PathVariable Long deptId, @RequestParam Double percent) {
        int count = employeeService.giveDepartmentRaise(deptId, percent);
        return ResponseEntity.ok(count + " employees received a raise.");
    }

    // DELETE /api/employees/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}