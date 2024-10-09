package com.imaginnovate.employee.api.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.employee.api.jpa.entity.MoEmployee;

public interface MoEmployeeRepository extends JpaRepository<MoEmployee, Long> {

	Optional<MoEmployee> findById(String employeeId);
}