package az.company.auth_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "lastname")
    private String emp_lastNm;

    @Column(name = "firstname")
    private String  emp_firstNm;

    @Column(name = "job_nm")
    private String jobNm;

    @Column(name = "manager_id", nullable = true)
    private Long manager_id;

    @Column(name = "hire_date")
    private Date hireDt;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "commission", nullable = true)
    private Double commission;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department department;

}
