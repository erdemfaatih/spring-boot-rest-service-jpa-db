package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService theEmployeeService;


    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        this.theEmployeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){

        return theEmployeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee theEmployee(@PathVariable int employeeId){
        Employee employee = theEmployeeService.findById(employeeId);

        if (employee == null){
            throw  new RuntimeException("Employee id not found - "+employeeId);
        }

        return employee;

    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee dbEmployee = theEmployeeService.save(theEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee theEmployee = theEmployeeService.save(employee);
        return theEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee = theEmployeeService.findById(employeeId);
        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        theEmployeeService.delete(employeeId);
        return "Deleted employee id - "+employeeId;

    }

}
