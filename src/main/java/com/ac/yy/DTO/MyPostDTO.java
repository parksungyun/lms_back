package com.ac.yy.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MyPostDTO {
    private int id;
    private String type;
    private String title;
    private String content;
    private LocalDateTime regDate;
}