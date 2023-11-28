package com.ac.yy.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "feedbacks")
public class FeedbackEntity {
    @Id
    @Column(name = "submit_id")
    private int submitId;

    @Column(name = "hw_score")
    private int hwScore;

    @Column(name = "hw_comment")
    private String hwComment;

    @Column(name = "academic_id")
    private int academicId;

    @Column(name = "feedback_reg_date")
    @CreationTimestamp
    private LocalDateTime feedbackRegDate;

    @Column(name = "feedback_mod_date")
    @UpdateTimestamp
    private LocalDateTime feedbackModDate;
}
