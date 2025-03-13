package com.heroku.controller;

import com.heroku.dto.EmployeeReqDto;
import com.heroku.model.Employee;
import com.heroku.serviceImpl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;


    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeReqDto employeeReqDto) {
        try {
            employeeService.createEmployee(employeeReqDto);
            return ResponseEntity.status(HttpStatus.OK).body(employeeReqDto);
        } catch (Exception e) {
            System.out.println("Employee not created!!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee not created!!");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeReqDto employeeReqDto, @PathVariable Long id) {
        try {
            employeeService.updateEmployee(employeeReqDto, id);
            return ResponseEntity.status(HttpStatus.OK).body("Employee updated with id:" + id);
        } catch (Exception e) {
            System.out.println("Not updated");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not updated with id:" + id);
        }

    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> employeeList = employeeService.getAllEmployees();
            return ResponseEntity.status(HttpStatus.OK).body(employeeList);
        } catch (Exception e) {
            System.out.println("Not found!!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employees not found");
        }
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(employee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with id:-" + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
        try {
            //Employee employee = employeeService.getEmployeeById(id);
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).body("Employee deleted with id:-" + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }
}
