package com.employee.repository;

import com.employee.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 * JpaRepository<Department, Long>:
 *   - Department: the entity type this repository manages
 *   - Long: the type of the primary key
 *
 * Spring generates the full implementation at runtime.
 * You get these methods for free:
 *   save(), findById(), findAll(), deleteById(), count(), existsById(), etc.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // ── Derived query methods ──────────────────────────────────────────────
    // Spring reads the method name and generates SQL automatically.

    // SELECT * FROM departments WHERE name = ?
    Optional<Department> findByName(String name);

    // SELECT * FROM departments WHERE name LIKE %?%
    List<Department> findByNameContainingIgnoreCase(String keyword);

    // SELECT * FROM departments WHERE description LIKE %?%
    List<Department> findByDescriptionContaining(String keyword);

    // SELECT (COUNT(*) > 0) FROM departments WHERE name = ?
    boolean existsByName(String name);

    // ── Custom JPQL query ──────────────────────────────────────────────────
    // JPQL uses entity class names and field names, NOT table/column names
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.id = :id")
    Optional<Department> findByIdWithEmployees(@Param("id") Long id);

    // ── Native SQL query ───────────────────────────────────────────────────
    // nativeQuery = true: use actual SQL (table names, column names)
    @Query(value = "SELECT * FROM departments ORDER BY name ASC", nativeQuery = true)
    List<Department> findAllOrderedByNameNative();
}