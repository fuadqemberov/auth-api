package az.company.auth_api.search;

import az.company.auth_api.dto.EmployeeDto;
import az.company.auth_api.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {
    public static Specification<Employee> findEmployee(EmployeeDto employeeDto) {
        Specification<Employee> spec = Specification.where(null);

        spec = spec.and(GenericSpecifications.stringContains("jobNm", employeeDto.getJobNm()));
        spec = spec.and(GenericSpecifications.stringContains("emp_firstNm", employeeDto.getEmp_firstNm()));
        spec = spec.and(GenericSpecifications.stringContains("emp_lastNm", employeeDto.getEmp_lastNm()));
        spec = spec.and(GenericSpecifications.numberEquals("salary", employeeDto.getSalary()));
        spec = spec.and(GenericSpecifications.joinAttributeContains("department", "dept_name", employeeDto.getDepartmentName()));

        return spec;
    }
}
