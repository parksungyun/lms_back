package com.ac.yy.DTO;

import com.ac.yy.Entity.FeedbackEntity;
import com.ac.yy.Entity.SubmitEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubmitDTO {
    private SubmitEntity submit;
    private FeedbackEntity feedback;
}
