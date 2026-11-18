
package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // In-memory storage
    private final List<Employee> employees = new ArrayList<>();

    // Add some sample employees
    public EmployeeController() {
        employees.add(new Employee(101, "Rahul", "IT", 45000));
        employees.add(new Employee(102, "Priya", "HR", 40000));
        employees.add(new Employee(103, "Amit", "Finance", 50000));
    }

    // 1. POST - Add Employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(
            @RequestBody Employee employee) {

        employees.add(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employee);
    }

    // 2. GET - Retrieve All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // 3. GET - Retrieve Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {

        for (Employee e : employees) {

            if (e.getId() == id) {
                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Employee not found"));
    }

    // 4. PUT - Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmployee) {

        for (Employee e : employees) {

            if (e.getId() == id) {

                e.setName(updatedEmployee.getName());
                e.setDepartment(updatedEmployee.getDepartment());
                e.setSalary(updatedEmployee.getSalary());

                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Employee not found"));
    }

    // 5. DELETE - Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {

        for (Employee e : employees) {

            if (e.getId() == id) {

                employees.remove(e);

                return ResponseEntity.ok(
                        Map.of("message", "Employee deleted successfully")
                );
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Employee not found"));
    }
}
