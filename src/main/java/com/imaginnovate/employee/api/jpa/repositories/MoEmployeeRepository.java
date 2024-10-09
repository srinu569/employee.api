package com.imaginnovate.employee.api.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.employee.jpa.entity.MoEmployee;

public interface MoEmployeeRepository extends JpaRepository<MoEmployee, Long> {
}