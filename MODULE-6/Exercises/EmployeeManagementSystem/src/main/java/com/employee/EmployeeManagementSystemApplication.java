package com.employee;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import com.employee.service.BatchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    /*
     * CommandLineRunner runs once after the Spring context is fully loaded.
     * Perfect for seeding test data.
     */
    @Bean
    public CommandLineRunner seedData(
            DepartmentRepository deptRepo,
            EmployeeRepository empRepo,
            BatchService batchService) {

        return args -> {

            System.out.println("\n=== Seeding departments ===");

            Department engineering = deptRepo.save(
                Department.builder()
                    .name("Engineering")
                    .description("Software development team")
                    .build());

            Department hr = deptRepo.save(
                Department.builder()
                    .name("Human Resources")
                    .description("People and culture team")
                    .build());

            Department finance = deptRepo.save(
                Department.builder()
                    .name("Finance")
                    .description("Financial planning and analysis")
                    .build());

            System.out.println("Departments created: "
                + deptRepo.count());

            System.out.println("\n=== Seeding employees ===");

            empRepo.save(Employee.builder()
                .name("Alice Johnson")
                .email("alice.johnson@company.com")
                .salary(95000.0)
                .hireDate(LocalDate.of(2020, 3, 15))
                .department(engineering)
                .employmentType(Employee.EmploymentType.FULL_TIME)
                .build());

            empRepo.save(Employee.builder()
                .name("Bob Smith")
                .email("bob.smith@company.com")
                .salary(85000.0)
                .hireDate(LocalDate.of(2019, 7, 1))
                .department(engineering)
                .employmentType(Employee.EmploymentType.FULL_TIME)
                .build());

            empRepo.save(Employee.builder()
                .name("Carol Davis")
                .email("carol.davis@company.com")
                .salary(72000.0)
                .hireDate(LocalDate.of(2021, 1, 20))
                .department(hr)
                .employmentType(Employee.EmploymentType.FULL_TIME)
                .build());

            empRepo.save(Employee.builder()
                .name("Dave Wilson")
                .email("dave.wilson@company.com")
                .salary(45000.0)
                .hireDate(LocalDate.of(2022, 6, 10))
                .department(finance)
                .employmentType(Employee.EmploymentType.PART_TIME)
                .build());

            System.out.println("Employees created: " + empRepo.count());

            System.out.println("\n=== Testing pagination ===");
            var page = empRepo.findAll(
                org.springframework.data.domain.PageRequest.of(0, 2,
                    org.springframework.data.domain.Sort.by("salary").descending()));
            System.out.println("Page 0 (size 2, sorted by salary desc):");
            page.getContent().forEach(e ->
                System.out.println("  " + e.getName() + " — $" + e.getSalary()));
            System.out.println("Total employees: " + page.getTotalElements());
            System.out.println("Total pages: " + page.getTotalPages());

            System.out.println("\n=== Testing projections ===");
            empRepo.findAllProjectedBy().forEach(s ->
                System.out.println("  Summary: " + s.getNameWithEmail()));

            System.out.println("\n=== Testing batch insert ===");
            batchService.batchInsertEmployees(engineering.getId(), 50);
            System.out.println("Total employees after batch: " + empRepo.count());

            System.out.println("\n=== Application ready! ===");
            System.out.println("H2 Console: http://localhost:8080/h2-console");
            System.out.println("API Base:   http://localhost:8080/api");
        };
    }
}