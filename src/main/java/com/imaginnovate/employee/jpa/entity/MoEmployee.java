package com.imaginnovate.employee.jpa.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;


@Entity
@Table(name = "mo_employee")
public class MoEmployee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

	@Column(nullable = false)
    private String firstName;

	@Column(nullable = false)
    private String lastName;

	@Column(nullable = false)
    private String email;

	@Column(nullable = false)
    private List<String> phoneNumbers;

    @Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoining;

    @Column(nullable = false)
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
