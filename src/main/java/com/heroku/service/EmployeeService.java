package com.heroku.service;

import com.heroku.dto.EmployeeReqDto;
import com.heroku.model.Employee;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeReqDto employeeReqDto);

    void updateEmployee(EmployeeReqDto employeeReqDto, Long id);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    void deleteEmployee(Long id);


}
