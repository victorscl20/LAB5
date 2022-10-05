package com.pucp.lab5gtics.repository;

import com.pucp.lab5gtics.entity.DepartmentsEntity;
import com.pucp.lab5gtics.entity.EmployeesEntity;
import com.pucp.lab5gtics.entity.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Completar
public interface JobRepository extends JpaRepository<JobsEntity, Integer> {
}
