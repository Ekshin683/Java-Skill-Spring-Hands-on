package com.employee;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @DataJpaTest: loads only the JPA layer (repositories + entities).
 * Does NOT start the full Spring context or web layer.
 * Uses an embedded H2 DB automatically — fast and isolated.
 */
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department engineering;
    private Department hr;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();

        engineering = departmentRepository.save(
            Department.builder()
                .name("Engineering")
                .description("Tech team")
                .build());

        hr = departmentRepository.save(
            Department.builder()
                .name("Human Resources")
                .description("People team")
                .build());

        employeeRepository.save(Employee.builder()
            .name("Alice").email("alice@co.com")
            .salary(90000.0).hireDate(LocalDate.now())
            .department(engineering).build());

        employeeRepository.save(Employee.builder()
            .name("Bob").email("bob@co.com")
            .salary(70000.0).hireDate(LocalDate.now())
            .department(engineering).build());

        employeeRepository.save(Employee.builder()
            .name("Carol").email("carol@co.com")
            .salary(60000.0).hireDate(LocalDate.now())
            .department(hr).build());
    }

    @Test
    void shouldSaveAndFindEmployee() {
        assertTrue(employeeRepository.existsByEmail("alice@co.com"));
    }

    @Test
    void shouldFindByDepartment() {
        List<Employee> result = employeeRepository
                .findByDepartmentId(engineering.getId());
        assertEquals(2, result.size());
    }

    @Test
    void shouldFindBySalaryRange() {
        List<Employee> result = employeeRepository
                .findBySalaryBetween(65000.0, 95000.0);
        assertEquals(2, result.size());
    }

    @Test
    void shouldPageEmployees() {
        Page<Employee> page = employeeRepository.findAll(
                PageRequest.of(0, 2, Sort.by("salary").descending()));
        assertEquals(2, page.getContent().size());
        assertEquals(3, page.getTotalElements());
        assertEquals("Alice", page.getContent().get(0).getName());
    }

    @Test
    void shouldFindByNameIgnoreCase() {
        List<Employee> result = employeeRepository
                .findByNameContainingIgnoreCase("alice");
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    void shouldCountByDepartment() {
        long count = employeeRepository
                .countByDepartmentId(engineering.getId());
        assertEquals(2, count);
    }

    @Test
    void shouldReturnProjections() {
        var summaries = employeeRepository.findAllProjectedBy();
        assertEquals(3, summaries.size());
        assertNotNull(summaries.get(0).getName());
        assertNotNull(summaries.get(0).getEmail());
    }

    @Test
    void shouldFindAboveAverageSalary() {
        // Average = (90000 + 70000 + 60000) / 3 = 73333
        // Only Alice (90000) is above average
        List<Employee> result = employeeRepository
                .findEmployeesAboveAverageSalary();
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }
}