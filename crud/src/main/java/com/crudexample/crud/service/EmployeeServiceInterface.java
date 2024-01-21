package com.crudexample.crud.service;

import com.crudexample.crud.entity.Employee;

import java.util.List;

public interface EmployeeServiceInterface{
   Employee addEmployee(Employee employee);

   List<Employee> getAllEmployees();

   Employee getEmpByID(Long empidL);

   void deleteEmployee(Long empidL);

}
