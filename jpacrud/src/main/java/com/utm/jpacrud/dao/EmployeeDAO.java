package com.utm.jpacrud.dao;

import com.utm.jpacrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Integer> {

    @Query("SELECT e.gender, COUNT(e) FROM Employee e GROUP BY e.gender")
    List<Object[]> countByGender();

    @Query("SELECT e.department, e.gender, COUNT(e) FROM Employee e GROUP BY e.department, e.gender")
    List<Object[]> countByDepartmentAndGender();
}





