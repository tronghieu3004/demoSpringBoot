package com.utm.jpacrud.controller;


import com.utm.jpacrud.dao.EmployeeDAO;
import com.utm.jpacrud.model.Employee;

import com.utm.jpacrud.model.dto.GenderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeDAO employeeDAO;
    //GET

    @GetMapping("/employees")
        public List<Employee> getAll(){
            return employeeDAO.findAll();
        }

    @GetMapping("/employee")
    public Employee getById(@RequestParam int id) {
        Optional<Employee> employee = employeeDAO.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }else {
            throw new RuntimeException("Employee not found for the id "+id);
        }
    }
    @GetMapping("/employees/gender/department")
    public List<GenderDTO> getGenderCounts() {
        return employeeDAO.countByGender();
    }

    //POST
    @PostMapping("/employee")
    public Employee save(@RequestBody Employee employeeObj) {
        return employeeDAO.save(employeeObj);
    }
    @PostMapping("/employees")
    public List<Employee> saveAll(@RequestBody List<Employee> employees) {
        return employeeDAO.saveAll(employees);
    }
    //DETELE
    @DeleteMapping("/employee/{id}")
    public String delete(@PathVariable int id) {
        Optional<Employee> employee = employeeDAO.findById(id);
        if(employee.isPresent()) {
            employeeDAO.delete(employee.get());
            return "Employee is deleted with id "+id;
        }else {
            throw new RuntimeException("Employee not found for the id "+id);
        }
    }
    //PUT
    @PutMapping("/employee")
    public Employee update(@RequestBody Employee employeeObj) {
        return employeeDAO.save(employeeObj);
    }

}
