package az.company.auth_api.controller;

import az.company.auth_api.dto.EmployeeDto;
import az.company.auth_api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/employee")
@RestController
public class EmployeeControllerr {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> findEmployee(@RequestBody  EmployeeDto employeeDto) {
       return employeeService.findEmployees(employeeDto);
    }
}
