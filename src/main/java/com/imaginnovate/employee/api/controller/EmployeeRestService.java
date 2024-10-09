package com.imaginnovate.employee.api.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.employee.api.dto.EmployeeReqDto;
import com.imaginnovate.employee.api.dto.EmployeeResp;
import com.imaginnovate.employee.api.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeRestService {

	 	@Autowired
	    private EmployeeService employeeService;
	 	
	 	
	 	@PostMapping
	     public ResponseEntity<EmployeeReqDto> addEmployee(@RequestBody EmployeeReqDto employee) {
	 		EmployeeReqDto response = employeeService.addEmployee(employee);
	        return ResponseEntity.ok(response);
	     }
	 	
	 	@GetMapping("/taxdeductions")
	    public ResponseEntity<List<EmployeeResp>> getTaxDeductions() {
	        List<EmployeeResp> taxDeductions = employeeService.getTaxDeductions();
	        return ResponseEntity.ok(taxDeductions);
	    }

	   
}
