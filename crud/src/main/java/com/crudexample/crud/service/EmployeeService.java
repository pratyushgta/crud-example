package com.crudexample.crud.service;

import com.crudexample.crud.entity.Employee;
import com.crudexample.crud.reporistory.EmployeeCrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    @Autowired
    private EmployeeCrudRepo crudRepo;

    // CREATE: to create repository instance method call
    @Override
    public Employee addEmployee(Employee employee) {
        Employee savedEmp = crudRepo.save(employee);
        return savedEmp;
    }

    // RETRIEVE
    @Override
    public List<Employee> getAllEmployees() {
        //find all returns a list
        return crudRepo.findAll();
    }

    @Override
    public Employee getEmpByID(Long empidL) {
        return crudRepo.findById(empidL).get();
    }

    // DELETE
    @Override
    public void deleteEmployee(Long empidL) {
        crudRepo.deleteById(empidL);
    }
}
