package az.company.auth_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {

    @Id
    private Long dept_id;

    @Column(name = "DEPT_NAME")
    private String dept_name;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> emps;

}