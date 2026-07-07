package com.employee.service;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService {

    /*
     * @PersistenceContext: injects the EntityManager directly.
     * Unlike repositories which abstract SQL, EntityManager gives
     * you direct JPA-level control — needed for batch processing.
     */
    @PersistenceContext
    private EntityManager entityManager;

    // Must match spring.jpa.properties.hibernate.jdbc.batch_size=20
    private static final int BATCH_SIZE = 20;

    /*
     * @Transactional: all DB operations here run in one transaction.
     * If anything fails, the entire batch is rolled back.
     *
     * Batch insert strategy:
     *   1. persist() adds entity to the persistence context (in-memory)
     *   2. flush() sends pending SQL to DB (but doesn't commit yet)
     *   3. clear() evicts all entities from the persistence context
     *      — frees memory so the context doesn't grow unbounded
     *
     * Without flush+clear, Hibernate would accumulate ALL entities
     * in memory and send them all at shutdown — causing OutOfMemoryError
     * for large datasets.
     */
    @Transactional
    public void batchInsertEmployees(Long departmentId, int count) {
        // First fetch the department once (outside the loop)
        Department department = entityManager.find(Department.class, departmentId);
        if (department == null) {
            throw new RuntimeException("Department not found: " + departmentId);
        }

        System.out.println("Starting batch insert of " + count + " employees...");

        List<Employee> batch = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            Employee employee = Employee.builder()
                    .name("BatchEmployee-" + i)
                    .email("batch.employee" + i + "@company.com")
                    .salary(50000.0 + (i * 100))
                    .hireDate(LocalDate.now())
                    .department(department)
                    .build();

            entityManager.persist(employee); // add to persistence context
            batch.add(employee);

            // Every BATCH_SIZE records: flush to DB and clear memory
            if (i % BATCH_SIZE == 0) {
                entityManager.flush(); // sends INSERT SQL to DB
                entityManager.clear(); // frees memory
                System.out.println("Flushed batch " + (i / BATCH_SIZE)
                        + " (" + i + " records so far)");

                // Re-attach department after clear() (clear detaches everything)
                department = entityManager.find(Department.class, departmentId);
            }
        }

        // Flush any remaining records that didn't fill a complete batch
        entityManager.flush();
        entityManager.clear();
        System.out.println("Batch insert complete. Total: " + count + " employees.");
    }

    /*
     * Batch UPDATE: update salaries for all employees in a department.
     * Using a JPQL bulk update is faster than loading + updating one by one.
     */
    @Transactional
    public int batchUpdateSalaries(Long departmentId, Double increasePercent) {
        int updatedCount = entityManager.createQuery(
            "UPDATE Employee e SET e.salary = e.salary * (1 + :pct / 100) " +
            "WHERE e.department.id = :deptId")
                .setParameter("pct", increasePercent)
                .setParameter("deptId", departmentId)
                .executeUpdate(); // returns number of rows affected

        System.out.println("Updated salaries for " + updatedCount + " employees.");
        return updatedCount;
    }

    /*
     * Batch DELETE: remove all employees from a department.
     * JPQL DELETE is much faster than loading each entity and calling remove().
     */
    @Transactional
    public int batchDeleteEmployees(Long departmentId) {
        int deletedCount = entityManager.createQuery(
            "DELETE FROM Employee e WHERE e.department.id = :deptId")
                .setParameter("deptId", departmentId)
                .executeUpdate();

        System.out.println("Deleted " + deletedCount + " employees.");
        return deletedCount;
    }
}