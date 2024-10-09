package com.imaginnovate.employee.api.jpa.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mo_employee")
public class MoEmployee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

	@NotBlank(message = "First Name is required")
    private String firstName;

	 @NotBlank(message = "Last Name is required")
    private String lastName;

	 @Email(message = "Invalid email format")
    private String email;

	@ElementCollection
    @CollectionTable(name = "employee_phone_numbers", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "phone_number")
    @NotEmpty(message = "At least one phone number is required")
	private List<String> phoneNumbers;

	 @NotNull(message = "Date of joining is required")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoining;

	 @NotNull(message = "Salary is required")
    private Double salaryPerMonth;

	
}
