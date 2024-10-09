package com.imaginnovate.employee.api.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imaginnovate.employee.api.dto.EmployeeReqDto;
import com.imaginnovate.employee.api.dto.EmployeeResp;
import com.imaginnovate.employee.api.jpa.entity.MoEmployee;
import com.imaginnovate.employee.api.jpa.repositories.MoEmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private MoEmployeeRepository empRepository;

    @Transactional
    public EmployeeReqDto addEmployee(EmployeeReqDto employee) {
       
        MoEmployee moEmployee = new MoEmployee();
        moEmployee.setFirstName(employee.getFirstName());
        moEmployee.setLastName(employee.getLastName());
        moEmployee.setEmail(employee.getEmail());
        moEmployee.setSalaryPerMonth(employee.getSalaryPerMonth());
        moEmployee.setDateOfJoining(employee.getDateOfJoining());
        moEmployee.setPhoneNumbers(employee.getPhoneNumbers());
        moEmployee.setEmployeeId(employee.getEmployeeId());
        
        empRepository.save(moEmployee);
        return getEmployeeDto(moEmployee);
    }
    
    public List<EmployeeReqDto> getAllEmployees() {
        List<MoEmployee> employees = empRepository.findAll();
        List<EmployeeReqDto> employeeDtos = new ArrayList<>();

        for (MoEmployee moEmployee : employees) {
            EmployeeReqDto empDto = getEmployeeDto(moEmployee);
            employeeDtos.add(empDto);
        }

        return employeeDtos;
    }


    @Transactional
    public List<EmployeeResp> getTaxDeductions() {
        List<EmployeeResp> employeeResponses = new ArrayList<>();
        List<MoEmployee> employees = empRepository.findAll();

        for (MoEmployee emp : employees) {
            double yearlySalary = emp.getSalaryPerMonth() * getMonthsInService(emp.getDateOfJoining());
            double taxAmount = calculateTax(yearlySalary);
            double cessAmount = calculateCess(yearlySalary);

            EmployeeResp response = new EmployeeResp(emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(), yearlySalary, taxAmount, cessAmount);
            employeeResponses.add(response);
        }

        return employeeResponses;
    }

    private int getMonthsInService(LocalDate doj) {
        LocalDate currentDate = LocalDate.now();
        long monthsWorked = ChronoUnit.MONTHS.between(doj, currentDate);

        return (int) Math.min(monthsWorked, 12); // Cap at 12 months
    }

    @Transactional
    public double calculateEmployeeTax(String employeeId) {
        MoEmployee employee = empRepository.findById(Long.parseLong(employeeId))
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        double yearlySalary = employee.getSalaryPerMonth() * getMonthsInService(employee.getDateOfJoining());
        return calculateTax(yearlySalary);
    }

    private double calculateTax(double yearlySalary) {
        if (yearlySalary <= 250000) return 0;

        double tax = 0.0;

        if (yearlySalary <= 500000) {
            tax = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            tax = (250000 * 0.05) + (yearlySalary - 500000) * 0.1;
        } else {
            tax = (250000 * 0.05) + (500000 * 0.1) + (yearlySalary - 1000000) * 0.2;
        }

        return tax;
    }

    private double calculateCess(double yearlySalary) {
        return yearlySalary > 2500000 ? (yearlySalary - 2500000) * 0.02 : 0;
    }

    private EmployeeReqDto getEmployeeDto(MoEmployee moEmployee) {
		
		EmployeeReqDto dto = new EmployeeReqDto();
		
		dto.setEmployeeId(moEmployee.getEmployeeId());
		dto.setFirstName(moEmployee.getFirstName());
		dto.setLastName(moEmployee.getLastName());
		dto.setEmail(moEmployee.getEmail());		
		dto.setSalaryPerMonth(moEmployee.getSalaryPerMonth());		
		dto.setDateOfJoining(moEmployee.getDateOfJoining());
		dto.setPhoneNumbers(moEmployee.getPhoneNumbers());		
		return dto;
	}

    
}