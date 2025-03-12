package com.heroku.serviceImpl;

import com.heroku.dto.EmployeeReqDto;
import com.heroku.mapper.EmployeeMapper;
import com.heroku.model.Employee;
import com.heroku.repo.EmployeeRepo;
import com.heroku.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;


    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;

    }

    @Override
    @Transactional
    public void createEmployee(EmployeeReqDto reqDto) {
        Employee employee = EmployeeMapper.mapToEmployee(new Employee(), reqDto);
        employeeRepo.save(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(EmployeeReqDto reqDto, Long id) {
        Employee existingEmployee = getEmployeeById(id);
        EmployeeMapper.mapToEmployee(existingEmployee, reqDto);
        employeeRepo.save(existingEmployee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found!"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepo.delete(employee);

    }
}
