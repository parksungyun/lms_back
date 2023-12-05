package com.ac.yy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private int academicId;
    private String courseName;
    private int subjectNo;
    private int capacity;
    private String startDate;
    private String endDate;
    private String recruitStart;
    private String recruitEnd;
    private String courseInfo;
}
