package com.imaginnovate.employee.api.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class EmployeeReqDto {
	
	private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> phoneNumbers;

    private LocalDate dateOfJoining;

    private Double salaryPerMonth;

}
