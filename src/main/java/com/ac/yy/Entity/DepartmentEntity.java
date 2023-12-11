package com.ac.yy.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name")
    private String deptName;
}
