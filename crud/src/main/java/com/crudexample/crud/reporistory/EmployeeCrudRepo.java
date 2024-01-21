package com.crudexample.crud.reporistory;

import com.crudexample.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCrudRepo extends JpaRepository<Employee, Long> {
}


