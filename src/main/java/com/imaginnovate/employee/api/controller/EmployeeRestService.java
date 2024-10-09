package com.imaginnovate.employee.api.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.employee.api.dto.EmployeeReqDto;
import com.imaginnovate.employee.api.dto.EmployeeResp;
import com.imaginnovate.employee.api.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeRestService {

	 	@Autowired
	    private EmployeeService employeeService;
	 	
	 	
	 	 @PostMapping("/employees")
	     public ResponseEntity<EmployeeReqDto> addEmployee(@Valid @RequestBody EmployeeReqDto employee) {
	 		EmployeeReqDto response = employeeService.addEmployee(employee);
	        return ResponseEntity.ok(response);
	     }
	 	 

	 	@GetMapping("/employees")
	 	public ResponseEntity<List<EmployeeReqDto>> getAllEmployees() {
	 	    return ResponseEntity.ok(employeeService.getAllEmployees());
	 	}
	 	
	 	@GetMapping("/employees/tax-deductions")
	    public ResponseEntity<List<EmployeeResp>> getTaxDeductions() {
	        List<EmployeeResp> taxDeductions = employeeService.getTaxDeductions();
	        return ResponseEntity.ok(taxDeductions);
	    }
	 	
	 	@GetMapping("/employees/{employeeId}/tax-deductions")
	    public ResponseEntity<Double> calculateTax(@PathVariable String employeeId) {
	        double tax = employeeService.calculateEmployeeTax(employeeId);
	        return ResponseEntity.ok(tax);
	    }

}
