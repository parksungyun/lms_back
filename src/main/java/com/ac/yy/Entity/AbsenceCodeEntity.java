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
@Table(name = "absence_code")
public class AbsenceCodeEntity {
    @Id
    @Column(name = "absence_id")
    private int absenceId;

    @Column(name = "absence_name")
    private String absenceName;
}
