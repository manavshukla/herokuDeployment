package com.heroku.mapper;

import com.heroku.dto.AddressDto;
import com.heroku.dto.EmployeeReqDto;
import com.heroku.dto.ExperienceDto;
import com.heroku.model.Address;
import com.heroku.model.Employee;
import com.heroku.model.Experience;

import java.time.LocalDateTime;

public class EmployeeMapper {


    public static Employee mapToEmployee(Employee employee, EmployeeReqDto employeeReqDto) {
        employee.setFirstName(employeeReqDto.getFirstName());
        employee.setLastname(employeeReqDto.getLastName());
        employee.setEmail(employeeReqDto.getEmail());
        employee.setNumber(employeeReqDto.getNumber());
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        if (employeeReqDto.getAddresses() != null) {
            for (AddressDto addressDto : employeeReqDto.getAddresses()) {
                Address address = new Address();
                address.setState(addressDto.getStreet());
                address.setCity(addressDto.getCity());
                address.setState(addressDto.getState());
                address.setCountry(addressDto.getCountry());
                address.setPostalCode(addressDto.getPostalCode());
                address.setCreatedAt(LocalDateTime.now());
                address.setUpdatedAt(LocalDateTime.now());
                address.setEmployee(employee);
                employee.addAddress(address);
            }
        }
        if (employeeReqDto.getExperiences() != null) {

            for (ExperienceDto experienceDto : employeeReqDto.getExperiences()) {
                Experience experience = new Experience();
                experience.setCompanyName(experienceDto.getCompanyName());
                experience.setJobTitle(experienceDto.getJobTitle());
                experience.setStartDate(LocalDateTime.now());
                experience.setEndDate(LocalDateTime.now());
                experience.setCreatedAt(LocalDateTime.now());
                experience.setUpdatedAt(LocalDateTime.now());
                experience.setEmployee(employee);
                employee.addExperience(experience);
            }
        }
        return employee;
    }
}
