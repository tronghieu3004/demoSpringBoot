package com.letronghieu.jpacrudapi.controller;

import com.letronghieu.jpacrudapi.model.Employee;
import org.springframework.web.bind.annotation.*;

@RestController  
//=@Controller + @ResponseBody

public class EmployeeController {
    //GET
    @GetMapping("/employees")// = @RequestMapping(value = "/employees",method = RequestMethod.GET)
    public String getEmployees(){
        return "the list of Employees";
    }

    @GetMapping("/employee")// = @RequestMapping(value =   "/employ/{id}",method = RequestMethod.GET)
    public String getEmployee(@RequestParam Long id){
        return " Employee has ID " + id;
    }

    //POST
    @PostMapping("/employee")
    public String saveEmployee(@RequestBody Employee employee) {
        return "Adding the Employee detail to db \n"+ employee;
    }



    //PUT
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee
    ){
        System.out.println("updating the employee data for the id "+ id);
        return employee;
    }

    //DELETE
    @DeleteMapping("/employees")
    public String deleteEmployees(){
        return "Deleting All Employees";
    }
    @DeleteMapping("/employee")
    public String deleteEmployee(@RequestParam Long id){
        return "Deleting Employees has ID: "+ id;
    }
}
