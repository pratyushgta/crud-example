// controller to handle HTTP requests to the application
package com.crudexample.crud.controller;

import com.crudexample.crud.entity.Employee;
import com.crudexample.crud.service.EmployeeService;
import com.crudexample.crud.service.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/code")
public class EmployeeController {
    @RequestMapping("/display")
    String display() {
        return "Hello World";
    }

    @Autowired // This means to get the employee service bean
    //EmployeeService employeeService;
    private EmployeeServiceInterface employeeServiceInterface;

    // don't want data to be appended in URL instead, the data should be in body
    // @requestbody because this annotation indicates that method should be bound to a body of a web request
    // it helps to bind parameter
    //POST: http://localhost:8080/save  Put JSON info in body
    @PostMapping("/save") //map only POST requests
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee saved;
        try {
            saved = employeeServiceInterface.addEmployee(employee);
        } catch (Exception e) {
            return new ResponseEntity("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Response entity is an object used to return to client to tell what happened inside service
        //return new ResponseEntity<Employee>("Saved: "+saved, HttpStatus.CREATED);
        return new ResponseEntity("Saved: " + saved.getId(), HttpStatus.CREATED);
    }

    //GET: http://localhost:8080/all
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = employeeServiceInterface.getAllEmployees();
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }

    // retrieve using ID
    //GET: http://localhost:8080/all/53
    @GetMapping("/all/{empid}")
    public ResponseEntity<Employee> getEmpByID(@PathVariable("empid") Long empidL) {
        Employee retrieved;
        try {
            retrieved = employeeServiceInterface.getEmpByID(empidL);
        } catch (Exception e) {
            return new ResponseEntity("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Employee>(retrieved, HttpStatus.OK);
    }

    //delete using emp id
    //DELETE: http://localhost:8080/delete/53
    @DeleteMapping("/delete/{empid}")
    public ResponseEntity<Void> deletEmpByID(@PathVariable("empid") Long empidL) {
        try {
            employeeServiceInterface.deleteEmployee(empidL);
        } catch (Exception e) {
            return new ResponseEntity("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    //update
    //PUT: http://localhost:8080/update
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee saved = employeeServiceInterface.addEmployee(employee);
        return new ResponseEntity("Updated: " + employee.getId(), HttpStatus.CREATED);
    }
}
