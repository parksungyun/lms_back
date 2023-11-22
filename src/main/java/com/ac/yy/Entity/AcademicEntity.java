package com.ac.yy.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "academics")
public class AcademicEntity {
    @Id
    @Column(name = "academic_id")
    private int academicId;

    @Column
    private int uid;

    @Column
    private int auth;

    @Column
    private int dept;

    @Column
    private int position;

    @Column(name = "user_photo")
    private String userPhoto;

    @Column
    private String remark;

    @Column
    private int available;

    @Column
    @CreationTimestamp
    private Date reg_date;

    @Column
    @UpdateTimestamp
    private Date mod_date;
}
