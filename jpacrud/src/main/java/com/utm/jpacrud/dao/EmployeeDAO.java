package com.utm.jpacrud.dao;

import com.utm.jpacrud.model.Employee;
import com.utm.jpacrud.model.dto.GenderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Integer> {
    
    @Query(value = "SELECT NEW com.utm.jpacrud.model.dto.GenderDTO(e.department, " +
            "SUM(CASE WHEN e.gender = 'male' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN e.gender = 'female' THEN 1 ELSE 0 END) ) " +
            "FROM Employee e " +
            "GROUP BY e.department")

    List<GenderDTO> countByGender();


}





