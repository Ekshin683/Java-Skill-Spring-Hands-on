package com.employee.service;

import com.employee.entity.Department;
import com.employee.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // ── CREATE ──────────────────────────────────────────────────────────────
    public Department createDepartment(Department department) {
        // Guard against duplicate names
        if (departmentRepository.existsByName(department.getName())) {
            throw new IllegalStateException(
                "Department already exists: " + department.getName());
        }
        // save() performs INSERT when entity has no id, UPDATE when it does
        return departmentRepository.save(department);
    }

    // ── READ ────────────────────────────────────────────────────────────────
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        // orElseThrow: clean exception if not found instead of returning null
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));
    }

    public Department getDepartmentByIdWithEmployees(Long id) {
        return departmentRepository.findByIdWithEmployees(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));
    }

    // ── UPDATE ──────────────────────────────────────────────────────────────
    public Department updateDepartment(Long id, Department updated) {
        Department existing = getDepartmentById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        // save() on an existing (managed) entity performs UPDATE
        return departmentRepository.save(existing);
    }

    // ── DELETE ──────────────────────────────────────────────────────────────
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found: " + id);
        }
        // CascadeType.ALL on employees means this also deletes all employees
        departmentRepository.deleteById(id);
    }

    public List<Department> searchByName(String keyword) {
        return departmentRepository.findByNameContainingIgnoreCase(keyword);
    }
}