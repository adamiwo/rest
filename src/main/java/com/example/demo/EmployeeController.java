package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> all()
    {
        return repository.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee)
    {
        return repository.save(newEmployee);
    }
    @GetMapping("/employees/{id}")
    Employee oneEmployees(@PathVariable Long id)
    {
        return repository.getById(id).orElseThrow(()->new EmployeeNotFoundExceptions(id));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
    {
        return repository.getById(id)
                .map(employee ->{
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet();
    }
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id)
    {
        repository.deleteById(id);
    }










}
