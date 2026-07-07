package com.employee.controller;

import com.employee.entity.Department;
import com.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // GET /api/departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // GET /api/departments/1
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    // GET /api/departments/1/employees — load with employees
    @GetMapping("/{id}/employees")
    public ResponseEntity<Department> getDepartmentWithEmployees(@PathVariable Long id) {
        return ResponseEntity.ok(
                departmentService.getDepartmentByIdWithEmployees(id));
    }

    // GET /api/departments/search?keyword=eng
    @GetMapping("/search")
    public List<Department> search(@RequestParam String keyword) {
        return departmentService.searchByName(keyword);
    }

    // POST /api/departments
    // Request body: { "name": "Engineering", "description": "Tech team" }
    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @RequestBody Department department) {
        Department saved = departmentService.createDepartment(department);
        return ResponseEntity.status(201).body(saved);
    }

    // PUT /api/departments/1
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id, @RequestBody Department department) {
        return ResponseEntity.ok(
                departmentService.updateDepartment(id, department));
    }

    // DELETE /api/departments/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}