package com.imaginnovate.employee.api.dto;

import java.time.LocalDate;
import java.util.List;


public class EmployeeReqDto {
	
	private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> phoneNumbers;

    private LocalDate dateOfJoining;

    private Double salaryPerMonth;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Double getSalaryPerMonth() {
		return salaryPerMonth;
	}

	public void setSalaryPerMonth(Double salaryPerMonth) {
		this.salaryPerMonth = salaryPerMonth;
	}
    
    
}
