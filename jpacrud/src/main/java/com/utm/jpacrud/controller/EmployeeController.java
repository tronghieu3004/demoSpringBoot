package com.utm.jpacrud.controller;


import com.utm.jpacrud.dao.EmployeeDAO;
import com.utm.jpacrud.model.Employee;

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
    @GetMapping("/employees/gender")
    public Map<String, Long> countByGender() {
        List<Object[]> counts = employeeDAO.countByGender();
        Map<String, Long> result = new HashMap<>();
        try {

            for (Object[] count : counts) {
                String gender = (String) count[0];
                Long total = (Long) count[1];
                result.put(gender, total);
            }
            return result;

        }catch (Exception e){
            return null;
        }
    }
    @GetMapping("/employees/gender/department")
    public Map<String, Map<String, Long>> countByDepartmentAndGender() {
        List<Object[]> counts = employeeDAO.countByDepartmentAndGender();
        Map<String, Map<String, Long>> result = new HashMap<>();
        for (Object[] count : counts) {
            String department = (String) count[0];
            String gender = (String) count[1];
            Long total = (Long) count[2];
            result.computeIfAbsent(department, k -> new HashMap<>()).put(gender, total);
        }
        return result;
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
