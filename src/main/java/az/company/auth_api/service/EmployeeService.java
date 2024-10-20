package az.company.auth_api.service;

import az.company.auth_api.dto.EmployeeDto;
import az.company.auth_api.mapper.EmployeeMapper;
import az.company.auth_api.repository.EmployeeRepository;
import az.company.auth_api.search.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDto> findEmployees(EmployeeDto employeeDto) {
        return employeeRepository
                .findAll(EmployeeSpecification.findEmployee(employeeDto))
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }
}
