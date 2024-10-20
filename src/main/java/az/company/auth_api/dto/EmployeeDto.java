package az.company.auth_api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto {
    private String emp_lastNm;
    private String emp_firstNm;
    private String jobNm;
    private Long manager_id;
    private Date hireDt;
    private double salary;
    private double commission;
    private String departmentName;
}
