CREATE TABLE mo_employee (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_numbers JSON NOT NULL,
    date_of_joining DATE NOT NULL,
    salary_per_month DECIMAL(10, 2) NOT NULL
);
