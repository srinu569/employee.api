package com.imaginnovate.employee.api.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.employee.api.dto.EmployeeReqDto;
import com.imaginnovate.employee.api.dto.EmployeeResp;
import com.imaginnovate.employee.api.jpa.repositories.MoEmployeeRepository;
import com.imaginnovate.employee.jpa.entity.MoEmployee;

@Service
public class EmployeeService {
	
	@Autowired
    private MoEmployeeRepository empRepository;

	public EmployeeReqDto addEmployee(EmployeeReqDto employee) {
		
		 validateEmployeeRequest(employee);
		
		MoEmployee moEmployee = new MoEmployee();
		
		moEmployee.setFirstName(employee.getFirstName());
		moEmployee.setLastName(employee.getLastName());
		moEmployee.setEmail(employee.getEmail());		
		moEmployee.setSalaryPerMonth(employee.getSalaryPerMonth());		
		moEmployee.setDateOfJoining(employee.getDateOfJoining());
		moEmployee.setPhoneNumbers(employee.getPhoneNumbers());
		empRepository.save(moEmployee);
		
		EmployeeReqDto response = getEmployeeDto(moEmployee);
		
		return response;
	}
	
	private void validateEmployeeRequest(EmployeeReqDto employee) {
		
		if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
	        throw new IllegalArgumentException("First name is required");
	    }
	    if (employee.getLastName() == null || employee.getLastName().isEmpty()) {
	        throw new IllegalArgumentException("Last name is required");
	    }
	    if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
	        throw new IllegalArgumentException("Email is required");
	    }
	    if (employee.getSalaryPerMonth() == null || employee.getSalaryPerMonth() <= 0) {
	        throw new IllegalArgumentException("Salary must be greater than zero");
	    }
	    if (employee.getDateOfJoining() == null) {
	        throw new IllegalArgumentException("Date of joining is required");
	    }
	    if (employee.getPhoneNumbers() == null || employee.getPhoneNumbers().isEmpty()) {
	        throw new IllegalArgumentException("At least one phone number is required");
	    }
		
	}

	public List<EmployeeResp> getTaxDeductions() {
        List<EmployeeResp> employeeResponses = new ArrayList<>();
        List<MoEmployee> employees = empRepository.findAll();
        
        for (MoEmployee emp : employees) {
        	
            int monthsInService = getMonthsInFinancialYear(emp.getDateOfJoining());
            
            double yearlySalary = emp.getSalaryPerMonth() * monthsInService;
            
            double taxAmount = calculateTax(yearlySalary);

           
            double cessAmount = calculateCess(yearlySalary);

           
            EmployeeResp response = new EmployeeResp(
                emp.getEmployeeId(),
                emp.getFirstName(),
                emp.getLastName(),
                yearlySalary,
                taxAmount,
                cessAmount
            );
            employeeResponses.add(response);
        }
        
        return employeeResponses;
    }
	
	private int getMonthsInFinancialYear(LocalDate doj) {
        LocalDate startOfYear = LocalDate.of(LocalDate.now().getYear(), 4, 1); 
        LocalDate endOfYear = LocalDate.of(LocalDate.now().getYear() + 1, 3, 31);
        if (doj.isAfter(startOfYear)) {
            return (int) ChronoUnit.MONTHS.between(doj.withDayOfMonth(1), endOfYear);
        }
        return 12;
    }

    private double calculateTax(double yearlySalary) {
        double tax = 0.0;

        if (yearlySalary <= 250000) {
            return tax;
        }
        if (yearlySalary > 250000 && yearlySalary <= 500000) {
            tax += (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
            tax += (500000 - 250000) * 0.05 + (yearlySalary - 500000) * 0.1;
        } else if (yearlySalary > 1000000) {
            tax += (500000 - 250000) * 0.05 + (1000000 - 500000) * 0.1 + (yearlySalary - 1000000) * 0.2;
        }
        return tax;
    }

    private double calculateCess(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        }
        return 0.0;
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
