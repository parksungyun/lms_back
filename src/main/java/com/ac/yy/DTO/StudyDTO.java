package com.ac.yy.DTO;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudyDTO {
    private int lectureId;
    private int studentId;
    private int progressTime;
}
