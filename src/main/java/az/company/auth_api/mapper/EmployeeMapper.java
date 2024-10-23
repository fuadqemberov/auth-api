package az.company.auth_api.mapper;

import az.company.auth_api.dto.EmployeeDto;
import az.company.auth_api.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  EmployeeMapper {

    Employee toEntity(EmployeeDto employeeDto);

    @Mapping(source = "department.dept_name", target = "departmentName")
    EmployeeDto toDto(Employee employee);
}
