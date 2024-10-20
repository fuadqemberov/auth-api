package az.company.auth_api.service;

import az.company.auth_api.dto.EmployeeDto;
import az.company.auth_api.entity.Employee;
import az.company.auth_api.mapper.EmployeeMapper;
import az.company.auth_api.repository.EmployeeRepository;
import az.company.auth_api.search.GenericSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDto> findEmployees(EmployeeDto employeeDto) {
        Specification<Employee> spec = Specification.where(null);

        if (employeeDto.getJobNm() != null) {
            spec = spec.and(GenericSpecifications.stringContains("jobNm", employeeDto.getJobNm()));
        }

        if (employeeDto.getEmp_firstNm() != null) {
            spec = spec.and(GenericSpecifications.stringContains("emp_firstNm", employeeDto.getEmp_firstNm()));
        }

        if (employeeDto.getEmp_lastNm() != null) {
            spec = spec.and(GenericSpecifications.stringContains("emp_lastNm", employeeDto.getEmp_lastNm()));
        }

        if (employeeDto.getSalary() != 0) {
            spec = spec.and(GenericSpecifications.numberEquals("salary", employeeDto.getSalary()));
        }

        if(employeeDto.getDepartmentName() != null){
            spec = spec.and(GenericSpecifications.joinAttributeContains("department", "dept_name", employeeDto.getDepartmentName()));
        }

        return employeeRepository.findAll(spec).stream().map(employeeMapper::toDto).toList();

    }
}
